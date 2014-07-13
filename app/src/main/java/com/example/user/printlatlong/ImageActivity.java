package com.example.user.printlatlong;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.user.printlatlong.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageActivity extends Activity {
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        Bundle extras = getIntent().getExtras();
        int pastFocusID=-1;
        if (extras != null) {
            pastFocusID = Integer.parseInt(extras.getString("pastFocusID"));
            Context context = this;
            ImageView imageViewVariable = (ImageView)findViewById(R.id.photoOfArtBig);
            Bitmap newImage = BitmapFactory.decodeResource(context.getResources(), getResources().getIdentifier("drawable/imageid" + Integer.toString(pastFocusID), null, getPackageName()));
            newImage = getRoundedCornerBitmap(newImage,50);
            imageViewVariable.setImageBitmap(newImage);
        }
    }
}
