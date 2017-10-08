package com.example.risabhmishra.facultylocator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class faculty_login extends AppCompatActivity {
    private static final String TAG = "TAG";
    TextView head;
    EditText femail,fpass;
    Button login,signup;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mprogressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(faculty_login.this,MainActivity.class));
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        head = (TextView)findViewById(R.id.head_fac_login);
        femail = (EditText)findViewById(R.id.et_fac_email);
        fpass = (EditText)findViewById(R.id.et_fac_pass);
        login = (Button)findViewById(R.id.bu_fac_login);
        signup = (Button)findViewById(R.id.bu_fac_signup);

        login.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
OnLogin();
            }
        });

        signup.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
startActivity(new Intent(faculty_login.this,Register.class));
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void OnLogin() {

        final String email = femail.getText().toString();
        final String password = fpass.getText().toString();


        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(faculty_login.this,"Fields Are Empty",Toast.LENGTH_LONG).show();
        }
        else{

            mprogressdialog.setMessage("Signing In");
            mprogressdialog.show();
            mprogressdialog.setCanceledOnTouchOutside(false);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(faculty_login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(faculty_login.this, MainActivity.class));
                        mprogressdialog.dismiss();
                    } else if (!task.isSuccessful()) {
                        Toast.makeText(faculty_login.this, "Sign In Failed!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
