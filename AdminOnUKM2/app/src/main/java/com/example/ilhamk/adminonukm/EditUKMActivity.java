package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditUKMActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;
    private ImageView imageViewUpload;

    private EditText editTextNamaUKM;
    private EditText editTextJadwalUKM;
    private EditText editTextPembinaUKM;

    private Button btnSimpanUKM;
    private Button btnBatalUKM;
    private ImageView btnPilihGambar;

    private Spinner spinnerEditKategori;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private ProgressDialog progressDialog;

    private UKMInformation ukmInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ukm);

        ukmInformation = getIntent().getParcelableExtra("ukmTag");

        databaseReference = FirebaseDatabase.getInstance().getReference("ukm");
        storageReference = FirebaseStorage.getInstance().getReference("logo");

        editTextJadwalUKM = findViewById(R.id.editJadwalUKM);
        editTextJadwalUKM.setText(ukmInformation.getJadwalLatihan());
        editTextNamaUKM = findViewById(R.id.editNamaUKM);
        editTextNamaUKM.setText(ukmInformation.getNamaUKM());
        editTextPembinaUKM = findViewById(R.id.editPembinaUKM);
        editTextPembinaUKM.setText(ukmInformation.getPembina());

        spinnerEditKategori = findViewById(R.id.editKategoriUKM);
//        imageViewUpload = findViewById(R.id.imageLogoUpload);

        btnBatalUKM = findViewById(R.id.btnBatalUKM);
        btnSimpanUKM = findViewById(R.id.btnSimpanUKM);
        btnPilihGambar = findViewById(R.id.btnPilihGambar);

        btnPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnSimpanUKM.setOnClickListener(this);
        btnBatalUKM.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

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
            Picasso.with(getApplicationContext()).load(mImageUri).into(btnPilihGambar);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void push(UKMInformation ukmInformation){
        databaseReference.child(ukmInformation.getIdUKM()).setValue(ukmInformation)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), LihatUKMActivity.class));
                            Toast.makeText(getApplicationContext(), "Berhasil Mengubah data UKM",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(new Intent(getApplicationContext(), LihatUKMActivity.class));
                            Toast.makeText(getApplicationContext(), "Gagal Menambahkan UKM",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUKM(){
        String namaUKM = editTextNamaUKM.getText().toString().trim();
        String jadwalLatihan = editTextJadwalUKM.getText().toString().trim();
        String pembina = editTextPembinaUKM.getText().toString().trim();
        String kategori = spinnerEditKategori.getSelectedItem().toString();

        ukmInformation.updateData(namaUKM, jadwalLatihan, pembina, kategori);

        progressDialog.setMessage("Mengubah data UKM...");
        progressDialog.show();

        if(mImageUri != null){
            StorageReference fileReference = storageReference.child(namaUKM+"."+getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String imURL = taskSnapshot.getDownloadUrl().toString();
                            ukmInformation.setLogoUKM(imURL);
                            push(ukmInformation);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{ push(ukmInformation); }
    }

    @Override
    public void onClick(View v) {
        if(v == btnSimpanUKM){
            updateUKM();
        }
        if(v == btnBatalUKM){
            startActivity(new Intent(getApplicationContext(), LihatUKMActivity.class));
        }
    }
}
