package com.example.alvinafandi.on_ukm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alvinafandi.on_ukm.classes.User;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView textViewAngkatanUs;
    private TextView textViewNamaUs;
    private TextView textViewNIMUs;
    private TextView textViewTelpUs;
    private TextView textViewJurusanUs;

    private FirebaseAuth firebaseAuth;

    private Button btnEdit;
    private Button btnLogout;
    private User user;

    public void onResume(){
        super.onResume();
        BottomNavigationView mNavigationView = findViewById(R.id.tab_homeOn);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigationView.getMenu().findItem(R.id.tab_profileOff).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEdit = findViewById(R.id.edit);
        btnLogout = findViewById(R.id.logout);
        firebaseAuth = FirebaseAuth.getInstance();

        user = getIntent().getParcelableExtra("userTag");

        textViewAngkatanUs = (TextView) findViewById(R.id.angkatan);
        textViewNamaUs = (TextView) findViewById(R.id.nama);
        textViewNIMUs = (TextView) findViewById(R.id.nim);
        textViewTelpUs = (TextView) findViewById(R.id.no_hp);
        textViewJurusanUs = findViewById(R.id.departemen);

        textViewAngkatanUs.setText(Integer.toString(user.getAngkatan()));
        textViewNamaUs.setText(user.getNama());
        textViewNIMUs.setText(user.getNim());
        textViewTelpUs.setText(user.getPhone());
        textViewJurusanUs.setText(user.getJurusan());


        BottomNavigationView mNavigationView = findViewById(R.id.tab_homeOn);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigationView.getMenu().findItem(R.id.tab_profileOff).setChecked(true);

        btnEdit.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.tab_home:
                    Intent intent = new Intent(
                            ProfileActivity.this, UkmHomeActivity.class);
                    intent.putExtra("userTag", user);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    public void logoutUser() {
        firebaseAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogout)
            logoutUser();
        else if(v == btnEdit) {
            Intent intent = new Intent(ProfileActivity.this, EditProfile.class);
            intent.putExtra("userTag", user);
            startActivity(intent);
        }

    }
}
