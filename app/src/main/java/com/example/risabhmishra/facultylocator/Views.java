package com.example.risabhmishra.facultylocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Views extends AppCompatActivity {
SearchView sv;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Faculty");
    ArrayList<faculty> faculti;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_view);
         faculti = new ArrayList<>();
 sv = (SearchView)findViewById(R.id.search_view);
        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_view);
 rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

         adapter = new MyAdapter(this, getFaculties());
        rv.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {


                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private ArrayList<faculty> getFaculties() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    faculty fac = new faculty();
                    fac.setId(ds.child("Faculty Id").getValue().toString());
                    fac.setName(ds.child("Faculty Name").getValue().toString());
                    fac.setDept(ds.child("Department").getValue().toString());
                    fac.setImg(ds.child("Profile_pic").getValue().toString());
                    fac.setLoct(ds.child("Building").getValue().toString());
                    fac.setLoct_room(ds.child("Room No").getValue().toString());
                    faculti.add(fac);
                   adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


return faculti;
    }
}
