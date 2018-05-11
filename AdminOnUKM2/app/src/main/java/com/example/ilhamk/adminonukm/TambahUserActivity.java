package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TambahUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static String UKM_ID = "id_ukm";
    public static String UKM_Jadwal = "jadwal_ukm";
    public static String UKM_Nama = "nama_ukm";
    public static int UKM_Anggota = 0;
    public static String UKM_Pembina = "pembina_ukm";
    public static String UKM_Kategori = "kategori_ukm";

    private EditText editTextEmail;
    private EditText editTextPass;

//    private EditText editTextNamaUser;
//    private EditText editTextNIM;
//    private EditText editTextJurusan;

    private Spinner spinnerRole;
    private Spinner spinnerUKM;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private ProgressDialog progressDialog;

    List<UKMInformation> ukmInformationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_user);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextEmail = (EditText) findViewById(R.id.editTextEmailP);
        editTextPass = (EditText) findViewById(R.id.editTextPassP);

//        editTextNamaUser = (EditText) findViewById(R.id.editTextNamaUser);
//        editTextNIM = (EditText) findViewById(R.id.editTextNIM);
//        editTextJurusan = (EditText) findViewById(R.id.editTextJurusan);

        spinnerRole = (Spinner) findViewById(R.id.spinnerRole);
        spinnerUKM = (Spinner) findViewById(R.id.spinnerUKM);

        ukmInformationList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);

        spinnerUKM.setOnItemSelectedListener(this);
    }

    public void addUser(View view){
        final String emailP = editTextEmail.getText().toString().trim();
        String passwordP = editTextPass.getText().toString().trim();

        final String nama = "Belum diisi";
        final String nim  = "Belum diisi";
        final String jurusan = "Belum diisi";
        final String phone = "-";
        final Integer angkatan = 50;
        final String role = "Pengurus";

        final String jabatan = spinnerRole.getSelectedItem().toString();

        if(TextUtils.isEmpty(emailP)) {
            Toast.makeText(this, "Masukkan Email User", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordP)) {
            Toast.makeText(this, "Masukkan Password User", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Masukkan Nama User", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(nim)) {
            Toast.makeText(this, "Masukkan NIM User", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(jurusan)) {
            Toast.makeText(this, "Masukkan Jurusan", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Menambahkan User..");
        progressDialog.show();

        //buat akun pengurus ukm
        firebaseAuth.createUserWithEmailAndPassword(emailP,passwordP)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        //data user
                        String id_user = firebaseAuth.getCurrentUser().getUid();
                        UserInformation userInformation = new UserInformation(id_user, nama, nim, jurusan, phone,
                                angkatan, role, emailP);
                        //push data ke user
                        databaseReference.child("user").child(id_user).setValue(userInformation)
                                .addOnCompleteListener(TambahUserActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        if(task.isSuccessful()){
                                            finish();
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            Toast.makeText(TambahUserActivity.this, "Berhasil Menambahkan User",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(TambahUserActivity.this, "Gagal Menambahkan User",
                                                    Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    }
                                });

                        //data pengurus
                        PengurusInformation pengurusInformation = new PengurusInformation(jabatan, UKM_ID, id_user, emailP);
                        //push data ke pengurus
                        databaseReference.child("pengurus").child(UKM_ID).child(id_user).setValue(pengurusInformation)
                                .addOnCompleteListener(TambahUserActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(TambahUserActivity.this, "Gagal Menambahkan User",
                                                    Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    }
                                });
                        //update total anggota ukm
                        UKMInformation ukmInformation = new UKMInformation(UKM_ID, UKM_Jadwal, UKM_Nama,
                                UKM_Anggota, UKM_Pembina, UKM_Kategori);
                        databaseReference.child("ukm").child(UKM_ID).setValue(ukmInformation);
                    }
                    else{
                        Toast.makeText(TambahUserActivity.this, "Gagal menambahkan user",
                                Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //mengisi data spinner dari firebase
        Query ukmQuery =  databaseReference.child("ukm").orderByChild("namaUKM");

        ukmQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ukmInformationList.clear();

                for(DataSnapshot ukmSnapshot : dataSnapshot.getChildren()){
                    UKMInformation ukmInformation = ukmSnapshot.getValue(UKMInformation.class);
                    ukmInformationList.add(ukmInformation);
                }
                UKMList adapter = new UKMList(TambahUserActivity.this, ukmInformationList);
                spinnerUKM.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        UKMInformation ukmInformation = ukmInformationList.get(position);

        UKM_ID = ukmInformation.getIdUKM();
        UKM_Jadwal = ukmInformation.getJadwalLatihan();
        UKM_Nama = ukmInformation.getNamaUKM();
        UKM_Anggota = ukmInformation.getTotalAnggota() + 1;
        UKM_Pembina = ukmInformation.getPembina();
        UKM_Kategori = ukmInformation.getKategori();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
