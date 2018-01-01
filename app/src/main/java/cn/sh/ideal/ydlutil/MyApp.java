package cn.sh.ideal.ydlutil;

import android.app.Application;

import cn.sh.ideal.util.SPUtil;
import cn.sh.ideal.util.ToastUtil;
import cn.sh.ideal.util.YdlUtil;

/**
 * Created by yindalei on 2018/1/1.
 *
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        YdlUtil.init(this);


        System.out.println(this);



    }
}
