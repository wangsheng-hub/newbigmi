package com.example.xingzuoweb.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xingzuoweb.MainActivity;
import com.example.xingzuoweb.R;
public class WelcomeActivity extends AppCompatActivity {
    TextView tv;
    int count = 3;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
           if (msg.what==1){
               count--;
               if (count==0){
                   //判断是否是第一次进入应用，如果是第一次进入此应用，跳转到引导页面
                   boolean isFirst = pref.getBoolean("isFirst",true);
                   Intent intent = new Intent();
                   if (isFirst){
                       intent.setClass(WelcomeActivity.this,GuideActivity.class);
                       SharedPreferences.Editor editor = pref.edit();
                       editor.putBoolean("isFirst",false);
                       editor.commit();
                   }else {
                       intent.setClass(WelcomeActivity.this,MainActivity.class);
                   }
                   startActivity(intent);
                   finish();
               }else {
                   tv.setText(String.valueOf(count));
                   handler.sendEmptyMessageDelayed(1,500);
               }
           }

        }
    };
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv = findViewById(R.id.welcome_tv);
        pref = getSharedPreferences("first_pref", MODE_PRIVATE);
        handler.sendEmptyMessageDelayed(1,500);
    }
}
