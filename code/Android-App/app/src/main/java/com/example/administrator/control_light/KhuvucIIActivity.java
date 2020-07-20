package com.example.administrator.control_light;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class KhuvucIIActivity extends AppCompatActivity {
    /**
     *
     */

    ToggleButton khuvuc1,khuvuc2, khuvuc3, khuvuc4, khuvuc5, khuvuc6, khuvuc7, khuvuc8,khuvuc9,khuvuc10,khuvucall;
    ImageView hengio;
    private  int flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9,flag10,flag11,flag12,flag13,flag14,flag15,flag16,flag17,flag18,flag19,flag20,flag21,flag22;
    private MqttAndroidClient mqttAndroidClient;
    Processnhandulieu82 mProcessnhandulieu82;

    Processguinutbam82 mProcessguinutbam82;
    connectmqttlr mconnectmqtt;
    private MediaPlayer playoff, playon, playchoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuvuc_ii);

        khuvuc1 = findViewById(R.id.btn2kv1);
        khuvuc2 = findViewById(R.id.btn2kv2);
        khuvuc3 = findViewById(R.id.btn2kv3);
        khuvuc4 = findViewById(R.id.btn2kv4);
        khuvuc5 = findViewById(R.id.btn2kv5);
        khuvuc6 = findViewById(R.id.btn2kv6);
        khuvuc7 = findViewById(R.id.btn2kv7);
        khuvuc8 = findViewById(R.id.btn2kv8);
        khuvuc9 = findViewById(R.id.btn2kv9);
        khuvuc10 = findViewById(R.id.btn2kv10);
        khuvucall = findViewById(R.id.btn2ALLlr);

        hengio = findViewById(R.id.alarm_LR);
        //Log.w("oncreate", "start oncreate laprap");
        playoff = MediaPlayer.create(this, R.raw.off);
        playon = MediaPlayer.create(this, R.raw.on);
        playchoose = MediaPlayer.create(this, R.raw.login);
        hengio.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                playchoose.start();
                //Intent intent = new Intent(KhuvucIIActivity.this, Arlarm_Laprap.class);
                //startActivity(intent);
                Toast.makeText(KhuvucIIActivity.this, "Chức năng hẹn giờ vô hiệu hóa !", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //batdau.setVisibility(View.INVISIBLE);ketthuc.setVisibility(View.INVISIBLE); giobatdau.setVisibility(View.INVISIBLE);
        //textbd.setVisibility(View.INVISIBLE);texkt.setVisibility(View.INVISIBLE);   gioketthuc.setVisibility(View.INVISIBLE);
        //Log.w("onstart", "onstart  laprap");
        mconnectmqtt=new connectmqttlr();
        mconnectmqtt.execute();

    }

   @Override
    protected void onResume() {
        super.onResume();
       //delay1();
       final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               // Do something after 5s = 5000ms
               mProcessnhandulieu82=new Processnhandulieu82();
               mProcessnhandulieu82.execute();
           }
       }, 100);

       mProcessguinutbam82=new Processguinutbam82();
       mProcessguinutbam82.execute();
       //Log.w("onresume", "onresume  laprap");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
       // Log.w("Restart", "onRestart laprap");

    }
    @Override
    protected void onStop() {
        super.onStop();
       // Log.w("onstop", " onstop laprap");
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
       // Log.w("onpause", "dis onpause laprap");
        try {
            if(mqttAndroidClient.isConnected()&& mqttAndroidClient !=null) {
                mqttAndroidClient.unregisterResources();
                mqttAndroidClient.close();
                mqttAndroidClient.disconnect();
                mqttAndroidClient = null;
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       // Log.w("ondetroy", "dis ondetroy laprap");
        playchoose.stop();
        playchoose.release();
        finish();

    }


    private void laydulieuesp8266() {

                    mqttAndroidClient.setCallback(new MqttCallbackExtended() {
                        @Override
                        public void connectComplete(boolean b, String s) {
                            //Log.w("GETDATA", "GETDATA LAPRAP");

                        }

                        @Override
                        public void connectionLost(Throwable throwable) {
                            delay();
                            //Log.w("NOT CONNECT", "NOT Connected laprap");
                            //connectmqtt();
                        }

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void messageArrived(String topic, MqttMessage message) {

                            String s = new String(message.getPayload());
                            String[] mes = s.split("\\s");  // cách nhau dấu space

                           // Log.w("Rec", "messArrived laprap"+ " " + Arrays.toString(mes));
                            try {
                                if(mes[0].equals("dknutLR")){


                                    if (mes[1].equals("1")) {
                                        try {
                                            flag1 = 1;

                                            khuvuc1.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc1.setBackgroundResource(R.drawable.button2v2);
                                        flag1 = 0;
                                    }

                                    if (mes[2].equals("1")) {
                                        try {
                                            flag3 = 1;

                                            khuvuc2.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc2.setBackgroundResource(R.drawable.button2v2);
                                        flag3 = 0;
                                    }


                                    if (mes[3].equals("1")) {
                                        try {
                                            flag5 = 1;

                                            khuvuc3.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc3.setBackgroundResource(R.drawable.button2v2);
                                        flag5 = 0;
                                    }

                                    if (mes[4].equals("1")) {
                                        try {
                                            flag7 = 1;

                                            khuvuc4.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc4.setBackgroundResource(R.drawable.button2v2);
                                        flag7 = 0;
                                    }

                                    if (mes[5].equals("1")) {
                                        try {
                                            flag9 = 1;

                                            khuvuc5.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc5.setBackgroundResource(R.drawable.button2v2);
                                        flag9 = 0;
                                    }

                                    if (mes[6].equals("1")) {
                                        try {
                                            flag11 = 1;

                                            khuvuc6.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc6.setBackgroundResource(R.drawable.button2v2);
                                        flag11 = 0;
                                    }

                                    if (mes[7].equals("1")) {
                                        try {
                                            flag13 = 1;

                                            khuvuc7.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc7.setBackgroundResource(R.drawable.button2v2);
                                        flag13 = 0;
                                    }

                                    if (mes[8].equals("1")) {
                                        try {
                                            flag15 = 1;

                                            khuvuc8.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc8.setBackgroundResource(R.drawable.button2v2);
                                        flag15 = 0;
                                    }

                                    if (mes[9].equals("1")) {
                                        try {
                                            flag17 = 1;

                                            khuvuc9.setBackgroundResource(R.drawable.button1v2);
                                            Log.i("TEST", "9----------------------");
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc9.setBackgroundResource(R.drawable.button2v2);
                                        flag17 = 0;
                                    }

                                    if (mes[10].equals("1")) {
                                        try {
                                            flag19 = 1;
                                            Log.i("TEST", "10----------------------");
                                            khuvuc10.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvuc10.setBackgroundResource(R.drawable.button2v2);
                                        flag19 = 0;
                                    }

                                    if (mes[11].equals("1")) {
                                        try {
                                            flag21 = 1;
                                            Log.i("TEST", "ALL---------------------");
                                            khuvucall.setBackgroundResource(R.drawable.button1v2);
                                        } catch (final Exception ex) {
                                            Log.i("---", "Exception in thread");
                                        }
                                    } else {
                                        khuvucall.setBackgroundResource(R.drawable.button2v2);
                                        flag21 = 0;
                                    }


                                }
                            } catch (final Exception ex) {
                                Log.i("---", "Exception in thread");
                            }


                    /*        if(mes[0].equals("ttB1_onLR")){
                                khuvuc1.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB1_offLR")){
                                khuvuc1.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB2_onLR")){
                                khuvuc2.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB2_offLR")){
                                khuvuc2.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB3_onLR")){
                                khuvuc3.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB3_offLR")){
                                khuvuc3.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB4_onLR")){
                                khuvuc4.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB4_offLR")){
                                khuvuc4.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB5_onLR")){
                                khuvuc5.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB5_offLR")){
                                khuvuc5.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB6_onLR")){
                                khuvuc6.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB6_offLR")){
                                khuvuc6.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB7_onLR")){
                                khuvuc7.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB7_offLR")){
                                khuvuc7.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB8_onLR")){
                                khuvuc8.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB8_offLR")){
                                khuvuc8.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB9_onLR")){
                                khuvuc9.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB9_offLR")){
                                khuvuc9.setBackgroundResource(R.drawable.button2);
                            }
                            if(mes[0].equals("ttB10_onLR")){
                                khuvuc10.setBackgroundResource(R.drawable.button1);
                            }
                            if(mes[0].equals("ttB10_offLR")){
                                khuvuc10.setBackgroundResource(R.drawable.button2);
                            }
                         */
                        }



                        @Override
                        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                        }
                    });

    }

    private void publisher8266(String payload) {
        //mqttdulieukhuvucI = new DulieukhuvucI(getApplicationContext());
        String topic = "dkesp8266";
        byte[] encodedPayload;
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            //mqttdulieukhuvucI.mqttAndroidClient.publish(topic, message);
            mqttAndroidClient.publish(topic, message);

        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }


    private void connectmqtt() {
        String clientId = MqttClient.generateClientId();;    // Client ID của thiết bị này
        //String serverUri32 = "tcp://m15.cloudmqtt.com:10432";
        String serverUri8266 = "tcp://10.11.15.173:1883";
        mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), serverUri8266, clientId, new MemoryPersistence());
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setConnectionTimeout(240000);
        mqttConnectOptions.setKeepAliveInterval(300);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        try {
            IMqttToken token = mqttAndroidClient.connect(mqttConnectOptions);
            token.setActionCallback(new IMqttActionListener() {
                static final String TAG = "";

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                    // We are connected
                    Log.d(TAG, "onSuccess");
                    subscribeToTopic8266();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    //Log.d(TAG, "onFailure");
                    Toast.makeText(KhuvucIIActivity.this, "Không thể kết nối!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();

        }
  /*      try {

            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                   // Log.w("connectLAPRAP", "connectLAPRAP");
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    subscribeToTopic8266();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                   // Log.w("Mqtt", "Failed to connect to: " + serverUri + exception.toString());
                }
            });


        } catch (MqttException ex) {
            ex.printStackTrace();
        }
*/
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

    private void Nhannutbam() {
        // mProgressDialog = ProgressDialog.show(this, "","...", true);
        new Thread() {
            @Override
            public void run() {

                //delay1();
                try {
                    //connectmqtt();
                    // code runs in a thread


                    khuvuc1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc1.isChecked()) { // nhan lan 1

                                if (flag1 == 1) {
                                    khuvuc1.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off1");
                                    //publisher8266online("online"+" "+"off2");

                                    flag2 = 1;

                                } else {
                                    khuvuc1.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on1");
                                    //publisher8266online("online"+" "+"on2");
                                    flag2 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag2 == 1) {
                                    khuvuc1.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on1");
                                    //publisher8266online("online"+" "+"on2");

                                } else {
                                    khuvuc1.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off1");
                                    //publisher8266online("online"+" "+"off2");

                                }

                            }
                        }
                    });


                    khuvuc2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc2.isChecked()) { // nhan lan 1

                                if (flag3 == 1) {
                                    khuvuc2.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off2");
                                    //publisher8266online("online"+" "+"off2");

                                    flag4 = 1;

                                } else {
                                    khuvuc2.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on2");
                                    //publisher8266online("online"+" "+"on2");
                                    flag4 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag4 == 1) {
                                    khuvuc2.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on2");
                                    //publisher8266online("online"+" "+"on2");

                                } else {
                                    khuvuc2.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off2");
                                    //publisher8266online("online"+" "+"off2");

                                }

                            }
                        }
                    });
                    khuvuc3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc3.isChecked()) { // nhan lan 1

                                if (flag5 == 1) {
                                    khuvuc3.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off3");
                                   // publisher8266online("online"+" "+"off3");

                                    flag6 = 1;

                                } else {
                                    khuvuc3.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on3");
                                    //publisher8266online("online"+" "+"on3");
                                    flag6 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag6 == 1) {
                                    khuvuc3.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on3");
                                    //publisher8266online("online"+" "+"on3");

                                } else {
                                    khuvuc3.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off3");
                                    //publisher8266online("online"+" "+"off3");

                                }

                            }
                        }
                    });

                    khuvuc4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc4.isChecked()) { // nhan lan 1

                                if (flag7 == 1) {
                                    khuvuc4.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off4");
                                    //publisher8266online("online"+" "+"off2");

                                    flag8 = 1;

                                } else {
                                    khuvuc4.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on4");
                                    //publisher8266online("online"+" "+"on2");
                                    flag8 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag8 == 1) {
                                    khuvuc4.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on4");
                                    //publisher8266online("online"+" "+"on2");

                                } else {
                                    khuvuc4.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off4");
                                    //publisher8266online("online"+" "+"off2");

                                }

                            }
                        }
                    });

                    khuvuc5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc5.isChecked()) { // nhan lan 1

                                if (flag9 == 1) {
                                    khuvuc5.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off5");
                                    //publisher8266online("online"+" "+"off2");

                                    flag10 = 1;

                                } else {
                                    khuvuc5.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on5");
                                    //publisher8266online("online"+" "+"on2");
                                    flag10 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag10 == 1) {
                                    khuvuc5.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on5");
                                    //publisher8266online("online"+" "+"on2");

                                } else {
                                    khuvuc5.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off5");
                                    //publisher8266online("online"+" "+"off2");

                                }

                            }
                        }
                    });

                    khuvuc6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc6.isChecked()) { // nhan lan 1

                                if (flag11 == 1) {
                                    khuvuc6.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off6");
                                    //publisher8266online("online"+" "+"off6");

                                    flag12 = 1;

                                } else {
                                    khuvuc6.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on6");
                                    //publisher8266online("online"+" "+"on6");
                                    flag12 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag12 == 1) {
                                    khuvuc6.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on6");
                                   // publisher8266online("online"+" "+"on6");

                                } else {
                                    khuvuc6.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off6");
                                   // publisher8266online("online"+" "+"off6");

                                }

                            }
                        }
                    });

                    khuvuc7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc7.isChecked()) { // nhan lan 1

                                if (flag13 == 1) {
                                    khuvuc7.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off7");
                                    //publisher8266online("online"+" "+"off7");

                                    flag14 = 1;

                                } else {
                                    khuvuc7.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on7");
                                    //publisher8266online("online"+" "+"on7");
                                    flag14 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag14 == 1) {
                                    khuvuc7.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on7");
                                    //publisher8266online("online"+" "+"on7");

                                } else {
                                    khuvuc7.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off7");
                                    //publisher8266online("online"+" "+"off7");

                                }

                            }
                        }
                    });

                    khuvuc8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc8.isChecked()) { // nhan lan 1

                                if (flag15 == 1) {
                                    khuvuc8.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off8");
                                   // publisher8266online("online"+" "+"off8");

                                    flag16 = 1;

                                } else {
                                    khuvuc8.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on8");
                                    //publisher8266online("online"+" "+"on8");
                                    flag16 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag16 == 1) {
                                    khuvuc8.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on8");
                                   // publisher8266online("online"+" "+"on8");

                                } else {
                                    khuvuc8.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off8");
                                   // publisher8266online("online"+" "+"off8");

                                }

                            }
                        }
                    });

                    khuvuc9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc9.isChecked()) { // nhan lan 1

                                if (flag17 == 1) {
                                    khuvuc9.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off9");
                                    //publisher8266online("online"+" "+"off2");

                                    flag18 = 1;

                                } else {
                                    khuvuc9.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("on9");
                                    //publisher8266online("online"+" "+"on2");
                                    flag18 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag18 == 1) {
                                    khuvuc9.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("on9");
                                    //publisher8266online("online"+" "+"on2");

                                } else {
                                    khuvuc9.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("off9");
                                    //publisher8266online("online"+" "+"off2");

                                }

                            }
                        }
                    });

                    khuvuc10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc10.isChecked()) { // nhan lan 1

                                if (flag19 == 1) {
                                    khuvuc10.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("offmuoi");
                                    //publisher8266online("online"+" "+"off2");

                                    flag20 = 1;

                                } else {
                                    khuvuc10.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("onmuoi");
                                    //publisher8266online("online"+" "+"on2");
                                    flag20 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag20 == 1) {
                                    khuvuc10.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("onmuoi");
                                    //publisher8266online("online"+" "+"on2");

                                } else {
                                    khuvuc10.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("offmuoi");
                                    //publisher8266online("online"+" "+"off2");

                                }

                            }
                        }
                    });

                    khuvucall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvucall.isChecked()) { // nhan lan 1

                                if (flag21 == 1) {
                                    khuvucall.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("offall");
                                    //publisher8266online("online"+" "+"off2");

                                    flag22 = 1;

                                } else {
                                    khuvucall.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher8266("onall");
                                    //publisher8266online("online"+" "+"on2");
                                    flag22 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag22 == 1) {
                                    khuvucall.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher8266("onall");
                                    //publisher8266online("online"+" "+"on2");

                                } else {
                                    khuvucall.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher8266("offall");
                                    //publisher8266online("online"+" "+"off2");

                                }

                            }
                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // mProgressDialog.dismiss();
                        }
                    });
                } catch (final Exception ex) {
                    Log.i("---","Exception in thread");
                }
            }
        }.start();

    }
    protected void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }

    }


    @SuppressLint("StaticFieldLeak")
    private class Processnhandulieu82 extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {
            //connectmqtt();
            laydulieuesp8266();
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }


    @SuppressLint("StaticFieldLeak")
    private class Processguinutbam82 extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {
            Nhannutbam();
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }
    @SuppressLint("StaticFieldLeak")
    private class connectmqttlr extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {
            connectmqtt();
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }

}
