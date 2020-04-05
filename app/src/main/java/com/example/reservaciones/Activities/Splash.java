package com.example.reservaciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.reservaciones.R;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Splash extends AppCompatActivity {

    GifImageView gif_animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gif_animation = (GifImageView) findViewById(R.id.gif);


        try {
            InputStream inputStream = getAssets().open("Gif_splash.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gif_animation.setBytes(bytes);
            gif_animation.startAnimation();
        } catch (IOException ex) {

        }

        SharedPreferences preferences;
        preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usu = preferences.getString("nom_usuario","");
        String pass = preferences.getString("contra","");

        if (usu.isEmpty() || pass.isEmpty()){

            //Espera 4 segundos para ir al siguiente activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Splash.this.startActivity(new Intent(Splash.this,MainActivity.class));
                    Splash.this.finish();
                }
            },4000);

        }else{

            //Espera 4 segundos para ir al siguiente activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Splash.this.startActivity(new Intent(Splash.this,PrincipalActivity.class));
                    Splash.this.finish();
                }
            },4000);

        }


    }
}
