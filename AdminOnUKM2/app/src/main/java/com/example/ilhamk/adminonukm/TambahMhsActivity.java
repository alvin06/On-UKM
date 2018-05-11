package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahMhsActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmailM;
    private EditText editTextPassM;
//    private EditText editTextNamaM;
//    private EditText editTextNIMM;
//    private EditText editTextJurusanM;
    private Spinner spinnerSbg;

    private Button btnAddMhs;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mhs);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        editTextEmailM = (EditText) findViewById(R.id.editTextEmailM);
        editTextPassM = (EditText) findViewById(R.id.editTextPassM);
//        editTextNamaM = (EditText) findViewById(R.id.editTextNamaM);
//        editTextNIMM = (EditText) findViewById(R.id.editTextNIMM);
//        editTextJurusanM = (EditText) findViewById(R.id.editTextJurusanM);

        spinnerSbg = (Spinner) findViewById(R.id.spinnerSbg);

        btnAddMhs = (Button) findViewById(R.id.btnAddMhs);

        progressDialog = new ProgressDialog(this);

        btnAddMhs.setOnClickListener(this);
    }

    private void addMhs(){
        final String emailM = editTextEmailM.getText().toString().trim();
        final String passM = editTextPassM.getText().toString().trim();

        final String namaM = "Belum diisi";
        final String nimM = "Belum diisi";
        final String jurusanM = "Belum diisi";
        final String phone = "-";
        final Integer angkatan = 50;
        final String role = spinnerSbg.getSelectedItem().toString();

        if(TextUtils.isEmpty(emailM)) {
            Toast.makeText(this, "Masukkan Email User", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passM)) {
            Toast.makeText(this, "Masukkan Password User", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Menambahkan Mahasiswa...");
        progressDialog.show();

//        String id_user = "Belum diisi";
//        final String key = databaseReference.push().getKey();
//        final UserInformation userInformation = new UserInformation(key, id_user, namaM,nimM,jurusanM, phone, angkatan, role, emailM);

        firebaseAuth.createUserWithEmailAndPassword(emailM,passM)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String id_user = firebaseAuth.getCurrentUser().getUid();
                            UserInformation userInformation = new UserInformation(id_user, namaM,nimM,jurusanM, phone, angkatan, role, emailM);
                            databaseReference.child(id_user).setValue(userInformation)
                                    .addOnCompleteListener(TambahMhsActivity.this, new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            if(task.isSuccessful()){
                                                finish();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                Toast.makeText(TambahMhsActivity.this, "Berhasil Menambahkan User",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(TambahMhsActivity.this, "Gagal Menambahkan User",
                                                        Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(TambahMhsActivity.this, "Gagal menambahkan user",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == btnAddMhs){
            addMhs();
        }
    }
}
