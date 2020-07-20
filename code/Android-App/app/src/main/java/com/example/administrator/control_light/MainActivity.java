package com.example.administrator.control_light;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;

import org.eclipse.paho.client.mqttv3.IMqttToken;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class MainActivity extends AppCompatActivity {
    ImageView khuvucI,khuvucII;
    Button doitaikhoan;
    private MqttAndroidClient mqttAndroidClient;
    private MediaPlayer playchoose;
    private int connect = 0;
    Processpublish32 mProcesspublish32;
    Processpublish8266 mProcesspublish8266;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        khuvucI =  findViewById(R.id.imgbtnkv1);
        khuvucII =  findViewById(R.id.imgbtnkv2);
        doitaikhoan =  findViewById(R.id.btnDTK);
        playchoose = MediaPlayer.create(this, R.raw.login);


        //Log.w("create", "oncreate CHONKHUVUC");
        doitaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playchoose.start();
                Intent intent = new Intent(MainActivity.this, Doitaikhoan.class);
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
       // Log.w("onResume", "onResume CHONKHUVUC");
        xuly();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        playchoose.stop();
        playchoose.release();
        Log.w("ondestroy", "ondestroy CHONKHUVUC");

        finish();


    }
    protected void onStop() {
        super.onStop();
        try {
            //mqttAndroidClient.unregisterResources();
            //mqttAndroidClient.close();
            if( mqttAndroidClient != null && mqttAndroidClient.isConnected()) {
                mqttAndroidClient.unregisterResources();
                mqttAndroidClient.close();
                mqttAndroidClient.disconnect();
                mqttAndroidClient = null;
                Log.w("DISCONNECT", "ĐISCONECT CHONKHUVUC");
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }




    }
    @Override
    protected void onPause() {
        super.onPause();
        //Log.w("onPause", "onPause CHONKHUVUC");



    }
    private void subscribeToTopic32() {
        try {
            mqttAndroidClient.subscribe("dataesp32", 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Log.w("Mqtt","connected 32!");
                    //publisher32("connected");    // SAU KHI KẾT NỐI - SUB XONG - CẦN PUBLISH ĐỂ KIỂM TRA

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Log.w("Mqtt", "Subscribed fail!");
                }
            });

        } catch (MqttException ex) {
            System.err.println("Exception whilst subscribing");
            ex.printStackTrace();
        }
    }
    private void publisher32() {
        String clientId = MqttClient.generateClientId();    // Client ID của thiết bị này
        //String serverUri32 = "tcp://m12.cloudmqtt.com:17168"; CLOUDMQTT BROKER
        String serverUri32 = "tcp://10.11.15.162:1883";
        mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), serverUri32, clientId, new MemoryPersistence());

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setKeepAliveInterval(300); // thời gian ping tối đa đến server
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setConnectionTimeout(240000);
        mqttConnectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        try {


                IMqttToken token = mqttAndroidClient.connect(mqttConnectOptions);
                token.setActionCallback(new IMqttActionListener() {
                    static final String TAG = "";

                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        // We are connected



                        subscribeToTopic32();

                        byte[] encodedPayload = new byte[1024];
                        try {
                            encodedPayload = "getolddata32".getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        MqttMessage message = new MqttMessage(encodedPayload);
                        try {
                            mqttAndroidClient.publish("dkesp32", message);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }

                        //Log.d(TAG, "kết nối CHONKHUVUC");


                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                        //Log.d(TAG, "onFailure");
                        Toast.makeText(MainActivity.this, "Không thể kết nối!", Toast.LENGTH_SHORT).show();

                    }
                });

        } catch (MqttException e) {
            e.printStackTrace();

        }

    }

    private void subscribeToTopic8266() {
        try {
            mqttAndroidClient.subscribe("dataesp8266", 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //Log.w("Mqtt","connected laprap!");
                    //publisher8266("connected");  // PUBLISH KIỂM TRA

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    //Log.w("Mqtt", "Subscribed fail!");
                }
            });

        } catch (MqttException ex) {
            System.err.println("Exception whilst subscribing");
            ex.printStackTrace();
        }
    }

    private void publisher8266() {
        String clientId = MqttClient.generateClientId();   // Client ID của thiết bị này
        //String serverUri32 = "tcp://m15.cloudmqtt.com:10432";
        String serverUri8266 = "tcp://10.11.15.173:1883";
        mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), serverUri8266, clientId, new MemoryPersistence());
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setKeepAliveInterval(500);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setConnectionTimeout(60);
        mqttConnectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);

        try {
            IMqttToken token = mqttAndroidClient.connect(mqttConnectOptions);
            token.setActionCallback(new IMqttActionListener() {
                static final String TAG = "";

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    //disconnectedBufferOptions.setBufferEnabled(true);
                    //disconnectedBufferOptions.setBufferSize(100);
                    //disconnectedBufferOptions.setPersistBuffer(false);
                    //disconnectedBufferOptions.setDeleteOldestMessages(false);
                    //mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    // We are connected
                    //Log.d(TAG, "onSuccess");
                    subscribeToTopic8266();
                    byte[] encodedPayload = new byte[1024];
                    try {
                        encodedPayload = "getolddata8266".getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    MqttMessage message = new MqttMessage(encodedPayload);
                    try {
                        mqttAndroidClient.publish("dkesp8266", message);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //delay();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    //Log.d(TAG, "onFailure");
                    Toast.makeText(MainActivity.this, "Không thể kết nối!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();

        }


    }


    private void xuly() {
        new Thread() {
            @Override
            public void run() {
                //delay2();
                //try {
                    khuvucI.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playchoose.start();

                            mProcesspublish32=new Processpublish32();
                            mProcesspublish32.execute();
                            //delay();

                            Intent intent = new Intent(MainActivity.this, KhuvucIActivity.class);
                            startActivity(intent);


                        }
                    });

                    khuvucII.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playchoose.start();

                            mProcesspublish8266=new Processpublish8266();
                            mProcesspublish8266.execute();
                            //delay();
                            Intent intent = new Intent(MainActivity.this, KhuvucIIActivity.class);
                            //Bundle bundle = new Bundle();
                            //bundle.putString(TITLE,edtTitle.getText().toString());
                           // bundle.putString(DESCRIPTION,edtDescription.getText().toString());
                            //intent.putExtra(BUNDLE,bundle);

                            startActivity(intent);


                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                //} catch (final Exception ex) {
                //    Log.i("---","Exception in thread");
                //}
            }
        }.start();

    }


    @SuppressLint("StaticFieldLeak")
    private class Processpublish32 extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {
            publisher32();
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class Processpublish8266 extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {
            publisher8266();

            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }

    protected void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }

    }
}
