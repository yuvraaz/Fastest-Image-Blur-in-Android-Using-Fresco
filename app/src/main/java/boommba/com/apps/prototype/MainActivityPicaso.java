package boommba.com.apps.prototype;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import boommba.com.apps.prototype.utils.BlurImage;

public class MainActivityPicaso extends AppCompatActivity {

    private ImageView imageViewBackground;
    //static variables
    private int BLUR_PRECENTAGE = 50;
    private String IMAGE_URL = "http://behemppy.com/hotelshivasdream.com/astrokathmandu/api/users/system.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_picaso);
        //view assignment
        imageViewBackground = (ImageView) findViewById(R.id.iv_profilepic);


        //Configure target for
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageViewBackground.setImageBitmap(BlurImage.fastblur(bitmap, 1f, BLUR_PRECENTAGE));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                imageViewBackground.setImageResource(R.mipmap.ic_launcher);

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        imageViewBackground.setTag(target);
        Picasso.with(this)
                .load(IMAGE_URL)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(target);
    }
}
