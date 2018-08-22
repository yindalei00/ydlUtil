package cn.sh.ideal.ydlutil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sh.ideal.util.ntp.NtpUtil;

public class MainActivity extends Activity {

    @BindView(R.id.tv)
    TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getIntent() == null) {
            return;
        }

        int i = getIntent().getIntExtra("1", -1);
        if (i > 0) {

            mTv.setText("开机启动的");
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
                long ntpTime = NtpUtil.getTimeFromNtpServer("192.168.1.55");
                Log.d("MainActivity", "ntpTime:" + ntpTime);

                String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ntpTime));
                System.out.println(nowTime);


                long ntpTime2 = NtpUtil.getTimeFromNtpServer("time3.aliyun.com");
                Log.d("MainActivity", "ntpTime:" + ntpTime2);

                String nowTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ntpTime));
                System.out.println(nowTime2);


            }
        }.start();


    }
}
