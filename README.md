# FirebaseEmailPasswordLogin
<b>Email and Password login using Firebase Authentication</b>


<b>Simple Login Page using Firebase Authentication</b><br/> 
1.Login Page<br/> 
2.Register Page <br/> 
3.Forgot Password <br/> 

<img src="ScreenShoot/ss.jpeg"/>


<b>Registration</b>
        
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Account Created 
                            
                        }                      
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Something went wrong
            }
        });
        
<b>Login using email and pass</b>

    mAuth=FirebaseAuth.getInstance();
    mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Login Successfull
                            
                        }else{
                            //Login Failed
                           
                        }
                    }
                });
                
<b>Reset/Forgot Password Link</b> 

    mAuth.sendPasswordResetEmail(resetEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //Reset password link will be shared to the Email address
                          
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               //Something went problem
            }
        });
