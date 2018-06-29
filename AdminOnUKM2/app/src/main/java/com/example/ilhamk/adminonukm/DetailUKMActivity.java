package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
    private Button btnHapusUKM;

    private ImageView imageViewLogo;

    private ProgressDialog progressDialog;

    private UKMInformation ukmInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ukm);

        ukmInformation = getIntent().getParcelableExtra("ukmTag");

        textViewUKMname = (TextView) findViewById(R.id.textViewUKMName);
        textViewJadwalLatihan = (TextView) findViewById(R.id.textViewJadwalLatihan);
        textViewKategori = (TextView) findViewById(R.id.textViewKategori);
        textViewPembina = (TextView) findViewById(R.id.textViewPembina);
        textViewTotAnggota = (TextView) findViewById(R.id.textViewTotAnggonta);

        btnEditUKM = (Button) findViewById(R.id.btnEditUKM);
        btnLihatAnggota = (Button) findViewById(R.id.btnLihatAnggota);
        btnHapusUKM = findViewById(R.id.btnHapusUKM);

        imageViewLogo = findViewById(R.id.logoUKM);

        Picasso.with(getApplicationContext()).load(ukmInformation.getLogoUKM()).into(imageViewLogo);
//
        btnEditUKM.setOnClickListener(this);
        btnLihatAnggota.setOnClickListener(this);
        btnHapusUKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUKM(ukmInformation.getIdUKM());
            }
        });

        progressDialog = new ProgressDialog(this);

        textViewUKMname.setText(ukmInformation.getNamaUKM());
        textViewJadwalLatihan.setText(ukmInformation.getJadwalLatihan());
        textViewKategori.setText(ukmInformation.getKategori());
        textViewPembina.setText(ukmInformation.getPembina());
        textViewTotAnggota.setText(String.valueOf(ukmInformation.getTotalAnggota()));
    }

    private void deleteUKM(String idUKM){
        progressDialog.setMessage("Menghapus UKM....");
        progressDialog.show();

        DatabaseReference dbUKM = FirebaseDatabase.getInstance().getReference("ukm").child(idUKM);
        dbUKM.removeValue();

        //update role pengurus jadi biasa
        DatabaseReference dbPengurus = FirebaseDatabase.getInstance().getReference("pengurus").child(idUKM);
        final DatabaseReference dbuser = FirebaseDatabase.getInstance().getReference("user");
        dbPengurus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot pengurusSnapshot : dataSnapshot.getChildren()){
                    String idPengurus = pengurusSnapshot.child("id_user").getValue(String.class);
                    dbuser.child(idPengurus+"/role").setValue("Biasa");
                    dbuser.child(idPengurus+"/idUKM").setValue("kosong");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbPengurus.removeValue();

        startActivity(new Intent(getApplicationContext(), LihatUKMActivity.class));
        Toast.makeText(getApplicationContext(), "Berhasil menghapus UKM", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnLihatAnggota){
            Intent intent = new Intent(getApplicationContext(), LihatAnggotaActivity.class);

            intent.putExtra("ukmTag", ukmInformation);
            startActivity(intent);
        }
        if(v == btnEditUKM){
            Intent intent = new Intent(getApplicationContext(), EditUKMActivity.class);

            intent.putExtra("ukmTag", ukmInformation);

            startActivity(intent);
        }
    }
}
