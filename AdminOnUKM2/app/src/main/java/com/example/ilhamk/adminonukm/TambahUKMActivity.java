package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TambahUKMActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int PICK_IMAGE_REQUEST = 1;

    private Button btnAddUKM;
    private Button btnPilihGambar;
    private EditText editTextNamaUKM;
    private EditText editTextJadwal;
    private EditText editTextPembina;
    private EditText editTextFile;
    private Spinner spinnerKategori;

    private Uri mImageUri;

    public String imURL = "kosong";

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ukm);

        databaseReference = FirebaseDatabase.getInstance().getReference("ukm");
        storageReference = FirebaseStorage.getInstance().getReference("logoUKM");

        progressDialog = new ProgressDialog(this);
        btnAddUKM = (Button) findViewById(R.id.btnAddUKM);
        btnPilihGambar = findViewById(R.id.btnPilihGambar);
        editTextNamaUKM = (EditText) findViewById(R.id.editTextNamaUKM);
        editTextJadwal = (EditText) findViewById(R.id.editTextJadwal);
        editTextPembina = (EditText) findViewById(R.id.editTextPembina);
        editTextFile = findViewById(R.id.editTextNamaFile);
        spinnerKategori = (Spinner) findViewById(R.id.spinnerKategori);

        btnPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnAddUKM.setOnClickListener(this);
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null){
            mImageUri = data.getData();
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void addUKM(){
        String namaUKM = editTextNamaUKM.getText().toString().trim();
        String jadwalLatihan = editTextJadwal.getText().toString().trim();
        Integer totalAnggota = 0;
        String pembina = editTextPembina.getText().toString().trim();
        String kategori = spinnerKategori.getSelectedItem().toString();

        if(TextUtils.isEmpty(namaUKM)){
            Toast.makeText(this, "Nama UKM tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(jadwalLatihan)){
            Toast.makeText(this, "Masukkan Jadwal Latihan", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pembina)){
            pembina = "Belum ada";
        }

        if(mImageUri != null){
            StorageReference fileReference = storageReference.child(namaUKM+"."+getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                            imURL = taskSnapshot.getDownloadUrl().toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else { Toast.makeText(getApplicationContext(), "Tidak ada gambar yang dipilih", Toast.LENGTH_SHORT).show(); }

        String key = databaseReference.child("ukm").push().getKey();
        UKMInformation ukmInformation = new UKMInformation(key, jadwalLatihan, namaUKM,
                totalAnggota, pembina, kategori, imURL, "Coming Soon", "Coming Soon", false);

        progressDialog.setMessage("Menambahkan UKM..");
        progressDialog.show();

        databaseReference.child(key).setValue(ukmInformation)
            .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(TambahUKMActivity.this, "Berhasil Menambahkan UKM",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(TambahUKMActivity.this, "Gagal Menambahkan UKM",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public void onClick(View v) {
        if(v == btnAddUKM){
            addUKM();
        }
    }
}
