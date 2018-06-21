package com.example.alvinafandi.on_ukm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alvinafandi.on_ukm.classes.ukmTest;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UkmHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ukm_home);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ukm");
        databaseReference.keepSynced(true);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<ukmTest,ukmTestViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ukmTest, ukmTestViewHolder>(ukmTest.class, R.layout.ukm_row, ukmTestViewHolder.class, databaseReference) {

            @Override
            protected void populateViewHolder(final ukmTestViewHolder viewHolder, final ukmTest model, final int position) {
                viewHolder.setNamaUkm(model.getNamaUkm());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ukmTest ukm = model;
                        Intent intent = new Intent(UkmHomeActivity.this, UKMActivity.class); //buat intent dari sini ke activity lain
                        intent.putExtra("ukmTag", model); //masukin objek ke intent
                        startActivity(intent);
                    }
                });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ukmTestViewHolder extends RecyclerView.ViewHolder {
        static View view;
        public ukmTestViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setNamaUkm(String nama) {
            TextView namaUkm = (TextView)view.findViewById(R.id.namaUkm);
            namaUkm.setText(nama);
        }
//
//        public void setLogo(Context context, String image) {
//            ImageView logoUkm = (ImageView)view.findViewById(R.id.logoUkm);
//            Picasso.with(context).load(image).into(logoUkm);
//        }

    }
}
