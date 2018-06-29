package com.example.ilhamk.adminonukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LihatUserActivity extends AppCompatActivity{

    private ListView listViewUser;
    private List<UserInformation> userList;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    private UserInformation user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_user);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        listViewUser = (ListView) findViewById(R.id.listViewUser);

        userList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);

        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = userList.get(position);

                Intent intent = new Intent(getApplicationContext(), DetailUserActivity.class);

                intent.putExtra("userTag", user);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        progressDialog.setMessage("Mendapatkan list pengguna....");
        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userList.clear();

                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    UserInformation user = userSnapshot.getValue(UserInformation.class);
                    userList.add(user);
                }

                UserList adapter = new UserList(LihatUserActivity.this, userList);
                listViewUser.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
