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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditUKMActivity extends AppCompatActivity implements View.OnClickListener{

    private static String idUKM, namaUKM, pembina, totAnggota, jadwal, imURL, imPoster, Caption, Oprec;

    private EditText editTextNamaUKM;
    private EditText editTextJadwalUKM;
    private EditText editTextPembinaUKM;

    private Button btnSimpanUKM;
    private Button btnBatalUKM;

    private Spinner spinnerEditKategori;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ukm);

        Intent intent = getIntent();
        idUKM = intent.getStringExtra(DetailUKMActivity.ID_UKM);
        namaUKM = intent.getStringExtra(DetailUKMActivity.NAMA_UKM);
        pembina = intent.getStringExtra(DetailUKMActivity.PEMBINA_UKM);
        totAnggota = intent.getStringExtra(DetailUKMActivity.TOTANGGOTA_UKM);
        jadwal = intent.getStringExtra(DetailUKMActivity.JADWAL_UKM);
        imURL = intent.getStringExtra(DetailUKMActivity.IMURL_UKM);
        imPoster = intent.getStringExtra(DetailUKMActivity.IMPoster_UKM);
        Caption = intent.getStringExtra(DetailUKMActivity.Caption_UKM);
        Oprec = intent.getStringExtra(DetailUKMActivity.Oprec_UKM);


        databaseReference = FirebaseDatabase.getInstance().getReference("ukm");

        editTextJadwalUKM = findViewById(R.id.editJadwalUKM);
        editTextJadwalUKM.setText(jadwal);
        editTextNamaUKM = findViewById(R.id.editNamaUKM);
        editTextNamaUKM.setText(namaUKM);
        editTextPembinaUKM = findViewById(R.id.editPembinaUKM);
        editTextPembinaUKM.setText(pembina);

        spinnerEditKategori = findViewById(R.id.editKategoriUKM);

        btnBatalUKM = findViewById(R.id.btnBatalUKM);
        btnSimpanUKM = findViewById(R.id.btnSimpanUKM);

        btnSimpanUKM.setOnClickListener(this);
        btnBatalUKM.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

    }

    private void updateUKM(){
        String namaUKM = editTextNamaUKM.getText().toString().trim();
        String jadwalLatihan = editTextJadwalUKM.getText().toString().trim();
        String pembina = editTextPembinaUKM.getText().toString().trim();
        String kategori = spinnerEditKategori.getSelectedItem().toString();

        Integer totalAnggota = Integer.parseInt(totAnggota);
        Boolean oprec = true;

        if(TextUtils.equals(Oprec, "false")){
            oprec = false;
        }

        progressDialog.setMessage("Mengubah data UKM...");
        progressDialog.show();

        UKMInformation ukmInformation = new UKMInformation(idUKM, jadwalLatihan, namaUKM, totalAnggota,
                pembina, kategori, imURL, imPoster, Caption, oprec);
        databaseReference.child(idUKM).setValue(ukmInformation)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), LihatUKMActivity.class));
                            Toast.makeText(getApplicationContext(), "Berhasil Mengubah data UKM",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(new Intent(getApplicationContext(), LihatUKMActivity.class));
                            Toast.makeText(getApplicationContext(), "Gagal Menambahkan UKM",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == btnSimpanUKM){
            updateUKM();
        }
        if(v == btnBatalUKM){
            startActivity(new Intent(getApplicationContext(), LihatUKMActivity.class));
        }
    }
}
