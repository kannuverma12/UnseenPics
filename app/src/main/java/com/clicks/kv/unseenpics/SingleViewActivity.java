package com.clicks.kv.unseenpics;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.clicks.kv.R;
import com.clicks.kv.glide.DisplayImage;
import com.clicks.kv.glide.ImageModel;

import java.io.IOException;

import static com.clicks.kv.unseenpics.Constants.BUCKET_NAME;
import static com.clicks.kv.unseenpics.Constants.KEY;

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
        //imageView.setImageResource(imageAdapter.mThumbIds[position]);

        DisplayImage.getInstance().displayImageForUser(getApplicationContext(), imageView, KEY, position+1);

        //decodeSampledBitmapFromResource(getApplicationContext().getResources(),position+1, 100,100);

        button = (Button)findViewById(R.id.set_wallpaper);

        imageView = (ImageView)findViewById(R.id.SingleView);

        wallpaperManager  = WallpaperManager.getInstance(getApplicationContext());

        //bitmapDrawable = (BitmapDrawable) imageView.getDrawable();

        //Bitmap bitmap = DisplayImage.getInstance().getBitmap();


        //bitmap1 = bitmapDrawable.getBitmap();



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

    public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }
}
