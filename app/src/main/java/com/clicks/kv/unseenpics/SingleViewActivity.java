package com.clicks.kv.unseenpics;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.clicks.kv.R;
import com.clicks.kv.glide.DisplayImage;
import java.io.IOException;

import static com.clicks.kv.unseenpics.Constants.KEY;

public class SingleViewActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    WallpaperManager wallpaperManager ;
    Bitmap bitmap1, bitmap2 ;
    DisplayMetrics displayMetrics ;
    int width, height;
    Drawable bitmapDrawable ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);

        DisplayImage.getInstance().displayImageForUser(getApplicationContext(), imageView, KEY, position+1);
        button = (Button)findViewById(R.id.set_wallpaper);
        wallpaperManager  = WallpaperManager.getInstance(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenWidthHeight();
                wallpaperManager = WallpaperManager.getInstance(SingleViewActivity.this);
                try {
                    wallpaperManager.setBitmap(DisplayImage.getInstance().getBitmap());
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

    public void getScreenWidthHeight(){
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

}