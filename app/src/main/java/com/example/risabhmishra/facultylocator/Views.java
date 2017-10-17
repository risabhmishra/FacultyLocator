package com.example.risabhmishra.facultylocator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    ArrayList<faculty> teacherList;
    ArrayList<faculty> searchList;
    MyAdapter adapter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_view);
        teacherList = new ArrayList<>();
        sv = (SearchView) findViewById(R.id.search_view);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        searchList=new ArrayList<>();
        adapter = new MyAdapter(this, getFaculties());
        rv.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(query.contentEquals("")){
                    setNewAdapter(teacherList);
                }else {
                    setSearchList(query);
                    setNewAdapter(searchList);
                }

                return false;

            }

            @Override
            public boolean onQueryTextChange(String query) {

                if(query.contentEquals("")){
                    setNewAdapter(teacherList);
                }else {
                    setSearchList(query);
                    setNewAdapter(searchList);
                }

                return false;

            }
        });
    }

    private ArrayList<faculty> getFaculties() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    faculty fac = new faculty();
                    fac.setId(ds.child("Faculty Id").getValue().toString());
                    fac.setName(ds.child("Faculty Name").getValue().toString());
                    fac.setDept(ds.child("Department").getValue().toString());
                    fac.setImg(ds.child("Profile_pic").getValue().toString());
                    fac.setLoct(ds.child("Building").getValue().toString());
                    fac.setLoct_room(ds.child("Room No").getValue().toString());
                    teacherList.add(fac);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return teacherList;
    }

    private void setNewAdapter(ArrayList<faculty> list) {
        adapter=new MyAdapter(this,list);
        rv.setAdapter(adapter);
    }

    private void setSearchList(CharSequence charSequence) {
        searchList.clear();
        charSequence=charSequence.toString().toLowerCase();
        ArrayList<faculty> teacherList = getFaculties();
        for(faculty user:teacherList){
            if(user.name.toLowerCase().contains(charSequence)){
                searchList.add(user);
                //Toast.makeText(this, ""+user.name, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
