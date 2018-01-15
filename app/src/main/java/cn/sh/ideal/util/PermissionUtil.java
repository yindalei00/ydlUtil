package cn.sh.ideal.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Create at 2018/1/9.9:47
 * <p>
 * @author yindalei
 */

public class PermissionUtil {


    public static void reqTakePhoto(Activity activity) {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }

    }

    /**
     * 按需求申请权限
     * @param activity    activity
     * @param permissions 权限
     */
    public static void req(Activity activity, String... permissions) {
        ArrayList<String> ps = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ps.add(permission);
            }
        }

        ActivityCompat.requestPermissions(activity, ps.toArray(new String[ps.size()]), 100);


    }


}
