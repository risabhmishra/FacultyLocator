package com.example.risabhmishra.facultylocator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class student_login extends AppCompatActivity {
TextView head;
    EditText semail,spass;
    Button login,signup;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthstatelistener;
    private ProgressDialog mprogressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        head = (TextView)findViewById(R.id.head_fac_login);
        semail = (EditText)findViewById(R.id.et_stu_email);
        spass = (EditText)findViewById(R.id.et_stu_pass);
        login = (Button)findViewById(R.id.bu_student_login);
        signup = (Button)findViewById(R.id.bu_stu_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnLogin();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(student_login.this,Register.class));
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthstatelistener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthstatelistener != null) {
            mAuth.removeAuthStateListener(mAuthstatelistener);
        }
    }

    public void OnLogin() {

        final String email = semail.getText().toString();
        final String password = spass.getText().toString();


        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(student_login.this,"Fields Are Empty",Toast.LENGTH_LONG).show();
        }
        else{

            mprogressdialog.setMessage("Signing In");
            mprogressdialog.show();
            mprogressdialog.setCanceledOnTouchOutside(false);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(student_login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(student_login.this, MainActivity.class));
                        mprogressdialog.dismiss();
                    } else if (!task.isSuccessful()) {
                        Toast.makeText(student_login.this, "Sign In Failed!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}


