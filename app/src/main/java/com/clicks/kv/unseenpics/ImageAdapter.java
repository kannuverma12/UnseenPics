package com.clicks.kv.unseenpics;


import android.content.Context;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.clicks.kv.R;
import com.clicks.kv.glide.DisplayImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.clicks.kv.unseenpics.Constants.KEY;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    //change the size equal to images in s3 bucket
    public Integer[] mThumbIds = new Integer[10];
//    {
//
//            R.drawable.pic1, R.drawable.pic2,
//            R.drawable.pic3, R.drawable.pic4,
//
//            R.drawable.pic7,
//            R.drawable.pic9,
//            R.drawable.pic11
//    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        DisplayImage.getInstance().displayImageForUser(mContext, imageView, KEY, position+1);
        return imageView;
    }


}
