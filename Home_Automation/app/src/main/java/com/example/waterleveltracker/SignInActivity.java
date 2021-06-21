package com.example.waterleveltracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private Button btnSignIn;
    private EditText editEmail, editPass;
    private TextView tvForgotPass, tvNewUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        getallwidgtsIds();



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail.getText().toString();
                String pass = editPass.getText().toString();

                if(email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                startActivity(new Intent(SignInActivity.this, DashbordActivity.class));
                                finish();
                            }else{
                                Toast.makeText(SignInActivity.this, "Something want wrong..",Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                    });


                }

            }
        });



    }

    private void getallwidgtsIds() {

    btnSignIn = findViewById(R.id.btnSingIn);
    editEmail = findViewById(R.id.editEmailId);
    editPass = findViewById(R.id.editPassword);
    tvForgotPass = findViewById(R.id.tvForgotPass);
    tvNewUser = findViewById(R.id.tvNewUser);


    }
}