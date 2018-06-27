package com.example.alvinafandi.on_ukm;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alvinafandi.on_ukm.classes.ukmTest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class OpenRecruitmentActivity extends AppCompatActivity {

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private ImageView posterView;
    private EditText caption;
    private Button selectPosterButton, postRecruitmentButton;

    private Uri imageUri;

    static final int PICK_IMAGE_REQUEST = 71;

    private ukmTest ukm;

    @Override
    public void onResume(){
        super.onResume();
        BottomNavigationView mNavigationView = findViewById(R.id.tab_homeOn);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_recruitment);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("ukm");

        posterView = findViewById(R.id.imageViewPoster);
        caption = findViewById(R.id.caption);
        selectPosterButton = findViewById(R.id.selectPosterButton);
        postRecruitmentButton = findViewById(R.id.postButton);

        ukm = getIntent().getParcelableExtra("ukmTag");

        selectPosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        postRecruitmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRecruitment();
            }
        });

        BottomNavigationView mNavigationView = findViewById(R.id.tab_homeOn);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
    }

    private void postRecruitment() {

        if(imageUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Posting....");
            progressDialog.show();

            StorageReference ref = storageReference.child("poster/" + ukm.getNamaUKM()); //nanti ganti UUID ke ukm.getNamaUkm()
            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dapetin download url
                            //dapetin caption
                            ukm.setCaption(caption.getText().toString().trim());
                            ukm.setPosterUKM(taskSnapshot.getDownloadUrl().toString());
                            //update poster, caption, status ukm
                            databaseReference.child(ukm.getIdUKM() + "/caption").setValue(ukm.getCaption());
                            databaseReference.child(ukm.getIdUKM() + "/posterUKM").setValue(ukm.getPosterUKM());
                            progressDialog.dismiss();
                            //Toast.makeText(OpenRecruitmentActivity.this,"Recruitment Posted " + taskSnapshot.getDownloadUrl().toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(OpenRecruitmentActivity.this,"Post Recruitment Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Recruitment Posted " + (int)progress + "%");
                        }
                    });
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                posterView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.tab_profileOff:
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.tab_home:
                    intent = new Intent(getApplicationContext(), UkmHomeActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };
}
