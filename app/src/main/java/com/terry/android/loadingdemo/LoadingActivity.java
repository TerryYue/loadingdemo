package com.terry.android.loadingdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoadingActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (count<2){//模拟启动卡顿时，无法加载出布局
                mHandler.sendEmptyMessageDelayed(0,1000);
                count++;
            }else if (count==2){ //加载布局文件
                initView();
                mHandler.sendEmptyMessageDelayed(0,1000);
                count++;
            }else if (count<=5){ //模拟加载布局完成后初始化数据状态
                mainToast.setText(count+"");
                mainToast.show();
                mHandler.sendEmptyMessageDelayed(0,1000);
                count++;
            }else {     //模拟初始化数据完成，跳转主界面
                mainToast.setText("go");
                mainToast.show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };

    private int count = 1;

    private Toast mainToast ;

    private TextView tv_title;

    private LinearLayout ll_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler.sendEmptyMessageDelayed(0,1000);

    }

    private void initView(){

        setContentView(R.layout.activity_loading);

        tv_title = (TextView) findViewById(R.id.tv_title);

        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);

        mainToast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);

    }


}
