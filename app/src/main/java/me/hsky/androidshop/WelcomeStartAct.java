package me.hsky.androidshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import me.hsky.androidshop.utils.SharedUtils;

public class WelcomeStartAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

//        new Handler(new Handler.Callback() {
//            //处理接受消息的方法
//            @Override
//            public boolean handleMessage(Message msg) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                return false;
//            }
//        }).sendEmptyMessageDelayed(1, 3000); //表示延时3秒进行任务的执行

        Timer time = new Timer();
        time.schedule(new Task(), 3000);
    }

    class Task extends TimerTask{

        @Override
        public void run() {
            /*不是第一次启动*/
            if(SharedUtils.getWelcomeBoolean(getBaseContext())){
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }else{
                startActivity(new Intent(getBaseContext(), WelcomeGuide.class));
                SharedUtils.setWelcomeBoolean(getBaseContext(), true);
            }
            finish();
        }
    }
}
