package com.example.alvinafandi.on_ukm;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.alvinafandi.on_ukm.classes.Ukm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    //private FirebaseDatabase database = FirebaseDatabase.getInstance();
    //private DatabaseReference databaseReference;
    //private ValueEventListener mPostListener;

    //private Button btnUkm1;
    private CardView ukm1;
    final List<Ukm> ukms = new ArrayList<Ukm>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("ukm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    Ukm ukm = child.getValue(Ukm.class);
                    ukms.add(ukm);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ukm1 = findViewById(R.id.ukm1);

        ukm1.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener ukmListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Ukm ukm = dataSnapshot.getValue(Ukm.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public void onClick(View view) {
        if (view == ukm1) {
            Intent intent = new Intent(this, UKMActivity.class); //buat intent dari sini ke activity lain
            intent.putExtra("ukmTag", ukms.get(0)); //masukin objek ke intent
            startActivity(intent);
        }
    }
}
