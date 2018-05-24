package cn.sh.ideal.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;


public class HttpUtil {


    public static String doHttpPost(String url, String req) {
        PrintWriter outPrintWriter = null;
        BufferedReader inBufferedReader = null;
        try {
            // 打开和URL之间的连接
            URLConnection urlConnection = new URL(url).openConnection();
            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 必须设置如下两行
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(10 * 1000);

            urlConnection.connect(); // 建立实际的连接
            // 获取URLConnection对象对应的输出流
            outPrintWriter = new PrintWriter(urlConnection.getOutputStream());
            // 发送请求参数
            outPrintWriter.print(req);
            outPrintWriter.flush();
            // 定义BufferedReader输入流来读取URL的响应
            inBufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(), "utf-8"));
            String line = "";
            String response = "";
            while ((line = inBufferedReader.readLine()) != null) {
                response += line;
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (outPrintWriter != null) {
                    outPrintWriter.close();
                }
                if (inBufferedReader != null) {
                    inBufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static String doHttpPostForm(String url, String req) {
        PrintWriter outPrintWriter = null;
        BufferedReader inBufferedReader = null;
        try {
            // 打开和URL之间的连接
            URLConnection urlConnection = new URL(url).openConnection();
            // 设置通用的请求属性
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 必须设置如下两行
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(300000);
            urlConnection.setReadTimeout(300000);

            urlConnection.connect(); // 建立实际的连接
            // 获取URLConnection对象对应的输出流
            outPrintWriter = new PrintWriter(urlConnection.getOutputStream());
            // 发送请求参数
            outPrintWriter.print(req);
            outPrintWriter.flush();
            // 定义BufferedReader输入流来读取URL的响应
            inBufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(), "utf-8"));
            String line = "";
            String response = "";
            while ((line = inBufferedReader.readLine()) != null) {
                response += line;
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (outPrintWriter != null) {
                    outPrintWriter.close();
                }
                if (inBufferedReader != null) {
                    inBufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 下载 图片 到指定位置
     * @param url 图片url
     * @param file 位置
     * @throws IOException IO异常
     */
    public static void downloadImage(String url, File file) throws IOException {

        // 打开和URL之间的连接
        URLConnection urlConnection = new URL(url).openConnection();
        // 设置通用的请求属性
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        urlConnection.setDoInput(true);
        urlConnection.setConnectTimeout(300000);
        urlConnection.setReadTimeout(300000);

        urlConnection.connect(); // 建立实际的连接

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());

        int len;
        byte[] bytes = new byte[8096];
        while ((len = bis.read(bytes, 0, bytes.length)) != -1) {
            bos.write(bytes, 0, len);
        }

        bos.close();
        bis.close();


    }

    /**
     * 发送消息给微信
     * @param title 标题  最长为256，必填。
     * @param text 内容  最长64Kb，可空，支持MarkDown。
     */
    public static void sendToWX(String title, String text) {

        String url = "https://sc.ftqq.com/SCU4341T5de033ff26f64a33564e4513461729065846d69999155.send";

        HttpUtil.doHttpPostForm(url, "text=" + title + "&desp=" + text);

    }

}