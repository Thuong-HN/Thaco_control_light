package com.example.administrator.control_light;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class Doitaikhoan extends AppCompatActivity {
    EditText Doitk,Doimk;
    Button btndoitk,btnhuy;
    String save;int doitk;
    private MediaPlayer playchoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doitaikhoan);
        playchoose = MediaPlayer.create(this, R.raw.login);
        Doitk = findViewById(R.id.edtDOITK);Doimk = findViewById(R.id.edtDOIMK);
        btndoitk = findViewById(R.id.btnXNDOI); btnhuy = findViewById(R.id.btnHUY);
        btndoitk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playchoose.start();
                if(!Doitk.getText().toString().equals("") && !Doimk.getText().toString().equals("")){
                    doitk=3;
                    //Toast.makeText(Doitaikhoan.this, "save", Toast.LENGTH_SHORT).show();
                    save =  Doitk.getText().toString() + " " + Doimk.getText().toString() + " " + doitk;
                    saveData();
                    Intent intent = new Intent(Doitaikhoan.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {Toast.makeText(Doitaikhoan.this, "Chưa nhập tài khoản hoặc mật khẩu !", Toast.LENGTH_SHORT).show();}

            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playchoose.start();
                //Intent intent = new Intent(Doitaikhoan.this, MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }

    private void saveData() {
        try {
            String simpleFileName = "note.txt";
            FileOutputStream out = this.openFileOutput(simpleFileName, MODE_PRIVATE);
            out.write(save.getBytes());
            out.close();
            //Toast.makeText(this,"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
