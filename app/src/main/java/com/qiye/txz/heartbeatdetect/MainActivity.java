package com.qiye.txz.heartbeatdetect;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startservice();
    }
    private void startservice(){
        Intent intent = new Intent(this,HorizonService.class);
        startService(intent);
    }
    private void handlerhaert(){
        final Handler  handler=new Handler();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                Log.v("TAG","heart beat working");
                handler.postDelayed(this, 2000);
            }
        };

        handler.postDelayed(runnable, 2000);//每两秒执行一次runnable.
       // handler.removeCallbacks(runnable);
    }
    private void threadbeat(){
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                // 要做的事情
                Log.v("TAG","heart beat working");
                super.handleMessage(msg);
            }
        };

        class MyThread implements Runnable {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(10000);// 线程暂停10秒，单位毫秒
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);// 发送消息
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

        new Thread(new MyThread()).start();
    }

}
