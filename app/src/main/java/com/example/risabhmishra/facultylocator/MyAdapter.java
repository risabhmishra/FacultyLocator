package com.example.risabhmishra.facultylocator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Risabh Mishra on 8/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable{

    Context ctx;
    ArrayList<faculty> faculties,filterlist;
    CustomFilter filter;

public MyAdapter(Context ctx,ArrayList<faculty> faculties){

    this.ctx = ctx;
    this.faculties=faculties;
    this.filterlist=faculties;
}
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
holder.name.setText(faculties.get(position).getName());
        holder.id.setText(faculties.get(position).getId());
               // holder.profile.setImageURI(Uri.parse((faculties.get(position).getImage())));
PicassoClient.DownloadImage(ctx,faculties.get(position).getImg(),holder.profile);

        // Picasso.with(ctx).load(faculties.get(position).getImage()).placeholder(R.id.Profile_pic).into();
                        holder.loct.setText(faculties.get(position).getLoct());
                                holder.dept.setText(faculties.get(position).dept);
                                        holder.loct_room.setText(faculties.get(position).loct_room);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void ItemClick(View v, int pos) {
               Intent intent = new Intent(ctx,Map_activity.class);
                intent.putExtra("Location",faculties.get(pos).getLoct());
                ctx.startActivity(intent);

            }

        });

    }

    @Override
    public int getItemCount() {

        return (faculties == null) ? 0 : faculties.size();

    }

    @Override
    public Filter getFilter() {
       if(filter==null){
           filter=new CustomFilter(filterlist,this);
       }

        return filter;
    }
}
