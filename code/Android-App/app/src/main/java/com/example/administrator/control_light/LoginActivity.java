package com.example.administrator.control_light;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {
    EditText taikhoan,matkhau;
    CheckBox check;
    Button dangnhap;
    ImageView send,info;
    Processreaddata mProcessreaddata;
    //Processsavelogin mProcesssavelogin;
    public static final String MyPREFERENCES;
    private MediaPlayer playlogin, playcheck;

    static {
        MyPREFERENCES = "MyPrefs";
    }

    String userLogin = "NMNhip";
    String passLogin = "nhip@123";
    private String simpleFileName = "note.txt"; String checkk;
    String data;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        taikhoan = findViewById(R.id.edtTK);matkhau = findViewById(R.id.edtMK);
        check = findViewById(R.id.chkDN);
        dangnhap = findViewById(R.id.btnDN);
        send = findViewById(R.id.sendmail);
        info = findViewById(R.id.getinfo);
        playlogin = MediaPlayer.create(this, R.raw.login);
        playcheck = MediaPlayer.create(this, R.raw.check);
        setTitle(R.string.activity_title);  // ĐẶT TIÊU ĐỀ CHO ACTIVITY MÀ KHÔNG TRÙNG VỚI APP-NAME
        mProcessreaddata=new Processreaddata();
        mProcessreaddata.execute();
        //Toast.makeText(LoginActivity.this, userLogin + " " + passLogin, Toast.LENGTH_SHORT).show();
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    playlogin.start();
                    if (check.isChecked()) {
                        if ((taikhoan.getText().toString().equals(userLogin) && matkhau.getText().toString().equals(passLogin)) ) {
                            data = taikhoan.getText().toString() + " " + matkhau.getText().toString() + " " + "1";    // CÓ CHECK
                            playcheck.start();
                            saveData();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            //saveData(taikhoan.getText().toString(), matkhau.getText().toString());
                        }
                        else {Toast.makeText(LoginActivity.this, "Thông tin đăng nhập không đúng !", Toast.LENGTH_SHORT).show();}
                    } else {
                        if ((taikhoan.getText().toString().equals(userLogin) && matkhau.getText().toString().equals(passLogin)) ) {
                            //taikhoan.setText(" ");matkhau.setText(" ");
                            data = taikhoan.getText().toString() + " " + matkhau.getText().toString() + " " + "0";    // KHÔNG CHECK
                            saveData();
                            taikhoan.setText("");
                            matkhau.setText("");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else
                            Toast.makeText(LoginActivity.this, "Thông tin đăng nhập không đúng !", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    playlogin.start();
                    Intent intent = new Intent(LoginActivity.this, mail.class);
                    startActivity(intent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlogin.start();
                Intent intent = new Intent(LoginActivity.this, getinfo.class);
                startActivity(intent);


            }
        });


    }

    @SuppressLint("StaticFieldLeak")
    private class Processreaddata extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {
            readData();
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }

    private void saveData() {
        try {
            FileOutputStream out = this.openFileOutput(simpleFileName, MODE_PRIVATE);
            out.write(data.getBytes());
            out.close();
            //Toast.makeText(this,"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void readData() {
        try {
            String[] get;
            FileInputStream in = this.openFileInput(simpleFileName);
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String s= br.readLine();
            get = s.split("\\s");
            checkk = get[2];
            //Toast.makeText(this,"Error:"+ checkk,Toast.LENGTH_SHORT).show();
            switch (checkk) {
                case "1":
                    taikhoan.setText(get[0]);
                    matkhau.setText(get[1]);
                    userLogin = get[0];
                    passLogin = get[1];
                    check.setChecked(true);
                    break;
                case "3":
                    userLogin = get[0];
                    passLogin = get[1];
                    break;
                default:

                    check.setChecked(false);
                    break;
            }

        } catch (Exception e) {
            //Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


}
