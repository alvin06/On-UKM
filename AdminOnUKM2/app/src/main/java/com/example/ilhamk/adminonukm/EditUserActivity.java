package com.example.ilhamk.adminonukm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class EditUserActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNamaUser;
    private EditText editTextNIMUser;
    private EditText editTextJurusan;
    private EditText editTextAngkatan;
    private EditText editTextTelp;

    private Button btnSave;
    private Button btnCancel;

    private Spinner spinnerRoleUser;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    private String nama, nim, jurusan, telp, role;
    private int angkatan;

    UserInformation userInformation;
    UKMInformation ukmInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        userInformation = getIntent().getParcelableExtra("userTag");

        editTextAngkatan = findViewById(R.id.editAngkatanUser);
        editTextAngkatan.setText(String.valueOf(userInformation.getAngkatan()));

        editTextNamaUser = findViewById(R.id.editNamaUser);
        editTextNamaUser.setText(userInformation.getNama());

        editTextJurusan = findViewById(R.id.editJurusanUser);
        editTextJurusan.setText(userInformation.getJurusan());

        editTextNIMUser = findViewById(R.id.editNIMUser);
        editTextNIMUser.setText(userInformation.getNim());

        editTextTelp = findViewById(R.id.editTelpUser);
        editTextTelp.setText(userInformation.getPhone());

        spinnerRoleUser = findViewById(R.id.editRoleUser);

        btnSave = findViewById(R.id.btnSimpanUser);
        btnCancel = findViewById(R.id.btnBatalUser);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    private void saveUser(){
        nama = editTextNamaUser.getText().toString().trim();
        nim = editTextNIMUser.getText().toString().trim();
        jurusan = editTextJurusan.getText().toString().trim();
        telp = editTextTelp.getText().toString().trim();
        angkatan = Integer.parseInt(editTextAngkatan.getText().toString().trim());
        role = spinnerRoleUser.getSelectedItem().toString().trim();

        progressDialog.setMessage("Mengubah data pengguna...");
        progressDialog.show();

        if(TextUtils.equals(userInformation.getRole(), "Pengurus")){
//            kurangin total anggota
            final DatabaseReference dbUKM = FirebaseDatabase.getInstance().getReference("ukm")
                    .child(userInformation.getIdUKM());

            dbUKM.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ukmInformation = dataSnapshot.getValue(UKMInformation.class);

                    ukmInformation.setTotalAnggota(ukmInformation.getTotalAnggota()-1);
                    dbUKM.child("totalAnggota").setValue(ukmInformation.getTotalAnggota());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

//            hapus dari tabel pengurus
            DatabaseReference dbPengurus = FirebaseDatabase.getInstance().getReference("pengurus")
                    .child(userInformation.getIdUKM());
            dbPengurus.child(userInformation.getId_user()).removeValue();
        }

        userInformation.updateUser(nama, nim, jurusan, angkatan, telp, role, "kosong");

        databaseReference.child(userInformation.getId_user()).setValue(userInformation)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), LihatUserActivity.class));
                            Toast.makeText(getApplicationContext(), "Mengubah data user berhasil", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(new Intent(getApplicationContext(), LihatUserActivity.class));
                            Toast.makeText(getApplicationContext(), "Mengubah data user gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
//
    @Override
    public void onClick(View v) {
        if(v == btnSave){
            saveUser();
        }
        if(v == btnCancel){
            startActivity(new Intent(getApplicationContext(), LihatUserActivity.class));
        }
    }
}
