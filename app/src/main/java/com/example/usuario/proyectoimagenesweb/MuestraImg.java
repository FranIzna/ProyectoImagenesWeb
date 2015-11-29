package com.example.usuario.proyectoimagenesweb;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;

public class MuestraImg extends Activity {
    private ImageView iv;
    private Bitmap bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagen);
        iv=(ImageView)findViewById(R.id.imageView);
        bm=getIntent().getExtras().getParcelable("img");
        iv.setImageBitmap(bm);
    }

}
