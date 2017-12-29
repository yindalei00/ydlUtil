package cn.sh.ideal.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create By yindalei at 2017/3/26.14:29
 *
 * @author yindalei
 * @描述 ${TODO}
 */

public class Md5Util {

        /**
         * md5加密方法
         * @param s
         * @return
         */
        public static String md5(String s) {

            try {
                // 得到一个信息摘要器
                MessageDigest digest = MessageDigest.getInstance("md5");
                byte[] result = digest.digest(s.getBytes());
                StringBuilder buffer = new StringBuilder();
                // 把没一个byte 做一个与运算 0xff;
                for (byte b : result) {
                    // 与运算
                    int number = b & 0xff;
                    String str = Integer.toHexString(number);
                    if (str.length() == 1) {
                        buffer.append("0");
                    }
                    buffer.append(str);
                }
                // 标准的md5加密后的结果
                return buffer.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return "";
            }

        }
    }
