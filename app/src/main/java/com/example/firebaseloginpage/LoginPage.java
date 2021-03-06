package com.example.firebaseloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    EditText editemail,editpass;
    Button LoginBtn;
    CheckBox checkbox;
    TextView forgotPass;
    TextView registerText;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        forgotPass=findViewById(R.id.forgotPass);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,ForgotPassword.class));
                finish();
            }
        });
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        registerText=findViewById(R.id.register);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,Register.class));
                finish();
            }
        });

        editemail=findViewById(R.id.EditEmail);
        editpass=findViewById(R.id.EditPassword);

        checkbox=findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox.isChecked()) {
                    editpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    checkbox.setText("Hide password");
                }else {
                    editpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    checkbox.setText("Show Password");
                }
            }
        });
        LoginBtn=findViewById(R.id.btnlogin);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                progressDialog.setMessage("Please wait!");
                if(TextUtils.isEmpty(editemail.getText()))
                    Toast.makeText(LoginPage.this,"Please enter valid Email Address",Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(editpass.getText()))
                    Toast.makeText(LoginPage.this,"Please Password",Toast.LENGTH_SHORT).show();
                else
                    Login(editemail.getText().toString().trim(),editpass.getText().toString().trim());
                progressDialog.dismiss();
            }
        });
    }
    private void Login(String email,String pass){
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Login Successfull
                            startActivity(new Intent(LoginPage.this,HomePage.class));
                            finish();
                        }else{
                            //Login Failed
                            Toast.makeText(LoginPage.this,"Invalid Email and Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}