package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditUserActivity extends AppCompatActivity implements View.OnClickListener{

    public static String ID_U, NAMA_U, NIM_U, JURUSAN_U, ANGKATAN_U, TELP_U, EMAIL_U;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        Intent intent = getIntent();

        ID_U = intent.getStringExtra(DetailUserActivity.ID_U);
        NAMA_U = intent.getStringExtra(DetailUserActivity.NAMA_U);
        NIM_U = intent.getStringExtra(DetailUserActivity.NIM_U);
        JURUSAN_U = intent.getStringExtra(DetailUserActivity.JURUSAN_U);
        ANGKATAN_U = intent.getStringExtra(DetailUserActivity.ANGKATAN_U);
        TELP_U = intent.getStringExtra(DetailUserActivity.TELP_U);
        EMAIL_U = intent.getStringExtra(DetailUserActivity.EMAIL_U);

        editTextAngkatan = findViewById(R.id.editAngkatanUser);
        editTextAngkatan.setText(ANGKATAN_U);

        editTextNamaUser = findViewById(R.id.editNamaUser);
        editTextNamaUser.setText(NAMA_U);

        editTextJurusan = findViewById(R.id.editJurusanUser);
        editTextJurusan.setText(JURUSAN_U);

        editTextNIMUser = findViewById(R.id.editNIMUser);
        editTextNIMUser.setText(NIM_U);

        editTextTelp = findViewById(R.id.editTelpUser);
        editTextTelp.setText(TELP_U);

        spinnerRoleUser = findViewById(R.id.editRoleUser);

        btnSave = findViewById(R.id.btnSimpanUser);
        btnCancel = findViewById(R.id.btnBatalUser);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    private void saveUser(){
        String nama = editTextNamaUser.getText().toString().trim();
        String nim = editTextNIMUser.getText().toString().trim();
        String jurusan = editTextJurusan.getText().toString().trim();
        String telp = editTextTelp.getText().toString().trim();
        Integer angkatan = Integer.parseInt(editTextAngkatan.getText().toString().trim());
        String role = spinnerRoleUser.getSelectedItem().toString().trim();

        progressDialog.setMessage("Mengubah data pengguna...");
        progressDialog.show();

        UserInformation userInformation = new UserInformation(ID_U, nama, nim, jurusan, telp, angkatan,
               role, EMAIL_U);

        databaseReference.child(ID_U).setValue(userInformation)
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
