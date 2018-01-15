package cn.sh.ideal.util.encrypt;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {

    private final static String KEY = "asdsadadasdsadad";

    private static byte[] iv = "0392036201512080".getBytes();



    public static byte[] subBytes(byte[] src, int begin, int count) {
        int newCount = count ;
        int newBegin = begin ;
        if(count < 0){
            newCount = 0;
        }
        if(begin < 0){
            newBegin = 0;
        }
        if(src == null){
            return new byte[newCount];
        }
        byte[] bs = new byte[newCount];
        int max = (src.length - newBegin) > (newBegin+newCount) ? (newBegin+newCount) : (src.length - newBegin);
        for (int i=newBegin; i<max; i++) {
            bs[i - newBegin] = src[i];
        }
        return bs;
    }

    /**
     * 生成16位的aeskey
     * @return
     */
    public static String generateAesKey() {
        String aesKey = "";
        try {
            String uuid= UUID.randomUUID().toString();
            if(!TextUtils.isEmpty(uuid)){
                aesKey = uuid.replace("-", "");
                if(aesKey.length()>=16){
                    aesKey = aesKey.substring(0,16) ;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return aesKey;
    }


    /**
     * 使用默认秘钥加密
     * @param content 内容
     * @return 加密完
     */
    public static String encrypt(String content) {

        return encryptAesNew(content, KEY);
    }
    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @return 密文
     */
    public static String encryptAesNew(String content, String password) {
        try {

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] byteContent = content.getBytes("utf-8");

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key,ivspec);
            // 加密
            byte[] result = cipher.doFinal(byteContent);


            return Base64.encodeToString(result, Base64.DEFAULT);

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密AES加密过的字符串
     * @param content 内容
     * @return 结果
     */
    public static String decrypt(String content) {
        return  decryptAesNew(content,KEY);
    }
    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public static String decryptAesNew(String content, String password) {
        try {

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(), "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKey,ivspec);



            byte[] decode = Base64.decode(content.getBytes(), Base64.DEFAULT);

            byte[] result = cipher.doFinal(decode);
            return new String(result,"utf-8");
            // 明文
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        String s = "BWssZkiTWmLEyPcPVlDxFeKrp7YfyIKz7wMzA0x6cV27VkLMl2U8cMz6ZuLfcMYh";


//        System.out.println(AESUtil.decrypt(s));

        String s1 = "4WKTnlXgtjsjh4UmHrG3ee6j3t1FhxgWAgroxaNyaA98CphGQPXN2tkcnFkgKXU0YinpiAvl9KfkHvD+aQyycw==";

        System.out.println(AESUtil.decrypt(s1));


        /**
         *
         * 用户  点击  登录页 免密登录 按钮
         *      1. 打开免密sdk  登录页面
         *      2. 接收 登录结果回调 (accessToken)
         *      3. 如果 成功,使用 accessToken 换取 手机号,否则 结束
         *      4. 调取  后台 根据手机号 获取邮箱
         *          4.1 如果已绑定 随锐邮箱账号,则 调取 随锐后台 登录,进入到主界面
         *          4.2 否则 弹出 绑定页面,页面 包含 账号,密码,绑定 按钮
         *              4.2.1如果成功 ,调取  后台 绑定邮箱  ,进入 主界面
         *              4.2.2 否则 弹出错误提示
         *
         *
         *
         *
         *
         *
         */






    }

}