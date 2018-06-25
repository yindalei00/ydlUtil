package cn.sh.ideal.ydlutil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.tv)
    TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getIntent()==null) {
            return;
        }

        int i = getIntent().getIntExtra("1", -1);
        if (i>0) {

            mTv.setText("开机启动的");
        }


    }
}
