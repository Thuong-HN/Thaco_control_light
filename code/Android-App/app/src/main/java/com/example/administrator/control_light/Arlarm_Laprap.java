package com.example.administrator.control_light;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import android.widget.Toast;
import android.widget.ToggleButton;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
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
import java.util.Calendar;

public class Arlarm_Laprap extends AppCompatActivity {
    ToggleButton arlamkv1,arlamkv2,arlamkv3,arlamkv4,arlamkv5,arlamkv6,arlamkv7,arlamkv8,arlamkv9,arlamkv10;
    TextView bat1,bat2,bat3,bat4,bat5,bat6,bat7,bat8,bat9,bat10,tat1,tat2,tat3,tat4,tat5,tat6,tat7,tat8,tat9,tat10;
    private MqttAndroidClient mqttAndroidClient;

    get_datalr mget_time;

    Calendar c = Calendar.getInstance();

    private Integer mHour1 = c.get(Calendar.HOUR_OF_DAY);
    private Integer mMinute1 = c.get(Calendar.MINUTE);
    private Integer mHour2 = c.get(Calendar.HOUR_OF_DAY);
    private Integer mMinute2 = c.get(Calendar.MINUTE);

   connectmqttlr mconnectmqtt;

    private  int  tt_sw_1 = 0,tt_sw_2 = 0,tt_sw_3 = 0,tt_sw_4 = 0,tt_sw_5 = 0,tt_sw_6 = 0,tt_sw_7 = 0,tt_sw_8 = 0,tt_sw_9 = 0,tt_sw_10 = 0,connect = 0;


