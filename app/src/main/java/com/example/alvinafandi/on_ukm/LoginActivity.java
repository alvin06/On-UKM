package com.example.alvinafandi.on_ukm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alvinafandi.on_ukm.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.masuk);
        editTextEmail = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        user = new User();

        if(firebaseAuth.getCurrentUser() != null) {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                                user = dataSnapshot1.getValue(User.class);
//                            }

                    user = dataSnapshot.getValue(User.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            finish();
            startActivity(new Intent(getApplicationContext(), UkmHomeActivity.class));
        }

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogin) {
            loginAccount();
        }
    }

    private void loginAccount() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();

            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();

            return;
        }

        progressDialog.setMessage("Login Account....");
        progressDialog.show();



        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                                user = dataSnapshot1.getValue(User.class);
//                            }

                            user = dataSnapshot.getValue(User.class);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    Intent intent = new Intent(getApplicationContext(), UkmHomeActivity.class);
                    intent.putExtra("userTag", user);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText((getApplicationContext()), task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
