package cn.sh.ideal.ydlutil;

import android.app.Application;

import java.util.HashMap;

import cn.sh.ideal.util.DensityUtil;
import cn.sh.ideal.util.SPUtil;
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


        HashMap<String, String> saveObject = new HashMap<>();
        saveObject.put("1", "213123");


        SPUtil.save("123", saveObject);


        Object o = SPUtil.get("123", null);

        System.out.println(o);


        SPUtil.save("int", 123);
        System.out.println(SPUtil.get("int", -1));

        SPUtil.save("long", 123L);
        System.out.println(SPUtil.get("long", -1L));

        SPUtil.save("string", "123456");
        System.out.println(SPUtil.get("string", null));


        int i = DensityUtil.dip2px(this, 10);
        int j = DensityUtil.dp2px(this, 10);

        System.out.println(i+"==="+j);


    }
}