    String thu_trong_tuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arlarm__laprap);
       // Log.w("onCreate", "onCreate arlam");
        int week = c.get(Calendar.DAY_OF_WEEK );

        //Toast.makeText(Arlarm_Nhietluyen.this, "SỐ THỨ " + " " + week ,
        //Toast.LENGTH_LONG).show();
        switch (week) {
            case 1:
                // Current day is Sunday
                //Toast.makeText(Arlarm_Nhietluyen.this, "CHỦ NHẬT",
                //Toast.LENGTH_LONG).show();
                thu_trong_tuan = "CHUNHAT";
                break;
            case 7:
                // Current day is Monday
                //Toast.makeText(Arlarm_Nhietluyen.this, "THỨ 7",
                //Toast.LENGTH_LONG).show();
                thu_trong_tuan = "THU7";
                break;
            case 6:

                //Toast.makeText(Arlarm_Nhietluyen.this, "THỨ 6",
                // Toast.LENGTH_LONG).show();
                thu_trong_tuan = "THU6";
                break;
            case 5:

                //Toast.makeText(Arlarm_Nhietluyen.this, "THỨ 5",
                //Toast.LENGTH_LONG).show();
                thu_trong_tuan = "THU5";
                break;
            case 4:

                //Toast.makeText(Arlarm_Nhietluyen.this, "THỨ 4",
                //Toast.LENGTH_LONG).show();
                thu_trong_tuan = "THU4";
                break;
            case 3:

                // Toast.makeText(Arlarm_Nhietluyen.this, "THỨ 3",
                // Toast.LENGTH_LONG).show();
                thu_trong_tuan = "THU3";
                break;
            case 2:

                //Toast.makeText(Arlarm_Nhietluyen.this, "THỨ 2",
                //Toast.LENGTH_LONG).show();
                thu_trong_tuan = "THU2";
                break;
        }
        mconnectmqtt=new connectmqttlr();
        mconnectmqtt.execute();

        arlamkv1 =  findViewById(R.id.arlam1);
        arlamkv2 =  findViewById(R.id.arlam2);
        arlamkv3 =  findViewById(R.id.arlam3);
        arlamkv4 = findViewById(R.id.arlam4);
        arlamkv5 =  findViewById(R.id.arlam5);
        arlamkv6 =  findViewById(R.id.arlam6);
        arlamkv7 =  findViewById(R.id.arlam7);
        arlamkv8 =  findViewById(R.id.arlam8);
        arlamkv9 =  findViewById(R.id.arlam9);
        arlamkv10 =  findViewById(R.id.arlam10);

        bat1 =  findViewById(R.id.txtbat1);
        bat2 =  findViewById(R.id.txtbat2);
        bat3 =  findViewById(R.id.txtbat3);
        bat4 =  findViewById(R.id.txtbat4);
        bat5 =  findViewById(R.id.txtbat5);
        bat6 =  findViewById(R.id.txtbat6);
        bat7 =  findViewById(R.id.txtbat7);
        bat8 =  findViewById(R.id.txtbat8);
        //bat9 =  findViewById(R.id.txtbat9);
        bat10 =  findViewById(R.id.txtbat10);

        tat1 =  findViewById(R.id.txttat1);
        tat2 =  findViewById(R.id.txttat2);
        tat3 =  findViewById(R.id.txttat3);
        tat4 =  findViewById(R.id.txttat4);
        tat5 =  findViewById(R.id.txttat5);
        tat6 = findViewById(R.id.txttat6);
        tat7 =  findViewById(R.id.txttat7);
        tat8 =  findViewById(R.id.txttat8);
        //tat9 =  findViewById(R.id.txttat9);
        tat10 =  findViewById(R.id.txttat10);

        bat1.setVisibility(View.INVISIBLE);bat2.setVisibility(View.INVISIBLE);bat3.setVisibility(View.INVISIBLE);bat4.setVisibility(View.INVISIBLE);
        bat5.setVisibility(View.INVISIBLE);bat6.setVisibility(View.INVISIBLE);bat7.setVisibility(View.INVISIBLE);bat8.setVisibility(View.INVISIBLE);
        //bat9.setVisibility(View.INVISIBLE);
        bat10.setVisibility(View.INVISIBLE);

        tat1.setVisibility(View.INVISIBLE);tat2.setVisibility(View.INVISIBLE);tat3.setVisibility(View.INVISIBLE);tat4.setVisibility(View.INVISIBLE);
        tat5.setVisibility(View.INVISIBLE);tat6.setVisibility(View.INVISIBLE);tat7.setVisibility(View.INVISIBLE);tat8.setVisibility(View.INVISIBLE);
        //tat9.setVisibility(View.INVISIBLE);
        tat10.setVisibility(View.INVISIBLE);


        arlamkv1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                tt_sw_1 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv1.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv1.setBackgroundResource(R.drawable.clockredv2);
                        bat1.setVisibility(View.INVISIBLE);tat1.setVisibility(View.INVISIBLE);

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



            }

        });

        arlamkv2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_2 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv2.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv2.setBackgroundResource(R.drawable.clockredv2);
                        bat2.setVisibility(View.INVISIBLE);tat2.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        arlamkv3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_3 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv3.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv3.setBackgroundResource(R.drawable.clockredv2);
                        bat3.setVisibility(View.INVISIBLE);tat3.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        arlamkv4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_4 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv4.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv4.setBackgroundResource(R.drawable.clockredv2);
                        bat4.setVisibility(View.INVISIBLE);tat4.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        arlamkv5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_5 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv5.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv5.setBackgroundResource(R.drawable.clockredv2);
                        bat5.setVisibility(View.INVISIBLE);tat5.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        arlamkv6.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_6 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv6.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv6.setBackgroundResource(R.drawable.clockredv2);
                        bat6.setVisibility(View.INVISIBLE);tat6.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        arlamkv7.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_7 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv7.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv7.setBackgroundResource(R.drawable.clockredv2);
                        bat7.setVisibility(View.INVISIBLE);tat7.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        arlamkv8.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_8 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv8.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv8.setBackgroundResource(R.drawable.clockredv2);
                        bat8.setVisibility(View.INVISIBLE);tat8.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

      /*  arlamkv9.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_9 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv9.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv9.setBackgroundResource(R.drawable.clockredv2);
                        bat9.setVisibility(View.INVISIBLE);tat9.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });  */

        arlamkv10.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                tt_sw_10 =1;
                AlertDialog.Builder builder = new AlertDialog.Builder(Arlarm_Laprap.this);
                builder.setTitle("Cảnh Báo !");
                builder.setIcon(R.drawable.warning2);
                builder.setMessage("Bạn muốn đặt giờ bật/tắt đèn?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        showDialog(2);
                        arlamkv10.setBackgroundResource(R.drawable.clockbluev2);
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arlamkv10.setBackgroundResource(R.drawable.clockredv2);
                        bat10.setVisibility(View.INVISIBLE);tat10.setVisibility(View.INVISIBLE);
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

       // Log.w("onstart", "onstart  arlam laprap");


    }
    @Override
    protected void onRestart() {
        super.onRestart();
       // Log.w("Restart", "onRestart arlam");

    }
    @Override
    protected void onStop() {
        super.onStop();
       // Log.w("onstop", "dis onstop arlam");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.w("ondetroy", "dis ondetroy arlam");

        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        send_time("getolddata8266");
        //Log.w("onpause", "dis onpause arlam");
        try {
            //mqttAndroidClient.unregisterResources();
            //mqttAndroidClient.close();

            mqttAndroidClient.disconnect();
            //mqttAndroidClient = null;

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //delay();


            mget_time = new get_datalr();
            mget_time.execute();



        //delay();

       /* final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

            }
        }, 500); */
        //Log.w("onresume", " onresume arlam");


    }

    private void connectmqtt() {
        String clientId = MqttClient.generateClientId();;    // Client ID của thiết bị này
        //String serverUri32 = "tcp://m15.cloudmqtt.com:10432";
        String serverUri8266 = "tcp://10.11.15.173:1883";
        mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), serverUri8266, clientId, new MemoryPersistence());

        //MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        //mqttConnectOptions.setAutomaticReconnect(true);
        //mqttConnectOptions.setCleanSession(false);
        //String username = "vtzebdqb";
       // mqttConnectOptions.setUserName(username);
        //String password = "wbqkA68b6iej";
        //mqttConnectOptions.setPassword(password.toCharArray());
        try {
            IMqttToken token = mqttAndroidClient.connect();
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
                    Log.d(TAG, "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();

        }
   /*     try {

            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

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
                    //Log.w("Mqtt", "Failed to connect to: " + serverUri + exception.toString());
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
                    //Log.w("Mqtt","connected arlamlaprap!");
                    //send_time("connected arlamLR");
                    send_time("hengioLR");
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

    @SuppressLint("StaticFieldLeak")
    private class get_datalr extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected JSONObject doInBackground(String... args) {

            get_time();
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
        }

    }


    private void get_time() { // Lấy time hẹn giờ

        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                //Log.w("Rec", "Getdata arlam");
            }
            @Override
            public void connectionLost(Throwable throwable) {
                //Log.w("Rec", "Lostdata arlam");
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void messageArrived(String topic, MqttMessage message) {

                try {
                    String s = new String(message.getPayload());
                    String[] mes = s.split("\\s");  // cách nhau dấu space

                    //Log.w("Rec", "messArrived arlam"+ " " + mes);

                    if(mes[0].equals("capnhatLR")){
                        if(mes[1].equals("henbat1")){
                            arlamkv1.setBackgroundResource(R.drawable.clockbluev2);
                            bat1.setVisibility(View.VISIBLE);bat1.setText(mes[2]+":"+mes[3]);
                            tat1.setVisibility(View.VISIBLE);tat1.setText(mes[4]+":"+mes[5]);
                        }else{
                            arlamkv1.setBackgroundResource(R.drawable.clockredv2);
                            bat1.setVisibility(View.INVISIBLE);bat1.setText("");
                            tat1.setVisibility(View.INVISIBLE);tat1.setText("");
                        }

                        if(mes[6].equals("henbat2")){
                            arlamkv2.setBackgroundResource(R.drawable.clockbluev2);
                            bat2.setVisibility(View.VISIBLE);bat2.setText(mes[7]+":"+mes[8]);
                            tat2.setVisibility(View.VISIBLE);tat2.setText(mes[9]+":"+mes[10]);
                        }else{
                            arlamkv2.setBackgroundResource(R.drawable.clockredv2);
                            bat2.setVisibility(View.INVISIBLE);bat2.setText("");
                            tat2.setVisibility(View.INVISIBLE);tat2.setText("");
                        }
                        if(mes[11].equals("henbat3")){
                            arlamkv3.setBackgroundResource(R.drawable.clockbluev2);
                            bat3.setVisibility(View.VISIBLE);bat3.setText(mes[12]+":"+mes[13]);
                            tat3.setVisibility(View.VISIBLE);tat3.setText(mes[14]+":"+mes[15]);
                        }else{
                            arlamkv3.setBackgroundResource(R.drawable.clockredv2);
                            bat3.setVisibility(View.INVISIBLE);bat3.setText("");
                            tat3.setVisibility(View.INVISIBLE);tat3.setText("");
                        }
                        if(mes[16].equals("henbat4")){
                            arlamkv4.setBackgroundResource(R.drawable.clockbluev2);
                            bat4.setVisibility(View.VISIBLE);bat4.setText(mes[17]+":"+mes[18]);
                            tat4.setVisibility(View.VISIBLE);tat4.setText(mes[19]+":"+mes[20]);
                        }else{
                            arlamkv4.setBackgroundResource(R.drawable.clockredv2);
                            bat4.setVisibility(View.INVISIBLE);bat4.setText("");
                            tat4.setVisibility(View.INVISIBLE);tat4.setText("");
                        }
                        if(mes[21].equals("henbat5")){
                            arlamkv5.setBackgroundResource(R.drawable.clockbluev2);
                            bat5.setVisibility(View.VISIBLE);bat5.setText(mes[22]+":"+mes[23]);
                            tat5.setVisibility(View.VISIBLE);tat5.setText(mes[24]+":"+mes[25]);
                        }else{
                            arlamkv5.setBackgroundResource(R.drawable.clockredv2);
                            bat5.setVisibility(View.INVISIBLE);bat5.setText("");
                            tat5.setVisibility(View.INVISIBLE);tat5.setText("");
                        }
                        if(mes[26].equals("henbat6")){
                            arlamkv6.setBackgroundResource(R.drawable.clockbluev2);
                            bat6.setVisibility(View.VISIBLE);bat6.setText(mes[27]+":"+mes[28]);
                            tat6.setVisibility(View.VISIBLE);tat6.setText(mes[29]+":"+mes[30]);
                        }else{
                            arlamkv6.setBackgroundResource(R.drawable.clockredv2);
                            bat6.setVisibility(View.INVISIBLE);bat6.setText("");
                            tat6.setVisibility(View.INVISIBLE);tat6.setText("");
                        }
                        if(mes[31].equals("henbat7")){
                            arlamkv7.setBackgroundResource(R.drawable.clockbluev2);
                            bat7.setVisibility(View.VISIBLE);bat7.setText(mes[32]+":"+mes[33]);
                            tat7.setVisibility(View.VISIBLE);tat7.setText(mes[34]+":"+mes[35]);
                        }else{
                            arlamkv7.setBackgroundResource(R.drawable.clockredv2);
                            bat7.setVisibility(View.INVISIBLE);bat7.setText("");
                            tat7.setVisibility(View.INVISIBLE);tat7.setText("");
                        }
                        if(mes[36].equals("henbat8")){
                            arlamkv8.setBackgroundResource(R.drawable.clockbluev2);
                            bat8.setVisibility(View.VISIBLE);bat8.setText(mes[37]+":"+mes[38]);
                            tat8.setVisibility(View.VISIBLE);tat8.setText(mes[39]+":"+mes[40]);
                        }else{
                            arlamkv8.setBackgroundResource(R.drawable.clockredv2);
                            bat8.setVisibility(View.INVISIBLE);bat8.setText("");
                            tat8.setVisibility(View.INVISIBLE);tat8.setText("");
                        }
                        if(mes[41].equals("henbat9")){
                            arlamkv9.setBackgroundResource(R.drawable.clockbluev2);
                            bat9.setVisibility(View.VISIBLE);bat9.setText(mes[42]+":"+mes[43]);
                            tat9.setVisibility(View.VISIBLE);tat9.setText(mes[44]+":"+mes[45]);
                        }else{
                            arlamkv9.setBackgroundResource(R.drawable.clockredv2);
                            bat9.setVisibility(View.INVISIBLE);bat9.setText("");
                            tat9.setVisibility(View.INVISIBLE);tat9.setText("");
                        }
                        if(mes[46].equals("henbat10")){
                            arlamkv10.setBackgroundResource(R.drawable.clockbluev2);
                            bat10.setVisibility(View.VISIBLE);bat10.setText(mes[47]+":"+mes[48]);
                            tat10.setVisibility(View.VISIBLE);tat10.setText(mes[49]+":"+mes[50]);
                        }else{
                            arlamkv10.setBackgroundResource(R.drawable.clockredv2);
                            bat10.setVisibility(View.INVISIBLE);bat10.setText("");
                            tat10.setVisibility(View.INVISIBLE);tat10.setText("");
                        }
                    }

                    if(mes[0].equals("hengio")){
                        if(mes[1].equals("hentat1")) {
                            arlamkv1.setBackgroundResource(R.drawable.clockredv2);
                            bat1.setVisibility(View.INVISIBLE);bat1.setText("");
                            tat1.setVisibility(View.INVISIBLE);tat1.setText("");
                        }

                        if(mes[1].equals("hentat2")) {
                            arlamkv2.setBackgroundResource(R.drawable.clockredv2);
                            bat2.setVisibility(View.INVISIBLE);
                            bat2.setText("");
                            tat2.setVisibility(View.INVISIBLE);
                            tat2.setText("");
                        }

                        if(mes[1].equals("hentat3")) {

                            arlamkv3.setBackgroundResource(R.drawable.clockredv2);
                            bat3.setVisibility(View.INVISIBLE);
                            bat3.setText("");
                            tat3.setVisibility(View.INVISIBLE);
                            tat3.setText("");
                        }
                        if(mes[1].equals("hentat4")){

                            arlamkv4.setBackgroundResource(R.drawable.clockredv2);
                            bat4.setVisibility(View.INVISIBLE);bat4.setText("");
                            tat4.setVisibility(View.INVISIBLE);tat4.setText("");

                        }
                        if(mes[1].equals("hentat5")){

                            arlamkv5.setBackgroundResource(R.drawable.clockredv2);
                            bat5.setVisibility(View.INVISIBLE);bat5.setText("");
                            tat5.setVisibility(View.INVISIBLE);tat5.setText("");

                        }
                        if(mes[1].equals("hentat6")){

                            arlamkv6.setBackgroundResource(R.drawable.clockredv2);
                            bat6.setVisibility(View.INVISIBLE);bat6.setText("");
                            tat6.setVisibility(View.INVISIBLE);tat6.setText("");

                        }
                        if(mes[1].equals("hentat7")){

                            arlamkv7.setBackgroundResource(R.drawable.clockredv2);
                            bat7.setVisibility(View.INVISIBLE);bat7.setText("");
                            tat7.setVisibility(View.INVISIBLE);tat7.setText("");

                        }
                        if(mes[1].equals("hentat8")){

                            arlamkv8.setBackgroundResource(R.drawable.clockredv2);
                            bat8.setVisibility(View.INVISIBLE);bat8.setText("");
                            tat8.setVisibility(View.INVISIBLE);tat8.setText("");

                        }
                        if(mes[1].equals("hentat9")){

                            arlamkv9.setBackgroundResource(R.drawable.clockredv2);
                            bat9.setVisibility(View.INVISIBLE);bat9.setText("");
                            tat9.setVisibility(View.INVISIBLE);tat9.setText("");

                        }
                        if(mes[1].equals("hentat10")){

                            arlamkv10.setBackgroundResource(R.drawable.clockredv2);
                            bat10.setVisibility(View.INVISIBLE);bat10.setText("");
                            tat10.setVisibility(View.INVISIBLE);tat10.setText("");

                        }
                    }

                } catch (final Exception ex) {
                    Log.i("---","Exception in thread");
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    private void send_time(String payload) {
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



    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            //    case 1:
            //       return new DatePickerDialog(this, mDate1SetListener, mYear1, mMonth1, mday1);

            case 2:

                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour1 = hourOfDay;
                        mMinute1=minute;
                        showDialog(4);
                    }
                }, mHour1, mMinute1, true);

            //   case 3:

            //       return new DatePickerDialog(this, mDate2SetListener, mYear1, mMonth1, mday1);


            case 4:

                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour2 = hourOfDay;
                        mMinute2=minute;


                        if( tt_sw_1 == 1){
                            tt_sw_1  = 0;
                            bat1.setVisibility(View.VISIBLE);
                            bat1.setText( mHour1.toString() + ":" + mMinute1.toString());

                            tat1.setVisibility(View.VISIBLE);
                            tat1.setText( mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio1" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+ mHour2 + " " + mMinute2);
                        }



                        if (tt_sw_2 == 1) {
                            tt_sw_2  = 0;
                            bat2.setVisibility(View.VISIBLE);
                            bat2.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat2.setVisibility(View.VISIBLE);
                            tat2.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio2" + " "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+mHour2 + " " + mMinute2);

                        }

                        if(tt_sw_3 == 1) {
                            tt_sw_3  = 0;
                            bat3.setVisibility(View.VISIBLE);
                            bat3.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat3.setVisibility(View.VISIBLE);
                            tat3.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio3" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+mHour2 + " " + mMinute2);
                        }
                        if(tt_sw_4 == 1) {
                            tt_sw_4  = 0;
                            bat4.setVisibility(View.VISIBLE);
                            bat4.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat4.setVisibility(View.VISIBLE);
                            tat4.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio4" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+mHour2 + " " + mMinute2);
                        }
                        if(tt_sw_5 == 1) {
                            tt_sw_5 =0;
                            bat5.setVisibility(View.VISIBLE);
                            bat5.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat5.setVisibility(View.VISIBLE);
                            tat5.setText( mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio5" +" "+thu_trong_tuan+ " "+ mHour1 + " " + mMinute1 + " "+ mHour2 + " " + mMinute2);
                        }
                        if(tt_sw_6 == 1) {
                            tt_sw_6 =0;
                            bat6.setVisibility(View.VISIBLE);
                            bat6.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat6.setVisibility(View.VISIBLE);
                            tat6.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio6" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+ mHour2 + " " + mMinute2);
                        }
                        if(tt_sw_7 == 1) {
                            tt_sw_7 =0;
                            bat7.setVisibility(View.VISIBLE);
                            bat7.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat7.setVisibility(View.VISIBLE);
                            tat7.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio7" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+  mHour2 + " " + mMinute2);
                        }
                        if(tt_sw_8 == 1) {
                            tt_sw_8 =0;
                            bat8.setVisibility(View.VISIBLE);
                            bat8.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat8.setVisibility(View.VISIBLE);
                            tat8.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio8" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+  mHour2 + " " + mMinute2);
                        }
                        if(tt_sw_9 == 1) {
                            tt_sw_9 =0;
                            bat9.setVisibility(View.VISIBLE);
                            bat9.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat9.setVisibility(View.VISIBLE);
                            tat9.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio9" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+mHour2 + " " + mMinute2);
                        }
                        if(tt_sw_10 == 1) {
                            tt_sw_10 =0;
                            bat10.setVisibility(View.VISIBLE);
                            bat10.setText(mHour1.toString() + ":" + mMinute1.toString());

                            tat10.setVisibility(View.VISIBLE);
                            tat10.setText(mHour2.toString() + ":" + mMinute2.toString());

                            send_time("hengio10" +" "+thu_trong_tuan+ " "+mHour1 + " " + mMinute1 + " "+ mHour2 + " " + mMinute2);
                        }

                    }
                }, mHour2, mMinute2, true);
        }
        return super.onCreateDialog(id);
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

    protected void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

    }
}
