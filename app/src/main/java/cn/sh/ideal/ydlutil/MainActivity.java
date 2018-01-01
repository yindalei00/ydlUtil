package cn.sh.ideal.ydlutil;

import android.app.Activity;
import android.os.Bundle;

import cn.sh.ideal.util.SPUtil;
import cn.sh.ideal.util.ToastUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToastUtil.showShortToast("123");

    }
}
