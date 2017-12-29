package cn.sh.ideal.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author yindalei
 */
public class SPUtil {
    private static Context sContext;
    private static String FILE_NAME = "ydlConfig";


    protected static  void init(Context context) {
        sContext = context;
    }
    /**
     * 保存一个boolean值
     *
     * @param key   key
     * @param value value
     */
    public static void saveBoolean(String key, Boolean value) {
        saveBoolean(sContext, key, value);
    }

    private static void saveBoolean(Context context, String key, Boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    /**
     * 读取一个boolean值
     *
     * @param key      存储key
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getBoolean(sContext, key, defValue);
    }


    private static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defValue);
    }


    /**
     * 保存一个字符串
     *
     * @param key   key
     * @param value value
     */
    public static void saveString(String key, String value) {
        saveString(sContext, key, value);
    }

    private static void saveString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    /**
     * 根据key 获取字符串
     *
     * @param key key
     * @return 根据key得到的值
     */
    public static String getString(String key) {
        return getString(sContext, key);
    }

    public static String getString(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }
}
