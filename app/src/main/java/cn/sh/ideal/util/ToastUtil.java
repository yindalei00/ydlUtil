package cn.sh.ideal.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


/**
 * @author yindalei
 *
 * 吐司工具类,安全的显示吐司
 */
public class ToastUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    private ToastUtil() {
    }

    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 初始化工具
     * @param context  上下文
     */
    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    /**
     * 显示短时吐司
     *
     * @param text    文本
     */
    public static void showShortToast(CharSequence text) {
        showToast(sContext, text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void showShortToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }


    /**
     * 显示长时吐司
     *
     * @param text    文本
     */
    public static void showLongToast(CharSequence text) {
        showToast(sContext, text, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void showLongToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }

    /**
     * 取消吐司显示
     */
    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

    /**
     * 显示吐司
     *
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context, final CharSequence text, final int duration) {

        if (sContext == null) {
            sContext = context.getApplicationContext();
        }

        sHandler.post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(sContext, text, duration);
                } else {
                    sToast.setText(text);
                    sToast.setDuration(duration);
                }
                sToast.show();

            }
        });


    }


}