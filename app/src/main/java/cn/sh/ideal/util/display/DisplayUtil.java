package cn.sh.ideal.util.display;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 *
 *
 * 显示器 工具类
 *
 * @author yindalei
 * @date 2018/07/06
 *
 *
 * 多屏显示时 使用
 *
 *  创建 Presentation 对象
 *
 *
 *
 *
 *
 */
public class DisplayUtil {


    /**
     *  获取显示器 分辨率大小
     *
     * @param context  context
     * @return Point
     */
    public static Point getSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;

        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;

    }


    /**
     *  获取显示器 分辨率大小
     *
     * @param context  context
     * @return Point
     */
    public static Point getSizeNew(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;

        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        Point size = new Point();
        size.x = dm.widthPixels;
        size.y = dm.heightPixels;
        return size;
    }

    /**
     * 获取所有显示器
     * @param context   context
     * @return 所有显示器
     */
    public static Display[] getDisplays(Context context) {
        DisplayManager mDisplayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);

        return mDisplayManager == null ? null : mDisplayManager.getDisplays();
    }


    /**
     * Presentation 显示
     * @param presentation  presentation
     */
    public static void showPresentation(Presentation presentation) {

        if (presentation.getWindow() != null) {
            presentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            presentation.show();
        }

    }

    /**
     * presentation 隐藏
     * 测试中发现 调用 会闪一下  主屏画面,所以在 onDestroy 里调用,
     *
     * @param presentation  presentation
     */
    public static void hidePresentation(Presentation presentation) {

        if (presentation != null) {
            presentation.cancel();
        }
    }

}
