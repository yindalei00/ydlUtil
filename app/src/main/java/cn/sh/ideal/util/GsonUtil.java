package cn.sh.ideal.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yindalei
 * @date 2017/6/6
 *  Gson 工具类  json转换
 */
public class GsonUtil {


    private GsonUtil() {
    }

    /**
     * 转成json
     *
     * @param object 对象
     * @return 字符串
     */
    public static String Gson2String(Object object) {
        String gsonString;
        Gson gson = new GsonBuilder().create();
        gsonString = gson.toJson(object);
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString 字符串
     * @param cls 类型 .Class
     * @return 对象
     */
    public static <T> T Gson2Bean(String gsonString, Class<T> cls) {
        T t = null;
        Gson  gson = new GsonBuilder().create();
        t = gson.fromJson(gsonString, cls);

        return t;
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString 字符串
     * @param cls 类型 .Class
     * @return 集合
     */
    public static <T> List<T> Gson2List(String gsonString, Class<T> cls) {
        List<T> list = null;
        Gson gson = new GsonBuilder().create();
        list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());

        return list;
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json 字符串
     * @param cls class
     * @param <T> 泛型
     * @return 集合
     */
    public <T> List<T> json2List(String json, Class<T> cls) {
        Gson gson = new GsonBuilder().create();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }


    /**
     * 转成list中有map的
     *
     * @param gsonString 字符串
     * @return list
     */
    public static <T> List<Map<String, T>> Gson2ListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = new GsonBuilder().create();
        list = gson.fromJson(gsonString,
                new TypeToken<List<Map<String, T>>>() {
                }.getType());

        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString 字符串
     * @return map
     */
    public static <T> Map<String, T> Gson2Maps(String gsonString) {
        Map<String, T> map = null;
        Gson   gson = new GsonBuilder().create();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());

        return map;
    }
}