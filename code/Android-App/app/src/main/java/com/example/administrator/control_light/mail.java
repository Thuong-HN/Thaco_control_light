package com.example.administrator.control_light;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class mail extends AppCompatActivity {
    Button gui,quaylai;
    EditText noidung,chude;
    String gopy,chudegopy;
    private MediaPlayer playchoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        gui = findViewById(R.id.btnmail);
        quaylai = findViewById(R.id.btnquaylai);
        noidung = findViewById(R.id.edtnoidung);
        chude = findViewById(R.id.edtchude);
        playchoose = MediaPlayer.create(this, R.raw.login);
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playchoose.start();
                gopy=noidung.getText().toString();
                chudegopy=chude.getText().toString();
                sendEmail();

            }
        });
        quaylai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                playchoose.start();
                finish();

            }
        });
    }
    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        //intent.setData(Uri.parse("mailto:thuonghuynh.work@gmail.com"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"huynhngocthuong@thaco.com.vn"});
        intent.putExtra(Intent.EXTRA_SUBJECT, chudegopy);
        intent.putExtra(Intent.EXTRA_TEXT, gopy);

        try {
            startActivity(Intent.createChooser(intent, "Gửi Mail"));

        } catch (ActivityNotFoundException e) {
            Toast.makeText(this
                    , "Không tìm thấy ứng dụng Gmail...", Toast.LENGTH_SHORT).show();
        }

    }


   
}
