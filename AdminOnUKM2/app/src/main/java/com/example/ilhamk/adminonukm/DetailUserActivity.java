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

    public static String NAMA_U;
    public static String ANGKATAN_U;
    public static String EMAIL_U;
    public static String ID_U;
    public static String JURUSAN_U;
    public static String NIM_U;
    public static String TELP_U;
    public static String ROLE_U;

    private TextView textViewAngkatanU;
    private TextView textViewNamaU;
    private TextView textViewNIMU;
    private TextView textViewTelpU;
    private TextView textViewJurusanU;

    private Button btnEditU;
    private Button btnHapusU;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        Intent intent = getIntent();
        String cek = intent.getStringExtra(LihatUserActivity.USER_ID);
        if(TextUtils.equals(cek, null)){
            cek = intent.getStringExtra(LihatAnggotaActivity.USER_ID);
        }

        ID_U = cek;
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(ID_U);

        textViewAngkatanU = (TextView) findViewById(R.id.textViewAngkatanU);
        textViewNamaU = (TextView) findViewById(R.id.textViewNamaU);
        textViewNIMU = (TextView) findViewById(R.id.textViewNIMU);
        textViewTelpU = (TextView) findViewById(R.id.textViewTelpU);
        textViewJurusanU = findViewById(R.id.textViewDepartemen);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation users = dataSnapshot.getValue(UserInformation.class);
                ANGKATAN_U = users.getAngkatan().toString();
                NAMA_U = users.getNama();
                NIM_U = users.getNim();
                TELP_U = users.getPhone().toString();
                JURUSAN_U = users.getJurusan();
                ROLE_U = users.getRole();
                EMAIL_U = users.getEmail();

                textViewAngkatanU.setText(ANGKATAN_U);
                textViewNamaU.setText(NAMA_U);
                textViewNIMU.setText(NIM_U);
                textViewTelpU.setText(TELP_U);
                textViewJurusanU.setText(JURUSAN_U);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnEditU = (Button) findViewById(R.id.btnEditUs);
        btnHapusU = (Button) findViewById(R.id.btnHapusUser);

        btnHapusU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(ID_U);
            }
        });
        btnEditU.setOnClickListener(this);
    }

    private void deleteUser(String idUser){
//        DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference("user").child(idUser);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


//        dbUser.removeValue();
        Toast.makeText(getApplicationContext(), idUser, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnEditU){
            Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);

            intent.putExtra(NAMA_U, NAMA_U);
            intent.putExtra(ANGKATAN_U, ANGKATAN_U);
            intent.putExtra(NIM_U, NIM_U);
            intent.putExtra(ID_U, ID_U);
            intent.putExtra(TELP_U, TELP_U);
            intent.putExtra(EMAIL_U, EMAIL_U);
            intent.putExtra(ROLE_U, ROLE_U);
            intent.putExtra(JURUSAN_U, JURUSAN_U);

            startActivity(intent);
        }
    }
}
