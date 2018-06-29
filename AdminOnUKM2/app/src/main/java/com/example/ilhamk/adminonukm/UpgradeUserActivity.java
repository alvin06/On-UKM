package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpgradeUserActivity extends AppCompatActivity {

    private Spinner spinnerUKM, spinnerJabatan;
    private Button btnSave;

    private DatabaseReference dbUser, dbPengurus, dbUKM;

    List<UKMInformation> ukmInformationList;
    private UKMInformation ukmInformation;
    private UserInformation user;
    private PengurusInformation pengurus;
    private String role, idUKM;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_user);

        user = getIntent().getParcelableExtra("userTag");

        dbUser = FirebaseDatabase.getInstance().getReference("user");
        dbPengurus = FirebaseDatabase.getInstance().getReference("pengurus");
        dbUKM = FirebaseDatabase.getInstance().getReference("ukm");

        spinnerUKM = findViewById(R.id.spinnerUKM);
        spinnerJabatan = findViewById(R.id.spinnerRole);

        btnSave = findViewById(R.id.btnSaveUser);

        ukmInformationList = new ArrayList<>();

        spinnerUKM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ukmInformation = ukmInformationList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        progressDialog = new ProgressDialog(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
    }

    private void saveUser(){
        progressDialog.setMessage("Mengubah data pengguna");
        progressDialog.show();

        role = spinnerJabatan.getSelectedItem().toString().trim();

        //update tabel user
        dbUser.child(user.getId_user()).child("role").setValue("Pengurus");
        dbUser.child(user.getId_user()).child("idUKM").setValue(ukmInformation.getIdUKM());

        pengurus = new PengurusInformation(role, ukmInformation.getIdUKM(), user.getId_user(),
                user.getEmail(), user.getNama());

        //masukkan ke tabel pengurus
        dbPengurus.child(ukmInformation.getIdUKM()).child(user.getId_user()).setValue(pengurus);

        //tambahkan total anggota
        ukmInformation.setTotalAnggota(ukmInformation.getTotalAnggota()+1);
        dbUKM.child(ukmInformation.getIdUKM()).child("totalAnggota").setValue(ukmInformation.getTotalAnggota())
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Intent intent = new Intent(getApplicationContext(), LihatUserActivity.class);

                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Berhasil mengubah data pengguna", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Gagal mengubah data pengguna", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbUKM.orderByChild("namaUKM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ukmInformationList.clear();

                for(DataSnapshot ukmSnapshot : dataSnapshot.getChildren()){
                    ukmInformation = ukmSnapshot.getValue(UKMInformation.class);
                    ukmInformationList.add(ukmInformation);
                }
                UKMList adapter = new UKMList(UpgradeUserActivity.this, ukmInformationList);
                spinnerUKM.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
