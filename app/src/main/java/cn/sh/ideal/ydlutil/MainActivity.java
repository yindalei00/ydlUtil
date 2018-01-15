package cn.sh.ideal.ydlutil;

import android.app.Activity;
import android.os.Bundle;

import cn.sh.ideal.util.ToastUtil;
import cn.sh.ideal.util.encrypt.AESUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToastUtil.showShortToast("123");




        String s = "dasdasfdffsfsd";


        System.out.println(AESUtil.encrypt(s));

        System.out.println(AESUtil.decrypt(AESUtil.encrypt(s)));
    }
}
