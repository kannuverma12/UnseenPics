package com.clicks.kv.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.clicks.kv.R;

import static com.clicks.kv.unseenpics.Constants.BUCKET_NAME;
import static com.clicks.kv.unseenpics.Constants.PHOTO_TEMP_PATH;

/**
 * Created by ankit on 26-08-2017.
 */

public class DisplayImage {
    public Bitmap bitmap;

    private static DisplayImage displayImage = new DisplayImage();

    private DisplayImage() {}

    public static DisplayImage getInstance () { return displayImage;}

    public void displayImageForUser (final Context context, final ImageView imageView, String uniqueId, int id) {

        ImageModel imageModel = new ImageModel();
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
//                .into(new SimpleTarget<Bitmap>() {
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        imageView.setImageBitmap(decodeSampledBitmapFromResource(imageModel.getLocalPath(), uid, 100, 100));
//                    }
//                });


//        try {
//            bitmap = Glide
//                    .with(context)
//                    .asBitmap()
//                    .load(imageModel)
//                    .submit()
//                    .get();
//        }catch(Exception e){
//            e.printStackTrace();
//        }

    }

    public Bitmap getBitmap() {
        return bitmap;
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
}
