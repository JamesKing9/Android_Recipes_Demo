package com.cheng.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import de.greenrobot.event.EventBus;


public class SecondActivity extends AppCompatActivity {

    private Button btn_FirstEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn_FirstEvent = (Button) findViewById(R.id.btn_first_event);

        btn_FirstEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //发送消息是使用EventBus中的Post方法来实现发送的，发送过去的是我们新建的类的实例！
                EventBus.getDefault().post(
                        new MessageEvent("FirstEvent btn clicked"));
            }
        });
    }
}
