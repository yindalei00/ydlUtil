package cn.sh.ideal.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yindalei
 *         <p>
 *         时间工具类
 */

@SuppressWarnings("WeakerAccess")
@SuppressLint("SimpleDateFormat")
public class DateUtil {

    /**
     * 默认时间格式化 样式
     */
    private static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 返回时间戳 long
     * @return 返回时间戳
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 按照指定的文本格式返回当前时间。默认格式：yyyy-MM-dd HH:mm:ss
     * @return 格式后时间
     */
    public static String getTime() {

        return getTime(DEFAULT_FORMAT);
    }

    /**
     * 按照指定的文本格式返回当前时间。默认格式：yyyy-MM-dd HH:mm:ss
     * @param format 时间格式
     * @return 格式后时间
     */
    public static String getTime(String format) {
        if (TextUtils.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        return new SimpleDateFormat(format).format(System.currentTimeMillis());
    }

    /**
     * 时间戳格式化
     * @param timestamp 时间戳
     * @return 格式化之后的字符串
     */
    public static String format(Object timestamp) {

        return format(timestamp, DEFAULT_FORMAT);
    }

    /**
     * 时间戳格式化
     * @param timestamp 时间戳
     * @param format    时间格式
     * @return 格式化之后的字符串
     */
    public static String format(Object timestamp, String format) {

        if (format == null || "".equals(format)) {
            format = DEFAULT_FORMAT;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        if (timestamp instanceof String) {
            if (((String) timestamp).matches("^[0-9]*$")) {
                return dateFormat.format(Long.parseLong((String) timestamp));
            } else {
                throw new RuntimeException("时间戳数据错误，错误数据为:" + timestamp);
            }
        } else if (timestamp instanceof Date || timestamp instanceof Number) {
            return dateFormat.format(timestamp);
        } else {
            throw new RuntimeException("时间戳类型错误,错误类型为:" + timestamp.getClass());
        }

    }

}
