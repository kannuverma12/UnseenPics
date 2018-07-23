package com.clicks.kv.unseenpics;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class SingleViewActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    WallpaperManager wallpaperManager ;
    Bitmap bitmap1, bitmap2 ;
    DisplayMetrics displayMetrics ;
    int width, height;
    BitmapDrawable bitmapDrawable ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);


        button = (Button)findViewById(R.id.set_wallpaper);

        imageView = (ImageView)findViewById(R.id.SingleView);

        wallpaperManager  = WallpaperManager.getInstance(getApplicationContext());

        bitmapDrawable = (BitmapDrawable) imageView.getDrawable();

        bitmap1 = bitmapDrawable.getBitmap();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetScreenWidthHeight();

                SetBitmapSize();

                wallpaperManager = WallpaperManager.getInstance(SingleViewActivity.this);

                try {

                    wallpaperManager.setBitmap(bitmap2);

                    wallpaperManager.suggestDesiredDimensions(width, height);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


//        WebView adsDisplay = (WebView) findViewById(R.id.adsDisplay);
//        adsDisplay.getSettings().setJavaScriptEnabled(true);
//        adsDisplay.loadUrl("https://picsitis.wordpress.com/ad/");
    }

    public void GetScreenWidthHeight(){

        displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;

        height = displayMetrics.heightPixels;

    }

    public void SetBitmapSize(){

        bitmap2 = Bitmap.createScaledBitmap(bitmap1, width, height, false);

    }
}
