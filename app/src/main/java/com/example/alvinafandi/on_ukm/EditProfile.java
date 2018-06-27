package com.example.alvinafandi.on_ukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alvinafandi.on_ukm.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNamaUs;
    private EditText editTextNIMUs;
    private EditText editTextJurusanUs;
    private EditText editTextAngkatanUs;
    private EditText editTextTelpUs;

    private Button btnSimpan;
    private Button btnBatal;

    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = getIntent().getParcelableExtra("userTag");
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        editTextAngkatanUs = findViewById(R.id.angkatan);
        editTextNamaUs =  findViewById(R.id.nama);
        editTextNIMUs = findViewById(R.id.nim);
        editTextTelpUs = findViewById(R.id.no_hp);
        editTextJurusanUs = findViewById(R.id.departemen);

        editTextAngkatanUs.setText(Integer.toString(user.getAngkatan()));
        editTextNamaUs.setText(user.getNama());
        editTextNIMUs.setText(user.getNim());
        editTextTelpUs.setText(user.getPhone());
        editTextJurusanUs.setText(user.getJurusan());

        btnSimpan = findViewById(R.id.simpan);
        btnBatal = findViewById(R.id.batal);

        btnSimpan.setOnClickListener(this);
        btnBatal.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }


    private void saveUser() {
        String nama = editTextNamaUs.getText().toString().trim();
        String nim = editTextNIMUs.getText().toString().trim();
        String jurusan = editTextJurusanUs.getText().toString().trim();
        String telp = editTextTelpUs.getText().toString().trim();
        Integer angkatan = Integer.parseInt(editTextAngkatanUs.getText().toString().trim());


        user.updateUser(nama, nim, jurusan, telp, angkatan);



        progressDialog.setMessage("Mengubah data pengguna...");
        progressDialog.show();

        databaseReference.child(user.getid_user()).setValue(user)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        finish();
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("userTag", user);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Mengubah data user berhasil", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        Toast.makeText(getApplicationContext(), "Mengubah data user gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    @Override
    public void onClick(View v) {
        if(v == btnSimpan){
            saveUser();
        }
        if(v == btnBatal){
            startActivity(new Intent(EditProfile.this, ProfileActivity.class));
        }
    }


}
