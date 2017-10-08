package com.example.risabhmishra.facultylocator;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class data extends AppCompatActivity {
TextView head;
    EditText e1,e2,e3,e4,e5;
    Button submit;
    String fa_id,fa_name,fa_dept,fa_loct,fa_loct_room;
    String Image_uri;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        //mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        String curr = current.getUid();

        final DatabaseReference myRef = database.getReference("Faculty").child(curr);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Image_uri = dataSnapshot.child("Profile_pic").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        head = (TextView)findViewById(R.id.textView);
        e1 = (EditText)findViewById(R.id.et_fid);
        e2 = (EditText)findViewById(R.id.et_fname);
        e3 = (EditText)findViewById(R.id.et_dept);
        e4 = (EditText)findViewById(R.id.et_location);
        e5 = (EditText)findViewById(R.id.et_location_room);
        submit = (Button)findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fa_id = e1.getText().toString();
                fa_name = e2.getText().toString();
                fa_dept = e3.getText().toString();
                fa_loct = e4.getText().toString();
                fa_loct_room = e5.getText().toString();

                HashMap<String,String> faculty_data = new HashMap<String, String>();
                faculty_data.put("Faculty Id",fa_id);
                faculty_data.put("Faculty Name",fa_name);
                faculty_data.put("Department",fa_dept);
                faculty_data.put("Building",fa_loct);
                faculty_data.put("Room No",fa_loct_room);
                faculty_data.put("Profile_pic", Image_uri);

                myRef.setValue(faculty_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(data.this,"Successful",Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(data.this,"Failed",Toast.LENGTH_SHORT).show();
                   }
                    }
                });

            }
        });
    }
}
