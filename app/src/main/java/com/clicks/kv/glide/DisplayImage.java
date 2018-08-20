package com.clicks.kv.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.clicks.kv.R;
import java.util.concurrent.ExecutionException;

import static com.clicks.kv.unseenpics.Constants.BUCKET_NAME;


public class DisplayImage {
    public Bitmap bitmap;

    private static DisplayImage displayImage = new DisplayImage();

    private DisplayImage() {}

    public static DisplayImage getInstance () { return displayImage;}

    public void displayImageForUser (final Context context, final ImageView imageView, String uniqueId, int id) {

        final ImageModel imageModel = new ImageModel();
        uniqueId = uniqueId + id + ".jpg";
        final int uid = id;
        imageModel.setId(uniqueId);
        //imageModel.setLocalPath(PHOTO_TEMP_PATH);
        imageModel.setLocalPath(context.getFilesDir().getPath());
        imageModel.setBucketName(BUCKET_NAME);

        GlideApp.with(context)
                .setDefaultRequestOptions(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(R.drawable.ic_launcher_background)
                        .fitCenter())
                .asBitmap()
                .load(imageModel)
                .into(imageView);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //Looper.prepare();
                try {
                    bitmap = Glide
                            .with(context)
                            .asBitmap()
                            .load(imageModel)
                            .submit()
                            .get();

                } catch (final ExecutionException e) {
                    e.printStackTrace();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void dummy) {
                if (null != bitmap) {
                    // The full bitmap should be available here
                    //imageView.setImageBitmap(bitmap);
                    //Log.d(TAG, "Image loaded");
                };
            }
        }.execute();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
