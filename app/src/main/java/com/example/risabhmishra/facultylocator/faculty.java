package com.example.risabhmishra.facultylocator;

import android.widget.ImageView;

/**
 * Created by Risabh Mishra on 8/5/2017.
 */

public class faculty {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getLoct() {
        return loct;
    }

    public void setLoct(String loct) {
        this.loct = loct;
    }

    public String getLoct_room() {
        return loct_room;
    }

    public void setLoct_room(String loct_room) {
        this.loct_room = loct_room;
    }

    String name, id, dept, loct, loct_room;



    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
