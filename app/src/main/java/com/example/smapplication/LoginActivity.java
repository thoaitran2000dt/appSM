package com.example.smapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailEt, passEt;
    Button btn_register, btn_login;
    CheckBox checkBox;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = findViewById(R.id.login_email_et);
        passEt = findViewById(R.id.login_password_et);
        checkBox = findViewById(R.id.login_checkbox);
        btn_register = findViewById(R.id.login_to_signup);
        btn_login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressbar_login);
        mAuth = FirebaseAuth.getInstance();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {
                    passEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                   // confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    passEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    // confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString();
                String pass = passEt.getText().toString();
                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(email)){
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               sendtoMain();
                           }else {
                               String error = task.getException().getMessage();
                               Toast.makeText(LoginActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendtoMain() {
        Intent intent = new Intent(LoginActivity.this, Splashscreen.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}