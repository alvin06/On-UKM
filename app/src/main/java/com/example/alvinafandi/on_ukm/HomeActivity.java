package com.example.alvinafandi.on_ukm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alvinafandi.on_ukm.classes.Ukm;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Ukm ukm1 = new Ukm("test1", "testUkm1", 0, "testKetua1", "besok pendaftaran");
    private Ukm ukm2 = new Ukm("test2", "testUkm2", 0, "testKetua2", "besok pendaftaran");
    private Ukm ukm3 = new Ukm("test3", "testUkm3", 0, "testKetua3", "besok pendaftaran");
    private Ukm ukm4 = new Ukm("test4", "testUkm4", 0, "testKetua4", "besok pendaftaran");

    private Button btnUkm1;
    private Button btnUkm2;
    private Button btnUkm3;
    private Button btnUkm4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnUkm1 = findViewById(R.id.btnUkm1);
        btnUkm2 = findViewById(R.id.btnUkm2);
        btnUkm3 = findViewById(R.id.btnUkm3);
        btnUkm4 = findViewById(R.id.btnUkm4);

        btnUkm1.setOnClickListener(this);
        btnUkm2.setOnClickListener(this);
        btnUkm3.setOnClickListener(this);
        btnUkm4.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == btnUkm1) {
            Intent intent = new Intent(this, UKMActivity.class); //buat intent dari sini ke activity lain
            intent.putExtra("ukmTag", ukm1); //masukin objek ke intent
            startActivity(intent);
        }
        else if (view == btnUkm2) {
            Intent intent = new Intent(this, UKMActivity.class);
            intent.putExtra("ukmTag", ukm2);
            startActivity(intent);
        }
        else if (view == btnUkm3) {
            Intent intent = new Intent(this, UKMActivity.class);
            intent.putExtra("ukmTag", ukm3);
            startActivity(intent);
        }
        else if (view == btnUkm4){
            Intent intent = new Intent(this, UKMActivity.class);
            intent.putExtra("ukmTag", ukm4);
            startActivity(intent);
        }
    }
}
