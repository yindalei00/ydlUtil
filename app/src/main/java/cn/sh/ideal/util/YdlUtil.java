package cn.sh.ideal.util;

import android.content.Context;

/**
 * Create at 2017/12/29.16:34
 *
 * @author yindalei
 */

public class YdlUtil {



    public static void init(Context context) {

        SPUtil.init(context.getApplicationContext());
        ToastUtil.init(context);


    }




}
