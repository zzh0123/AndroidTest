package com.zzh.androidtest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @Description:
 * @Author: zzh
 * @CreateDate: 2019/11/7$ 15:09$
 */
public class MyBroadCastReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyBroadCastReciver "," -- 接收到的消息 --r = ");
        Toast.makeText(context, "接收到的消息", Toast.LENGTH_SHORT).show();
    }
}
