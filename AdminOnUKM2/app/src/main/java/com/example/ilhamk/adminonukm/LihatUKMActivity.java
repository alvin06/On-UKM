package com.example.ilhamk.adminonukm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LihatUKMActivity extends AppCompatActivity{

    public static final String UKM_ID = "idUKM";
    public static final String UKM_Nama = "namaUKM";
    public static final String UKM_Jadwal = "jadwalUKM";
    public static final String UKM_Kat = "kategoriUKM";
    public static final String UKM_Pembina = "pembinaUKM";
    public static final String UKM_totAnggota = "totAnggotaUKM";

    private ListView listViewUKM;
    private List<UKMInformation> ukmList;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_ukm);

        databaseReference = FirebaseDatabase.getInstance().getReference("ukm");

        listViewUKM = (ListView) findViewById(R.id.listViewUKM);

        ukmList = new ArrayList<>();

        listViewUKM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UKMInformation ukm = ukmList.get(position);

                Intent intent = new Intent(getApplicationContext(), DetailUKMActivity.class);

                intent.putExtra(UKM_ID, ukm.getIdUKM());
                intent.putExtra(UKM_Nama, ukm.getNamaUKM());
                intent.putExtra(UKM_Jadwal, ukm.getJadwalLatihan());
                intent.putExtra(UKM_Kat, ukm.getKategori());
                intent.putExtra(UKM_Pembina, ukm.getPembina());
                intent.putExtra(UKM_totAnggota, ukm.getTotalAnggota().toString());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ukmList.clear();

                for(DataSnapshot ukmSnapshot : dataSnapshot.getChildren()){
                    UKMInformation ukm = ukmSnapshot.getValue(UKMInformation.class);
                    ukmList.add(ukm);
                }

                UKMList adapter = new UKMList(LihatUKMActivity.this, ukmList);
                listViewUKM.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
