package com.example.ilhamk.adminonukm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailUserActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewAngkatanU;
    private TextView textViewNamaU;
    private TextView textViewNIMU;
    private TextView textViewTelpU;
    private TextView textViewJurusanU;

    private Button btnEditU;
    private Button btnHapusU;
    private Button btnUpU;

    private DatabaseReference databaseReference;

    private UserInformation user, users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        user = getIntent().getParcelableExtra("userTag");
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(user.getId_user());

        textViewAngkatanU = (TextView) findViewById(R.id.textViewAngkatanU);
        textViewNamaU = (TextView) findViewById(R.id.textViewNamaU);
        textViewNIMU = (TextView) findViewById(R.id.textViewNIMU);
        textViewTelpU = (TextView) findViewById(R.id.textViewTelpU);
        textViewJurusanU = findViewById(R.id.textViewDepartemen);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users = dataSnapshot.getValue(UserInformation.class);

                textViewAngkatanU.setText(String.valueOf(users.getAngkatan()));
                textViewNamaU.setText(users.getNama());
                textViewNIMU.setText(users.getNim());
                textViewTelpU.setText(users.getPhone());
                textViewJurusanU.setText(users.getJurusan());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnEditU = (Button) findViewById(R.id.btnEditUs);
//        btnHapusU = (Button) findViewById(R.id.btnHapusUser);
        btnUpU = findViewById(R.id.btnUpdateUs);

        if(!TextUtils.equals(user.getRole(), "Biasa")){
            btnUpU.setVisibility(View.INVISIBLE);
        }

//        btnHapusU.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteUser(users.getId_user());
//            }
//        });
        btnEditU.setOnClickListener(this);
        btnUpU.setOnClickListener(this);
    }

    private void deleteUser(String idUser){
//        DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference("user").child(idUser);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

//        dbUser.removeValue();
        Toast.makeText(getApplicationContext(), idUser, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnEditU){
            Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);
            intent.putExtra("userTag", users);
            startActivity(intent);
        }
        if(v == btnUpU){
            Intent intent = new Intent(getApplicationContext(), UpgradeUserActivity.class);
            intent.putExtra("userTag", users);
            startActivity(intent);
        }
    }
}
