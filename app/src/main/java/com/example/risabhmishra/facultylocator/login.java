package com.example.risabhmishra.facultylocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
TextView tv_head;
    Button f_login,s_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_head = (TextView)findViewById(R.id.tv_head_login);
        f_login = (Button)findViewById(R.id.bu_faculty_login);
        s_login = (Button)findViewById(R.id.bu_student_login);

        f_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,faculty_login.class));
            }
        });
    s_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(login.this,student_login.class));
        }
    });
    }


}
