package cn.sh.ideal.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * @author yindalei
 *
 *
 * SharedPreferences 工具类
 */
public class SPUtil {
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    /**
     * 存储配置信息的文件名
     */
    private static String DEFAULT_FILE_NAME = "ydlConfig.sp";
    /**
     *   saveO 方法时 默认存储文件名
     *   存储 对象 的 文件名
     */
    private static String OBJECT_FILE_NAME  = "ydlObject.sp";


    public static void init(Context context) {
        sContext = context.getApplicationContext();
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

        if (sContext == null) {
            sContext = context.getApplicationContext();
        }

        SharedPreferences preferences = sContext.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
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

        if (sContext == null) {
            sContext = context.getApplicationContext();
        }

        SharedPreferences preferences = sContext.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
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

        if (sContext == null) {
            sContext = context.getApplicationContext();
        }

        SharedPreferences preferences = sContext.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }


    /**
     * 保存一个 任意类型
     *
     * @param key   key
     * @param value value
     */
    public static void save(String key, Object value) {
        save(sContext, DEFAULT_FILE_NAME, key, value);
    }

    /**
     * 保存一个 任意类型
     *
     * @param fileName   文件名
     * @param key   key
     * @param value value
     */
    public static void save(String fileName, String key, Object value) {
        save(sContext, fileName, key, value);
    }

    /**
     *
     * @param context   context
     * @param key       键
     * @param value     默认值
     */
    private static void save(Context context, String key, Object value) {

        save(context, DEFAULT_FILE_NAME, key, value);
    }

    /**
     *
     * @param context   context
     * @param fileName  文件名
     * @param key       键
     * @param value     默认值
     */
    private static void save(Context context, String fileName, String key, Object value) {

        if (sContext == null) {
            sContext = context.getApplicationContext();
        }
        SharedPreferences preferences = sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Serializable) {
            String string = object2string((Serializable) value);
            edit.putString(key, string);
        }

        edit.apply();

    }


    /**
     * 获取存的 数值
     *
     * @param key key
     * @param defaultValue 默认值
     * @return 根据key得到的值
     */
    public static <T> T get(String key, T defaultValue) {
        return get(sContext, DEFAULT_FILE_NAME, key, defaultValue);
    }

    /**
     * 获取存的 数值
     *
     * @param fileName  文件名
     * @param key       键
     * @param defaultValue         默认值
     * @return 根据key得到的值
     */
    public static <T> T get(String fileName, String key, T defaultValue) {
        return get(sContext, fileName, key, defaultValue);
    }

    /**
     *
     * @param context   context
     * @param key       键
     * @param defaultValue         默认值
     * @return 返回对应 的 值
     */
    public static <T> T get(Context context, String key, T defaultValue) {
        return get(context, DEFAULT_FILE_NAME, key, defaultValue);
    }

    /**
     *
     * @param context   context
     * @param fileName  文件名
     * @param key       键
     * @param defaultValue         默认值
     * @return 返回对应 的 值
     */
    public static <T> T get(Context context, String fileName, String key, T defaultValue) {

        if (sContext == null) {
            sContext = context.getApplicationContext();
        }

        SharedPreferences sp = sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        if (defaultValue instanceof Integer) {

            return (T) (Integer) (sp.getInt(key, (Integer) defaultValue));
        } else if (defaultValue instanceof Long) {
            return (T) (Long) sp.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return (T) (Boolean) sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof String) {
            return (T) sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Float) {
            return (T) (Float) sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Serializable || defaultValue == null) {
            String s = sp.getString(key, null);

            Object object = string2object(s);

            return (T) (object == null ? s : object);


        } else {
            return null;
        }
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

    private static String getString(Context context, String key) {

        if (sContext == null) {
            sContext = context.getApplicationContext();
        }

        SharedPreferences preferences = sContext.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }


    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String object2string(Serializable object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object string2object(String objectString) {

        if (objectString == null) {
            return null;
        }

        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 使用SP保存对象 默认路径
     *
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void saveO(String key, Serializable saveObject) {

        saveO(OBJECT_FILE_NAME, key, saveObject);
    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param fileName   储存文件的文件名
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void saveO(String fileName, String key, Serializable saveObject) {

        String string = object2string(saveObject);

        SharedPreferences sharedPreferences = sContext.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, string);
        editor.apply();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param key 储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object getO(String key) {
        return getO(OBJECT_FILE_NAME, key);
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param fileName 储存文件的文件名
     * @param key      储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object getO(String fileName, String key) {
        SharedPreferences sharedPreferences = sContext.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, null);
        if (string != null) {
            return string2object(string);
        } else {
            return null;
        }
    }


}
