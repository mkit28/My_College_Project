package com.example.waterleveltracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText editName, editEmail, editPass, editConfPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPassword);
        editConfPass = findViewById(R.id.editConfPassword);
        Button btnSignUp = findViewById(R.id.btnSingUp);
        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String pass = editPass.getText().toString();
                String passConf = editConfPass.getText().toString();

                if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || passConf.isEmpty()){

                    if(pass.length() < 6){
                        Toast.makeText(SignUpActivity.this, "Please Enter at least 6 digits password", Toast.LENGTH_LONG).show();
                        if(!pass.equals(passConf)){
                            Toast.makeText(SignUpActivity.this, "Pssword Mismatch", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        return;
                    }

                    Toast.makeText(SignUpActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }else{


                    mAuth.createUserWithEmailAndPassword(email, pass
                    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this, "Something want wrong..", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }


            }
        });


    }
}