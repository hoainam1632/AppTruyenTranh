package com.example.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ZoomImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        PhotoView photoView = findViewById(R.id.img_photo);
        Picasso.get().load("https://gamek.mediacdn.vn/zoom/700_438/2019/10/26/photo-1-1572060315190470419314.jpg")
                .into(photoView);
    }
}
