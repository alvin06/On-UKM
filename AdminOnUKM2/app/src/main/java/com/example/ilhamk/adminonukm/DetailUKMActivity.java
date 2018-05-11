package com.example.ilhamk.adminonukm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailUKMActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewUKMname;
    private TextView textViewJadwalLatihan;
    private TextView textViewKategori;
    private TextView textViewPembina;
    private TextView textViewTotAnggota;

    private Button btnEditUKM;
    private Button btnLihatAnggota;

    private String idUKM, namaUKM, jadwalLatihan, kategori, pembina, totalAnggota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ukm);

        Intent intent = getIntent();

        idUKM = intent.getStringExtra(LihatUKMActivity.UKM_ID);
        namaUKM = intent.getStringExtra(LihatUKMActivity.UKM_Nama);
        jadwalLatihan = intent.getStringExtra(LihatUKMActivity.UKM_Jadwal);
        kategori = intent.getStringExtra(LihatUKMActivity.UKM_Kat);
        pembina = intent.getStringExtra(LihatUKMActivity.UKM_Pembina);
        totalAnggota = intent.getStringExtra(LihatUKMActivity.UKM_totAnggota);

        textViewUKMname = (TextView) findViewById(R.id.textViewUKMName);
        textViewJadwalLatihan = (TextView) findViewById(R.id.textViewJadwalLatihan);
        textViewKategori = (TextView) findViewById(R.id.textViewKategori);
        textViewPembina = (TextView) findViewById(R.id.textViewPembina);
        textViewTotAnggota = (TextView) findViewById(R.id.textViewTotAnggonta);

        btnEditUKM = (Button) findViewById(R.id.btnEditUKM);
        btnLihatAnggota = (Button) findViewById(R.id.btnLihatAnggota);

        btnEditUKM.setOnClickListener(this);
        btnLihatAnggota.setOnClickListener(this);

        textViewUKMname.setText("Nama UKM : " + namaUKM);
        textViewJadwalLatihan.setText("Jadwal Latihan : " + jadwalLatihan);
        textViewKategori.setText("Kategori : " + kategori);
        textViewPembina.setText("Pembina : " + pembina);
        textViewTotAnggota.setText("Total Anggota : " + totalAnggota);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLihatAnggota){
            startActivity(new Intent(DetailUKMActivity.this, LihatAnggotaActivity.class ));
        }
        if(v == btnEditUKM){
            startActivity(new Intent(DetailUKMActivity.this, EditUKMActivity.class ));
        }
    }
}
