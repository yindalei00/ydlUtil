package cn.sh.ideal.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class HttpUtil {

    public static String doPostWithJson(String url, String request) {


        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return doPost(url, request, headers);
    }


    public static String doPostWithForm(String url, String request) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        return doPost(url, request, headers);
    }


    public static String doPost(String url, String request, Map<String, String> headers) {

        PrintWriter outPrintWriter = null;
        BufferedReader br = null;
        try {
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {

                    conn.setRequestProperty(entry.getKey(), entry.getValue());


                }

            }
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            conn.connect(); // 建立实际的连接
            // 获取URLConnection对象对应的输出流
            outPrintWriter = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            outPrintWriter.print(request);
            outPrintWriter.flush();
            // 定义BufferedReader输入流来读取URL的响应
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            closeIO(outPrintWriter, br);

        }


    }

    /**
     * 下载 图片 到指定位置
     * @param url 图片url
     * @param file 位置
     */
    public static void downloadImage(String url, File file) {

        //创建父目录
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            // 打开和URL之间的连接
            URLConnection urlConnection = null;
            urlConnection = new URL(url).openConnection();

            // 设置通用的请求属性
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(300000);
            urlConnection.setReadTimeout(300000);

            urlConnection.connect(); // 建立实际的连接

            bos = new BufferedOutputStream(new FileOutputStream(file));
            bis = new BufferedInputStream(urlConnection.getInputStream());

            int len;
            byte[] bytes = new byte[8096];
            while ((len = bis.read(bytes, 0, bytes.length)) != -1) {
                bos.write(bytes, 0, len);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            closeIO(bis, bos);
        }


    }


    /**
     * 关闭流
     * @param io io流
     */
    private static void closeIO(Closeable... io) {

        for (Closeable closeable : io) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 发送消息给我的微信
     * @param title 标题  最长为256，必填。
     * @param text 内容  最长64Kb，可空，支持MarkDown。
     */
    public static void sendToWX(String title, String text) {

        String url = "https://sc.ftqq.com/SCU4341T5de033ff26f64a33564e4513461729065846d69999155.send";

        HttpUtil.doPostWithForm(url, "text=" + title + "&desp=" + text);

    }
}