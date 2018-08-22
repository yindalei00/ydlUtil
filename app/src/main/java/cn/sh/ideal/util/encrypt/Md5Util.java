package cn.sh.ideal.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



// aaaa1

/**
 * @author yindalei
 *
 *  MD5 消息摘要 工具类
 */
public class Md5Util {

    /**
     * md5加密方法
     * @param s string
     * @return 结果
     */
    public static String md5(String s) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("md5").digest(s.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder buffer = new StringBuilder();
        // 把没一个byte 做一个与运算 0xff;
        for (byte b : hash) {
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
    }

}

