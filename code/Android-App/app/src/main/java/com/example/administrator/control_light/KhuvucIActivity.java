package com.example.administrator.control_light;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class KhuvucIActivity extends AppCompatActivity {
    ToggleButton khuvuc1, khuvuc2, khuvuc3, khuvuc4, khuvucALL, khuvuc6,khuvuc5, khuvuc7,khuvuc8, khuvuc10auto, khuvuc9manual, khuvuc10manual;
    ImageView arlam_var;
    private  int flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9,flag10,flag11,flag12,flag13,flag14,flag15,flag16,flag17,flag18,flag19,flag20,flag21,flag22,flag23,flag24,flag25;
    private MqttAndroidClient mqttAndroidClient;
    Processnhandulieu32 mProcessnhandulieu32;
    connectmqtt mProcessconnectmqtt;
    Processguinutbam32 mProcessguinutbam32;
    private int connect = 0;
    private MediaPlayer playoff, playon,playchoose;



    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuvuc_i);
        arlam_var = findViewById(R.id.alarm_LR);
        khuvuc1 = findViewById(R.id.btn2kv1);
        khuvuc2 = findViewById(R.id.btn2kv2);
        khuvuc3 = findViewById(R.id.btn2kv3);
        khuvuc4 = findViewById(R.id.btn2kv4);
        khuvuc5 = findViewById(R.id.btn2kv5);
        khuvuc6 = findViewById(R.id.btn2kv6);
        khuvuc7 = findViewById(R.id.btn2kv7);
        khuvuc8 = findViewById(R.id.btn2kv8);

        khuvucALL = findViewById(R.id.btn2ALLnl);
        //khuvuc9auto = findViewById(R.id.btn2kv9auto);
        khuvuc10auto = findViewById(R.id.btn2kv10auto);
        khuvuc9manual = findViewById(R.id.btn2kv9manual);
        //Log.w("Create", "oncreate NHIETLUYEN");



        playoff = MediaPlayer.create(this, R.raw.off);
        playon = MediaPlayer.create(this, R.raw.on);
        playchoose = MediaPlayer.create(this, R.raw.login);
        arlam_var.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //publisher32("hengioNL");
                //delay1();
                playchoose.start();
                Toast.makeText(KhuvucIActivity.this, "Chức năng hẹn giờ vô hiệu hóa !", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(KhuvucIActivity.this, Arlarm_Nhietluyen.class);
                //startActivity(intent);
                //finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
                //Log.w("start", "onstart NHIETLUYEN");
        mProcessconnectmqtt=new connectmqtt();
        mProcessconnectmqtt.execute();

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.w("REstart", "onREstart NHIETLUYEN");
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Log.w("onresume", "onresume NHIETLUYEN");

        mProcessguinutbam32=new Processguinutbam32();
        mProcessguinutbam32.execute();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                mProcessnhandulieu32=new Processnhandulieu32();
                mProcessnhandulieu32.execute();

            }
        }, 100);
    }
    @Override
    protected void onStop() {
        super.onStop();
       //Log.w("stop", "onstop NHIETLUYEN");


    }
    @Override
    protected void onPause() {
        super.onPause();
        //Log.w("onpause dis *********", "onPause NHIETLUYEN");
        try {
            //mqttAndroidClient.unregisterResources();
            //mqttAndroidClient.close();
            if(mqttAndroidClient.isConnected()&& mqttAndroidClient !=null) {
                mqttAndroidClient.unregisterResources();
                mqttAndroidClient.close();
                mqttAndroidClient.disconnect();
                mqttAndroidClient = null;
                Log.w("Disconnect", "Disconect NHIETLUYEN");
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("ondestroy", "ondestroy NHIETLUYEN");
        playchoose.stop();
        playchoose.release();


        finish();

    }
    private void laydulieuesp32() {


        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
               // Log.w("GETDATA", "GETDATA NHIETLUYEN");
            }
            @Override
            public void connectionLost(Throwable throwable) {
                //delay();
               // Log.w("LOST NHIETLUYEN", "NOT DATA NHIETLUYEN");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void messageArrived(String topic, MqttMessage message) {

                String s = new String(message.getPayload());
                String[] mes = s.split("\\s");  // cách nhau dấu space
                Log.d("messageArrived", Arrays.toString(mes));
                //Toast.makeText(KhuvucIActivity.this, "DATA:" + " " + Arrays.toString(mes), Toast.LENGTH_SHORT).show(); //+ mes[11]+" "+mes[12]+" "+mes[13]+" "+mes[14]+" "+mes[15]+mes[16]+" "+mes[17]
                //if(tmp==0) {
                //delay();

                    if(mes[0].equals("dknutNL")) {

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

                                khuvuc9manual.setBackgroundResource(R.drawable.button1v2);
                                //khuvuc9auto.setVisibility(View.INVISIBLE);
                            } catch (final Exception ex) {
                                Log.i("---", "Exception in thread");
                            }
                        } else {
                            khuvuc9manual.setBackgroundResource(R.drawable.button2v2);
                            //khuvuc9auto.setVisibility(View.VISIBLE);
                            flag17 = 0;
                        }

                        if (mes[10].equals("1")) {
                            try {
                                flag19 = 1;

                                khuvuc10auto.setBackgroundResource(R.drawable.button1v2);
                                //khuvuc10manual.setVisibility(View.INVISIBLE);
                            } catch (final Exception ex) {
                                Log.i("---", "Exception in thread");
                            }
                        } else {
                            khuvuc10auto.setBackgroundResource(R.drawable.button2v2);
                            //khuvuc10manual.setVisibility(View.VISIBLE);
                            flag19 = 0;
                        }

                        if (mes[11].equals("1")) {
                            try {
                                flag21 = 1;

                                khuvucALL.setBackgroundResource(R.drawable.button1v2);
                            } catch (final Exception ex) {
                                Log.i("---", "Exception in thread");
                            }
                        } else {
                            khuvucALL.setBackgroundResource(R.drawable.button2v2);
                            flag21 = 0;
                        }










                    }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    private void publisher32(String payload) {

        String topic = "dkesp32";
        byte[] encodedPayload;
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            mqttAndroidClient.publish(topic, message);

        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }


    private void connectmqtt() {

        String clientId = MqttClient.generateClientId();    // Client ID của thiết bị này
        //String serverUri32 = "tcp://m12.cloudmqtt.com:17168";
        String serverUri32 = "tcp://10.11.15.162:1883";
        mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), serverUri32, clientId, new MemoryPersistence());
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setKeepAliveInterval(300);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setConnectionTimeout(240000); //thời gian ping của điện thoại
        mqttConnectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);

            try {

                    IMqttToken token = mqttAndroidClient.connect(mqttConnectOptions);
                    token.setActionCallback(new IMqttActionListener() {
                        // public static final String TAG = "";
                        static final String TAG = "";

                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {

                            subscribeToTopic32();
                            // We are connected
                            Log.d(TAG, "Kết nối NHIETLUYEN");


                            //delay();
                            //laydulieuesp32();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            // Something went wrong e.g. connection timeout or firewall problems
                            //Log.d(TAG, "onFailure");
                            Toast.makeText(KhuvucIActivity.this, "Không thể kết nối!", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch(MqttException e){
                    e.printStackTrace();

                }

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
            //System.err.println("Exception whilst subscribing");
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



                    khuvuc1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //playoff.start();
                            if (khuvuc1.isChecked()) { // nhan lan 1

                                if (flag1 == 1) {

                                    khuvuc1.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off1");
                                    //publisher32online("online"+" "+"off1");
                                    flag2 = 1;

                                } else {
                                    khuvuc1.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on1");
                                    //publisher32online("online"+" "+"on1");
                                    flag2 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag2 == 1) {

                                    khuvuc1.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on1");
                                    //publisher32online("online"+" "+"on1");

                                } else {

                                    khuvuc1.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off1");
                                    //publisher32online("online"+" "+"off1");

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
                                    publisher32("off2");
                                    //publisher32online("online"+" "+"off2");
                                    flag4 = 1;

                                } else {

                                    khuvuc2.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on2");
                                    //publisher32online("online"+" "+"on2");
                                    flag4 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag4 == 1) {

                                    khuvuc2.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on2");
                                    //publisher32online("online"+" "+"on2");

                                } else {

                                    khuvuc2.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off2");
                                    //publisher32online("online"+" "+"off2");

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
                                    publisher32("off3");
                                    //publisher32online("online"+" "+"off3");
                                    flag6 = 1;

                                } else {
                                    khuvuc3.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on3");
                                    //publisher32online("online"+" "+"on3");
                                    flag6 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag6 == 1) {

                                    khuvuc3.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on3");
                                    //publisher32online("online"+" "+"on3");

                                } else {

                                    khuvuc3.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off3");
                                    //publisher32online("online"+" "+"off3");

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
                                    publisher32("off4");


                                    flag8 = 1;

                                } else {

                                    khuvuc4.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on4");

                                    flag8 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag8 == 1) {
                                    khuvuc4.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on4");


                                } else {
                                    khuvuc4.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off4");


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
                                    publisher32("off5");


                                    flag10 = 1;

                                } else {

                                    khuvuc5.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on5");

                                    flag10 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag10 == 1) {
                                    khuvuc5.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on5");


                                } else {
                                    khuvuc5.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off5");


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
                                    publisher32("off6");


                                    flag12 = 1;

                                } else {

                                    khuvuc6.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on6");

                                    flag12 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag12 == 1) {
                                    khuvuc6.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on6");


                                } else {
                                    khuvuc6.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off6");


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
                                    publisher32("off7");


                                    flag14 = 1;

                                } else {

                                    khuvuc7.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on7");

                                    flag14 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag14 == 1) {
                                    khuvuc7.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on7");


                                } else {
                                    khuvuc7.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off7");


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
                                    publisher32("off8");


                                    flag16 = 1;

                                } else {

                                    khuvuc8.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on8");

                                    flag16 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag16 == 1) {
                                    khuvuc8.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on8");


                                } else {
                                    khuvuc8.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off8");


                                }

                            }
                        }
                    });





            /*        khuvuc9auto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc9auto.isChecked()) { // nhan lan 1

                                if (flag17 == 1) {   // tat
                                    khuvuc9auto.setBackgroundResource(R.drawable.button2);

                                    publisher32("offauto9");
                                    khuvuc9auto.setVisibility(View.INVISIBLE);
                                    khuvuc9manual.setVisibility(View.VISIBLE);
                                    //publisher32online("online"+" "+"off9");

                                    flag18 = 1;

                                } else { // bat
                                    khuvuc9auto.setBackgroundResource(R.drawable.button1);

                                    // gui du lieu dieu khien
                                    publisher32("onauto9");
                                    khuvuc9manual.setVisibility(View.INVISIBLE);
                                    khuvuc9auto.setVisibility(View.VISIBLE);
                                    //publisher32online("online"+" "+"on9");
                                    flag18 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag18 == 1) { //bat
                                    khuvuc9auto.setBackgroundResource(R.drawable.button1);

                                    publisher32("onauto9");
                                    khuvuc9manual.setVisibility(View.INVISIBLE);
                                    khuvuc9auto.setVisibility(View.VISIBLE);
                                    //publisher32online("online"+" "+"on9");

                                } else {  //tat
                                    khuvuc9auto.setBackgroundResource(R.drawable.button2);

                                    publisher32("offauto9");
                                    khuvuc9manual.setVisibility(View.VISIBLE);
                                    khuvuc9auto.setVisibility(View.INVISIBLE);
                                    //publisher32online("online"+" "+"off9");

                                }

                            }
                        }
                    });
*/
                    khuvuc9manual.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc9manual.isChecked()) { // nhan lan 1

                                if (flag17 == 1) {
                                    khuvuc9manual.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off9");
                                    //khuvuc9manual.setVisibility(View.INVISIBLE);
                                    //khuvuc9auto.setVisibility(View.VISIBLE);
                                    //publisher32online("online"+" "+"off9");

                                    flag18 = 1;

                                } else {
                                    khuvuc9manual.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("on9");
                                    //publisher32online("online"+" "+"on9");
                                    flag18 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag18 == 1) {
                                    khuvuc9manual.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("on9");
                                    //publisher32online("online"+" "+"on9");

                                } else {
                                    khuvuc9manual.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("off9");
                                    //khuvuc9manual.setVisibility(View.INVISIBLE);
                                    //khuvuc9auto.setVisibility(View.VISIBLE);
                                    //publisher32online("online"+" "+"off9");

                                }

                            }
                        }
                    });

                    khuvuc10auto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvuc10auto.isChecked()) { // nhan lan 1

                                if (flag19 == 1) {
                                    khuvuc10auto.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("offauto10");
                                    //khuvuc10manual.setVisibility(View.VISIBLE);
                                    //khuvuc10auto.setVisibility(View.INVISIBLE);
                                    //publisher32online("online"+" "+"offmuoi");

                                    flag20 = 1;

                                } else {
                                    khuvuc10auto.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("onauto10");
                                    //khuvuc10manual.setVisibility(View.INVISIBLE);
                                    //khuvuc10auto.setVisibility(View.VISIBLE);
                                    //publisher32online("online"+" "+"onmuoi");
                                    flag20 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag20 == 1) {
                                    khuvuc10auto.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("onauto10");
                                    //khuvuc10manual.setVisibility(View.INVISIBLE);
                                    //khuvuc10auto.setVisibility(View.VISIBLE);
                                    //publisher32online("online"+" "+"onmuoi");

                                } else {
                                    khuvuc10auto.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("offauto10");
                                    //khuvuc10manual.setVisibility(View.VISIBLE);
                                    //khuvuc10auto.setVisibility(View.INVISIBLE);
                                    //publisher32online("online"+" "+"offmuoi");

                                }

                            }
                        }
                    });

                    khuvucALL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (khuvucALL.isChecked()) { // nhan lan 1

                                if (flag21 == 1) {
                                    khuvucALL.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("offall");
                                    //publisher32online("online"+" "+"off8");
                                    flag22 = 1;

                                } else {
                                    khuvucALL.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    // gui du lieu dieu khien
                                    publisher32("onall");
                                    //publisher32online("online"+" "+"on8");
                                    flag22 = 0;

                                }
                            } else {   // nhan lan 2
                                if (flag22 == 1) {
                                    khuvucALL.setBackgroundResource(R.drawable.button1v2);
                                    playon.start();
                                    publisher32("onall");
                                    //publisher32online("online"+" "+"on8");

                                } else {
                                    khuvucALL.setBackgroundResource(R.drawable.button2v2);
                                    playoff.start();
                                    publisher32("offall");
                                    //publisher32online("online"+" "+"off8");

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
    private class Processnhandulieu32 extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {
            //connectmqtt();
            laydulieuesp32();
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class Processguinutbam32 extends AsyncTask<String, JSONObject, JSONObject> {

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
    private class connectmqtt extends AsyncTask<String, JSONObject, JSONObject> {

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
