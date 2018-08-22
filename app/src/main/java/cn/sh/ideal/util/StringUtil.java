package cn.sh.ideal.util;

/**
 * @author yindalei
 */
public class StringUtil {


    /**
     * 判断是否为空
     * @param str    String
     * @return 判断是否为空
     */
    public static boolean isEmpty( String str) {
        return str == null || str.length() == 0;
    }


    public static boolean isPhone(String phone) {
        String regex = "[1]\\d{10}";
        return phone.matches(regex);
    }

    /**
     * 判断是否是 邮箱
     * @param email email
     * @return  是否是 邮箱
     */
    public static boolean isEmail(String email) {
        String regex =
                "^" +
                "([a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-])*)" +
                "@" +
                "[a-zA-Z0-9\\-]+" +
                "(\\.[a-zA-Z0-9\\-]+)+" +
                "$";

        return email.matches(regex);
    }


    public static void main(String[] a) {
        System.out.println(isEmail("y.j@q.c.cc"));
    }

}
