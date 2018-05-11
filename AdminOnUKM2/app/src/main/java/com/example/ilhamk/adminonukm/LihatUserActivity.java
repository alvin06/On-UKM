package com.example.ilhamk.adminonukm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    public static final String User_ID = "idUser";
//    public static final String UKM_ID = "idUKM";
//    public static final String UKM_ID = "idUKM";
//    public static final String UKM_ID = "idUKM";
//    public static final String UKM_ID = "idUKM";
//    public static final String UKM_ID = "idUKM";

    private ListView listViewUser;
    private List<UserInformation> userList;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_user);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        listViewUser = (ListView) findViewById(R.id.listViewUser);

        userList = new ArrayList<>();

        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserInformation user = userList.get(position);

                Intent intent = new Intent(getApplicationContext(), DetailUserActivity.class);

                intent.putExtra(User_ID, user.getId_user());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
