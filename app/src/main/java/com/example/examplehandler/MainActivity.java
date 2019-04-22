package com.example.examplehandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

     private Handler handler;
     private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        callingThread();
        receivingHandler();


    }
    public void findId()
    {
        progressBar=findViewById(R.id.progress);
    }

    public void callingThread(){

        //starting thread
        MyThread myThread=new MyThread();
        new Thread(myThread).start();

    }

    private void receivingHandler() {
        // receiving handler message

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg1);
            }
        };
    }

    class MyThread implements Runnable{

        @Override
        public void run() {

            //sending message to handler

            for(int i=0;i<100;i++)
            {

                Message message= Message.obtain();
                message.arg1=i;
                handler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
