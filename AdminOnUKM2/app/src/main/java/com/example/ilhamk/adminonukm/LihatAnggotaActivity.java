package com.example.ilhamk.adminonukm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LihatAnggotaActivity extends AppCompatActivity {

    public static final String USER_ID = "idUser";

    private ListView listViewAnggota;
    private List<PengurusInformation> pengurusList;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_anggota);

        Intent intent = getIntent();
        String idUKM = intent.getStringExtra(DetailUKMActivity.ID_UKM);

        databaseReference = FirebaseDatabase.getInstance().getReference("pengurus").child(idUKM);

        listViewAnggota = (ListView) findViewById(R.id.listViewAnggota);

        pengurusList = new ArrayList<>();

        listViewAnggota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PengurusInformation pengurus = pengurusList.get(position);

                Intent intent = new Intent(getApplicationContext(), DetailUserActivity.class);

                intent.putExtra(USER_ID, pengurus.getId_user());

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

                pengurusList.clear();

                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    PengurusInformation user = userSnapshot.getValue(PengurusInformation.class);
                    pengurusList.add(user);
                }

                PengurusList adapter = new PengurusList(LihatAnggotaActivity.this, pengurusList);
                listViewAnggota.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
