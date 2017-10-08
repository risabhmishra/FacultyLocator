package com.example.risabhmishra.facultylocator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
TextView reg_head;
    EditText email,pass,cpass,name;
    Button reg;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthstatelistener;
    private ProgressDialog mprogressdialog;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

    reg_head = (TextView)findViewById(R.id.tx_signup);
        email = (EditText)findViewById(R.id.et_su_email);
        pass = (EditText)findViewById(R.id.et_su_pass);
        cpass = (EditText)findViewById(R.id.et_su_cpass);
        reg = (Button)findViewById(R.id.bu_register);
        name = (EditText)findViewById(R.id.et_fac_name);


        mprogressdialog = new ProgressDialog(this);

        mAuthstatelistener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(Register.this, MainActivity.class));
                }
            }
        };

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
OnSignup();
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

    public void OnSignup(){
        final String email1 = email.getText().toString();
        final String password = pass.getText().toString();
        final String cpassword = cpass.getText().toString();
        final String name1 = name.getText().toString();
        if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cpassword) ||TextUtils.isEmpty(name1) )
        {
            Toast.makeText(Register.this,"Fields are Empty",Toast.LENGTH_LONG).show();
        }
       else if(!password.equals(cpassword)) {
            Toast.makeText(Register.this, "Password and Confirm Password don't match!", Toast.LENGTH_LONG).show();
        }
        else {

            mprogressdialog.setMessage("Signing Up");
            mprogressdialog.show();
            mprogressdialog.setCanceledOnTouchOutside(false);

            mAuth.createUserWithEmailAndPassword(email1, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "Successfully Signed Up", Toast.LENGTH_LONG).show();
                        upload();
                    } else if (!task.isSuccessful()) {
                        Toast.makeText(Register.this, "Signing Up Failed"+task.getException(), Toast.LENGTH_LONG).show();

                        mprogressdialog.dismiss();
                    }
                }
            });
        }
    }

    private void upload() {
        final String email2 = email.getText().toString();
        final String name2 = name.getText().toString();
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        String curr = current.getUid();

        mDatabase = database.getReference().child("Faculty").child(curr);

        HashMap<String,String> data = new HashMap<>();
        data.put("Faculty Name",name2);
        data.put("Faculty Email",email2);
        data.put("Profile_pic","https://firebasestorage.googleapis.com/v0/b/faculty-locator.appspot.com/o/Srmseal.png?alt=media&token=572b9489-f0d7-46e8-b6c0-43470510ec77");
        data.put("Faculty Id","id");
        data.put("Department","Department");
        data.put("Building","Building");
        data.put("Room No","Room No.");
        mDatabase.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Data Updated Succesfully", Toast.LENGTH_LONG).show();
                    mprogressdialog.dismiss();
                }
                else {
                    Toast.makeText(Register.this, "Data Update Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
