package cn.sh.ideal.util;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Create at 2017/12/29.16:34
 *
 * @author yindalei
 */

public class YdlUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;


    public static void init(Context context) {
         sContext = context;

        SPUtil.init(context);


    }




}
