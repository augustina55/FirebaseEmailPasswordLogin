package com.example.firebaseloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class ForgotPassword extends AppCompatActivity {

    EditText resetEmail;
    Button btnSend;
    private FirebaseAuth mAuth;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        pd=new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();
        resetEmail=(EditText)findViewById(R.id.resetEmail);
        btnSend=(Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(resetEmail.getText().toString())){
                    resetEmail.setError("Please Enter Email id");
                }else{
                    ResetLink();
                }
            }
        });
    }
    private void ResetLink(){
        pd.show();
        pd.setMessage("Please wait");
        mAuth.sendPasswordResetEmail(resetEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            // LAUNCH activity after certain time period
                            new Timer().schedule(new TimerTask(){
                                public void run() {
                                    ForgotPassword.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            mAuth.signOut();
                                            Intent mainIntent =  new Intent(ForgotPassword.this, LoginPage.class);
                                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(mainIntent);
                                            finish();
                                            Toast.makeText(ForgotPassword.this, "Please check your mail for reset link.",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }, 8000);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgotPassword.this, "Oops!! "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        pd.dismiss();
    }
}