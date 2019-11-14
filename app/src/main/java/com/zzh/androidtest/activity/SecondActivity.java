package com.zzh.androidtest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zzh.androidtest.MainActivity;
import com.zzh.androidtest.R;
import com.zzh.androidtest.fragment.FirstFragment;
import com.zzh.androidtest.fragment.SecondFragment;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    FrameLayout fl_container;

    //声明本次使用到的java类
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FirstFragment firstFragment;
    SecondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_second);
        for (int i=0; i<1000; i++){
            Log.i("--i--", "--i--" + i);
//            tv.setText(i);
        }
        fl_container = findViewById(R.id.fl_container);

        fragmentManager = getSupportFragmentManager();
        /*FragmentManager要管理fragment（添加，替换以及其他的执行动作）
         *的一系列的事务变化，需要通过fragmentTransaction来操作执行
         */
        fragmentTransaction = fragmentManager.beginTransaction();
        //实例化要管理的fragment
        firstFragment = new FirstFragment();
        //通过添加（事务处理的方式）将fragment加到对应的布局中
        fragmentTransaction.add(R.id.fl_container, firstFragment);
        //事务处理完需要提交
        fragmentTransaction.commit();

        secondFragment = new SecondFragment();

        tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.hide(firstFragment).add(R.id.fl_container, secondFragment).commit();
            }
        });

    }
}
