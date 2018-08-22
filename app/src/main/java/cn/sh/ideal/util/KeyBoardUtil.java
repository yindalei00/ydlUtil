package cn.sh.ideal.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 键盘管理
 *
 */

public class KeyBoardUtil {


    /**
     * 隐藏 虚拟键
     *
     * 用于 隐藏 虚拟键
     * @param activity  activity
     */
    public static void hideVirtualKeybord(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }



    /**
     * 强制隐藏输入法键盘
     *
     * 该方法会关闭所有控件弹出的软键盘；
     */
    public static void hideKeybord(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;

        if (imm.isActive()) {
            imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    /**
     * 隐藏输入法键盘
     *
     * 该方法只关闭指定EditText控件弹出的软键盘
     */
    public static void hideKeybord(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;

        imm.hideSoftInputFromInputMethod(editText.getWindowToken(),0);

    }


    /**
     * 输入法是否显示
     *
     */
    public static boolean isShowing(EditText edittext) {

        InputMethodManager imm = (InputMethodManager) edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;

        return imm.isActive();
    }



    /**
     * 显示输入法
     *
     * @param mAct activity
     */
    public static void showInputMethod( Activity mAct) {
        View v = mAct.getCurrentFocus();
        if (null == v) {
            return;
        }
        ((InputMethodManager) mAct.getSystemService(Activity.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
    }

}
