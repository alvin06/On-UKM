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

    public static String ID_UKM;
    public static String NAMA_UKM;
    public static String KATEGORI_UKM;
    public static String PEMBINA_UKM;
    public static String TOTANGGOTA_UKM;
    public static String JADWAL_UKM;

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

        ID_UKM = intent.getStringExtra(LihatUKMActivity.UKM_ID);
        NAMA_UKM = intent.getStringExtra(LihatUKMActivity.UKM_Nama);
        JADWAL_UKM = intent.getStringExtra(LihatUKMActivity.UKM_Jadwal);
        KATEGORI_UKM = intent.getStringExtra(LihatUKMActivity.UKM_Kat);
        PEMBINA_UKM = intent.getStringExtra(LihatUKMActivity.UKM_Pembina);
        TOTANGGOTA_UKM = intent.getStringExtra(LihatUKMActivity.UKM_totAnggota);

        textViewUKMname = (TextView) findViewById(R.id.textViewUKMName);
        textViewJadwalLatihan = (TextView) findViewById(R.id.textViewJadwalLatihan);
        textViewKategori = (TextView) findViewById(R.id.textViewKategori);
        textViewPembina = (TextView) findViewById(R.id.textViewPembina);
        textViewTotAnggota = (TextView) findViewById(R.id.textViewTotAnggonta);

        btnEditUKM = (Button) findViewById(R.id.btnEditUKM);
        btnLihatAnggota = (Button) findViewById(R.id.btnLihatAnggota);

        btnEditUKM.setOnClickListener(this);
        btnLihatAnggota.setOnClickListener(this);

        textViewUKMname.setText(NAMA_UKM);
        textViewJadwalLatihan.setText(JADWAL_UKM);
        textViewKategori.setText(KATEGORI_UKM);
        textViewPembina.setText(PEMBINA_UKM);
        textViewTotAnggota.setText(TOTANGGOTA_UKM);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLihatAnggota){
            Intent intent = new Intent(getApplicationContext(), LihatAnggotaActivity.class);

            intent.putExtra(ID_UKM, ID_UKM);
            startActivity(intent);
        }
        if(v == btnEditUKM){
            Intent intent = new Intent(getApplicationContext(), EditUKMActivity.class);

            intent.putExtra(ID_UKM, ID_UKM);
            intent.putExtra(NAMA_UKM, NAMA_UKM);
            intent.putExtra(KATEGORI_UKM, KATEGORI_UKM);
            intent.putExtra(JADWAL_UKM, JADWAL_UKM);
            intent.putExtra(PEMBINA_UKM, PEMBINA_UKM);
            intent.putExtra(TOTANGGOTA_UKM, TOTANGGOTA_UKM);

            startActivity(intent);
        }
    }
}
