package com.example.alvinafandi.on_ukm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alvinafandi.on_ukm.classes.Pendaftaran;
import com.example.alvinafandi.on_ukm.classes.Ukm;
import com.example.alvinafandi.on_ukm.classes.User;
import com.example.alvinafandi.on_ukm.classes.ukmTest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UKMActivity extends AppCompatActivity implements View.OnClickListener{

    private ukmTest ukm;
    private User user;
    private TextView namaTextView, anggotaTextView, captionTextView;
    private ImageView logoImageView, posterImageView;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Pendaftaran pendaftaran;
    private Button buttonDaftar;

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
        setContentView(R.layout.activity_ukm);

        buttonDaftar = findViewById(R.id.buttonDaftar);
        namaTextView = findViewById(R.id.textViewUkm);
        anggotaTextView = findViewById(R.id.anggota);
        logoImageView = findViewById(R.id.logoUkm);
        captionTextView = findViewById(R.id.caption);
        posterImageView = findViewById(R.id.poster);

        ukm = getIntent().getParcelableExtra("ukmTag"); //nerima parcel yg namanya "ukmTag"
        user = getIntent().getParcelableExtra("userTag");
        databaseReference = FirebaseDatabase.getInstance().getReference("Pendaftaran_Test");
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        pendaftaran = new Pendaftaran(firebaseUser.getUid(), ukm.getIdUKM());

        Picasso.with(getApplicationContext()).load(ukm.getLogoUKM()).into(logoImageView);
        Picasso.with(getApplicationContext()).load(ukm.getPosterUKM()).into(posterImageView);
        namaTextView.setText(ukm.getNamaUKM());
        captionTextView.setText(ukm.getCaption());
        anggotaTextView.setText(Integer.toString(ukm.getTotalAnggota()));

        //jika user role ==  pengurus && user ukm == id ukm
        if(TextUtils.equals(user.getRole(), "Pengurus") && TextUtils.equals(user.getIdUKM(), ukm.getIdUKM())) {

            //jika pendaftaran belom dibuka
            if(true) {

                // buka pendaftaran
                buttonDaftar.setText("Buka Pendaftaran");
            }
            else {

                //tutup pendaftaran
                buttonDaftar.setText("Tutup Pendaftaran");
            }
        }
        else{

            //daftar
            buttonDaftar.setText("Daftar");
        }

        buttonDaftar.setOnClickListener(this);

        BottomNavigationView mNavigationView = findViewById(R.id.tab_homeOn);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
    }

    public void daftar() {
        databaseReference.push().setValue(pendaftaran).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void bukaPendaftaran() {


    }

    public void tutupPendaftaran() {
        //update poster coming soon
        //update caption coming soon
        //update status pendaftaran
    }

    @Override
    public void onClick(View v) {
        if (v == buttonDaftar) {
            //jika user role ==  pengurus && user ukm == id ukm
            if(true) {

                //jika pendaftaran belom dibuka
                if(true) {

                    // buka pendaftaran
                    //bukaPendaftaran(); atau buka activity Open Rec
                    Intent intent = new Intent(this, OpenRecruitmentActivity.class); //buat intent dari sini ke activity lain
                    intent.putExtra("ukmTag", ukm); //masukin objek ke intent
                    startActivity(intent);
                }
                else {

                    //tutup pendaftaran
                    tutupPendaftaran();
                }
            }
            else{

                //daftar
                daftar();
            }
        }
    }

    public void setLogo(Context context, String image) {
        ImageView logoUkm = (ImageView)findViewById(R.id.logoUkm);
        Picasso.with(context).load(image).into(logoUkm);
    }

    public void setPoster() {

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
