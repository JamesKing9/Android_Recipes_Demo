package se.hellsoft.textoverimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**Text覆盖在image上 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Glide.with(this)
                .load(R.drawable.first_photo)
                .into(((ImageView) findViewById(R.id.first_photo)));
//        ((ImageView) findViewById(R.id.first_photo)).setImageResource(R.drawable.first_photo); /*使用这个方式加载图片会造成内存溢出，因此使用glide框架来加载图片*/
        Bitmap firstPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.first_photo);
        setTextColorForImage((TextView) findViewById(R.id.first_text), firstPhoto);

        Glide.with(this)
                .load(R.drawable.second_photo)
                .into(((ImageView) findViewById(R.id.second_photo)));
//        ((ImageView) findViewById(R.id.second_photo)).setImageResource(R.drawable.second_photo);
        Bitmap secondPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.second_photo);
        setTextColorForImage(((TextView) findViewById(R.id.second_text)), secondPhoto);
    }

private void setTextColorForImage(final TextView textView, Bitmap bitmap) {
    Palette.from(bitmap)
            .generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch swatch = palette.getVibrantSwatch();
                    if (swatch == null && palette.getSwatches().size() > 0) {
                        swatch = palette.getSwatches().get(0);
                    }

                    int titleTextColor = Color.WHITE;
                    if (swatch != null) {
                        titleTextColor = swatch.getTitleTextColor();
                        titleTextColor = ColorUtils.setAlphaComponent(titleTextColor, 255);
                    }

                    textView.setTextColor(titleTextColor);
                }
            });
}
}
