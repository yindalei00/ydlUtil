package cn.sh.ideal.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Create at 2018/1/9.9:47
 * <p>
 * @author yindalei
 */

public class PermissionUtil {


    /**
     * 请求相机 权限
     * @param activity      activity
     * @param requestCode   requestCode
     */
    public static void reqCamera(Activity activity,int requestCode) {

        req(activity,requestCode, Manifest.permission.CAMERA);

    }

    /**
     * 按需求申请权限
     * @param activity    activity
     * @param requestCode  请求码
     * @param permissions 权限
     */
    @SuppressWarnings("WeakerAccess")
    public static void req(Activity activity, int requestCode, String... permissions) {

        ArrayList<String> ps = new ArrayList<>();

        for (String permission : permissions) {

            if (!isGranted(activity, permission)) {
                ps.add(permission);
            }
        }

        ActivityCompat.requestPermissions(activity, ps.toArray(new String[ps.size()]), requestCode);


    }


    /**
     * 判断是不是已经授权
     */
    private static boolean isGranted(Context context, String permission) {
        return isM()
                && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 判断是不是M及以上版本
     */
    private static boolean isM() {
        return Build.VERSION.SDK_INT >= 23;

    }


}
