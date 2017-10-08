package com.example.risabhmishra.facultylocator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Risabh Mishra on 8/5/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView profile;
    TextView name,id,loct,dept,loct_room;
    ItemClickListener itemclicklistener;

    public MyHolder(android.view.View itemView) {
        super(itemView);

        this.profile = (ImageView)itemView.findViewById(R.id.Profile_pic);
        this.name = (TextView)itemView.findViewById(R.id.f_name) ;
        this.dept = (TextView)itemView.findViewById(R.id.f_dept);
        this.id = (TextView)itemView.findViewById(R.id.f_id);
        this.loct = (TextView)itemView.findViewById(R.id.f_loct);
        this.loct_room = (TextView)itemView.findViewById(R.id.f_loct_room);

itemView.setOnClickListener(this);
        }

    @Override
    public void onClick(View vie) {
this.itemclicklistener.ItemClick(vie,getLayoutPosition());

    }


    public void setItemClickListener(ItemClickListener ic){
        this.itemclicklistener=ic;
    }
}


