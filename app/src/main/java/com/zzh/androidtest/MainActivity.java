package com.zzh.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zzh.androidtest.activity.Main2Activity;
import com.zzh.androidtest.activity.SecondActivity;
import com.zzh.androidtest.bean.Student;
import com.zzh.androidtest.broadcast.MyBroadCastReciver;
import com.zzh.androidtest.db.StudentDaoOpe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Main2Activity {

    // test commit
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.btn_query_all)
    Button btnQueryAll;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bt_jump)
    TextView bt_jump;
    MyBroadCastReciver myBroadCastReceiver;

    private static final String BROADCAST_PERMISSION_DISC = "com.cn.customview.permissions.MY_BROADCAST";
    private static final String BROADCAST_ACTION_DISC = "com.abc.mybroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        Log.i("--zoule--", "--onCreate--");
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();

//        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
//        myBroadCastReceiver = new MyBroadCastReciver();
//        // 2. 设置接收广播的类型
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(BROADCAST_ACTION_DISC);
//        // 3. 动态注册：调用Context的registerReceiver（）方法
//        registerReceiver(myBroadCastReceiver, intentFilter);
    }

    private List<Student> studentList = new ArrayList<>();



    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 100; i++) {
            Student student = new Student((long) i, "ahuang" + i, 25,"666"+i);
            studentList.add(student);
        }
    }
    int page;
    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.btn_query_all
            ,R.id.bt_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                StudentDaoOpe.insertData(this, studentList);
                break;
            case R.id.button2:
                Student student = new Student((long) 5, "haung" + 5, 25,"123456");
                /**
                 * 根据特定的对象删除
                 */
                StudentDaoOpe.deleteData(this, student);
                /**
                 * 根据主键删除
                 */
                StudentDaoOpe.deleteByKeyData(this, 7);
                StudentDaoOpe.deleteAllData(this);
                break;
            case R.id.button3:
                student = new Student((long) 2, "caojin", 1314,"888888");
                StudentDaoOpe.updateData(this, student);
                break;
            case R.id.button4:
                List<Student> students = StudentDaoOpe.queryAll(this);
                tvContent.setText(students.toString());
                for (int i = 0; i < students.size(); i++) {
                    Log.i("Log", students.get(i).getName());
                }
                break;
            case R.id.button5:
                StudentDaoOpe.deleteAllData(this);
                break;
            case R.id.btn_query_all:
                List<Student> students2 = StudentDaoOpe.queryPaging(page, 20, this);

                if (students2.size() == 0) {
                    Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
                for (Student st : students2) {
                    Log.e("TAG", "onViewClicked: ==" + st);
                    Log.e("TAG", "onViewClicked: == num = " + st.getNum());
                }
                page++;
                tvContent.setText(students2.toString());
                break;

            case R.id.bt_jump:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//
                overridePendingTransition(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);

                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        Log.i("--zoule--", "--onBackPressed--");

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁在onResume()方法中的广播
        unregisterReceiver(myBroadCastReceiver);
    }
}
