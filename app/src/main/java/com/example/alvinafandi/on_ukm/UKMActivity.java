package com.example.alvinafandi.on_ukm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alvinafandi.on_ukm.classes.Pendaftaran;
import com.example.alvinafandi.on_ukm.classes.Ukm;
import com.example.alvinafandi.on_ukm.classes.ukmTest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;;
import com.google.firebase.database.FirebaseDatabase;

public class UKMActivity extends AppCompatActivity implements View.OnClickListener{

    private ukmTest ukm;
    private TextView namaTextView, anggotaTextView;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Pendaftaran pendaftaran;
    private Button buttonDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ukm);

        buttonDaftar = findViewById(R.id.buttonDaftar);
        namaTextView = findViewById(R.id.textViewUkm);
        anggotaTextView = findViewById(R.id.anggota);

        ukm = getIntent().getParcelableExtra("ukmTag"); //nerima parcel yg namanya "ukmTag"
        databaseReference = FirebaseDatabase.getInstance().getReference("Pendaftaran_Test");
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        pendaftaran = new Pendaftaran(firebaseUser.getUid(), ukm.getIdUkm());
        namaTextView.setText(ukm.getNamaUkm());
        anggotaTextView.setText(Integer.toString(ukm.getTotalAnggota()));


        buttonDaftar.setOnClickListener(this);
    }

    public void daftar() {
        databaseReference.push().setValue(pendaftaran).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonDaftar) {
            daftar();
        }
    }
}
