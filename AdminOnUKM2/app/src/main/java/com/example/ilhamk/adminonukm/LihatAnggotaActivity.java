package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
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

//    public static final String USER_ID = "idUser";

    private ListView listViewAnggota;
    private List<PengurusInformation> pengurusList;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    private String idUKM;

    private UKMInformation ukmInformation;
    private UserInformation user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_anggota);

        ukmInformation = getIntent().getParcelableExtra("ukmTag");

        databaseReference = FirebaseDatabase.getInstance().getReference("pengurus").child(ukmInformation.getIdUKM());

        listViewAnggota = (ListView) findViewById(R.id.listViewAnggota);

        pengurusList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);

        listViewAnggota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PengurusInformation pengurus = pengurusList.get(position);

                DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference("user").child(pengurus.getId_user());

                dbUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(UserInformation.class);

                        Intent intent = new Intent(getApplicationContext(), DetailUserActivity.class);

                        intent.putExtra("userTag", user);

                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        progressDialog.setMessage("Mendapatkan list pengurus UKM....");
        progressDialog.show();

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

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
