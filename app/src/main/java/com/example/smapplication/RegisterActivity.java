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

public class RegisterActivity extends AppCompatActivity {

    EditText emailEt, passEt, confirm_pass;
    Button btn_register, btn_login;
    CheckBox checkBox;
    ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEt = findViewById(R.id.register_email_et);
        passEt = findViewById(R.id.register_password_et);
        confirm_pass = findViewById(R.id.register_confirmpassword_et);
        checkBox = findViewById(R.id.register_checkbox);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.signup_to_login);
        progressBar = findViewById(R.id.progressbar_register);
        mAuth = FirebaseAuth.getInstance();


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {
                    passEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    passEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString();
                String pass = passEt.getText().toString();
                String confirm_password = confirm_pass.getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass) || !TextUtils.isEmpty(confirm_password)){

                    if (pass.equals(confirm_password)){
                        progressBar.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    sendtoMain();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(RegisterActivity.this, "Password and Confirm Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void sendtoMain() {
        Intent intent = new Intent(RegisterActivity.this, Splashscreen.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            sendtoMain();
        }


    }
}