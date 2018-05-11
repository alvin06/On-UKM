package com.example.ilhamk.adminonukm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnTambahUKM;
    private Button btnTambahPengurus;
    private Button btnLihatUKM;
    private Button btnLihatUser;
    private Button btnLogout;
    private Button btnTambahMhs;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        btnTambahUKM = (Button) findViewById(R.id.btnTambahUKM);
        btnTambahPengurus = (Button) findViewById(R.id.btnTambahPengurus);
        btnTambahMhs = (Button) findViewById(R.id.btnTambahMhs);
        btnLihatUKM = (Button) findViewById(R.id.btnLihatUKM);
        btnLihatUser = (Button) findViewById(R.id.btnLihatUser);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(this);
        btnTambahPengurus.setOnClickListener(this);
        btnTambahMhs.setOnClickListener(this);
        btnTambahUKM.setOnClickListener(this);
        btnLihatUser.setOnClickListener(this);
        btnLihatUKM.setOnClickListener(this);
    }

    private void logout(){
        firebaseAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogout){
            logout();
        }
        if(v == btnTambahUKM){
            startActivity(new Intent(this, TambahUKMActivity.class));
        }
        if(v == btnTambahPengurus){
            startActivity(new Intent(this, TambahUserActivity.class));
        }
        if(v == btnTambahMhs){
            startActivity(new Intent(this, TambahMhsActivity.class));
        }
        if(v == btnLihatUser){
            startActivity(new Intent(this, LihatUserActivity.class));
        }
        if(v == btnLihatUKM){
            startActivity(new Intent(this, LihatUKMActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
