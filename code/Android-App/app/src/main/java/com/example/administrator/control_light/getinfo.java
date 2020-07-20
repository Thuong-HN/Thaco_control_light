package com.example.administrator.control_light;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class getinfo extends AppCompatActivity {
    ImageView info;
    private MediaPlayer playchoose;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getinfo);
        info = findViewById(R.id.getinfo);
        playchoose = MediaPlayer.create(this, R.raw.login);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playchoose.start();
                Intent intent = new Intent(getinfo.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
