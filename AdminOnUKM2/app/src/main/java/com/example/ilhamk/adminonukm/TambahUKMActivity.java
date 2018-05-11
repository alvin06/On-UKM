package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahUKMActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddUKM;
    private EditText editTextNamaUKM;
    private EditText editTextJadwal;
    private EditText editTextPembina;
    private Spinner spinnerKategori;

    private DatabaseReference databaseReference;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ukm);

        databaseReference = FirebaseDatabase.getInstance().getReference("ukm");

        progressDialog = new ProgressDialog(this);
        btnAddUKM = (Button) findViewById(R.id.btnAddUKM);
        editTextNamaUKM = (EditText) findViewById(R.id.editTextNamaUKM);
        editTextJadwal = (EditText) findViewById(R.id.editTextJadwal);
        editTextPembina = (EditText) findViewById(R.id.editTextPembina);
        spinnerKategori = (Spinner) findViewById(R.id.spinnerKategori);

        btnAddUKM.setOnClickListener(this);
    }

    private void addUKM(){
        String namaUKM = editTextNamaUKM.getText().toString().trim();
        String jadwalLatihan = editTextJadwal.getText().toString().trim();
        Integer totalAnggota = 0;
        String pembina = editTextPembina.getText().toString().trim();
        String kategori = spinnerKategori.getSelectedItem().toString();

        if(TextUtils.isEmpty(namaUKM)){
            Toast.makeText(this, "Nama UKM tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(jadwalLatihan)){
            Toast.makeText(this, "Masukkan Jadwal Latihan", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pembina)){
            pembina = "Belum ada";
        }

        String key = databaseReference.child("ukm").push().getKey();
        UKMInformation ukmInformation = new UKMInformation(key, jadwalLatihan, namaUKM,
                totalAnggota, pembina, kategori);

        progressDialog.setMessage("Menambahkan UKM..");
        progressDialog.show();

        databaseReference.child(key).setValue(ukmInformation)
            .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(TambahUKMActivity.this, "Berhasil Menambahkan UKM",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(TambahUKMActivity.this, "Gagal Menambahkan UKM",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public void onClick(View v) {
        if(v == btnAddUKM){
            addUKM();
        }
    }
}
