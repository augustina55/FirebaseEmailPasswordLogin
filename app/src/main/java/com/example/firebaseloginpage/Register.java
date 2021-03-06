package com.example.firebaseloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText editemail,editpass,editConfirmpass;
    Button btnRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth=FirebaseAuth.getInstance();
        editemail=findViewById(R.id.EditEmail);
        editpass=findViewById(R.id.userPassword);
        editConfirmpass=findViewById(R.id.ConfirmPassword);

        btnRegister=findViewById(R.id.ButtonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editemail.getText()))
                    Toast.makeText(Register.this,"Please Enter Email Address",Toast.LENGTH_LONG).show();
                else if(TextUtils.isEmpty(editpass.getText()))
                    Toast.makeText(Register.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                else if(TextUtils.isEmpty(editConfirmpass.getText()))
                    Toast.makeText(Register.this,"Please Enter Confirm password",Toast.LENGTH_LONG).show();
                else
                    CreateEmailPassword(editemail.getText().toString().trim(),editpass.getText().toString().trim());


            }
        });

    }
    private void CreateEmailPassword(String email,String pass){
        //Create USer Email and Password
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Account Created1
                            Toast.makeText(Register.this,"Account Created!, Please login with email and password",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this,LoginPage.class));
                            finish();
                        }
                        else
                            Toast.makeText(Register.this,"Something went wrong ",Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this,"Something went wrong "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}