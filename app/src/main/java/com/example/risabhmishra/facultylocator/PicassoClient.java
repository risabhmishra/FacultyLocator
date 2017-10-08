package com.example.risabhmishra.facultylocator;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Risabh Mishra on 8/5/2017.
 */

public class PicassoClient {
public static void DownloadImage(Context c, String url, ImageView img){
    if(url !=null && url.length()>0){
        Picasso.with(c).load(url).into(img);
    }
}

}
