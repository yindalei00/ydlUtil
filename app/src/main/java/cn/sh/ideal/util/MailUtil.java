package cn.sh.ideal.util;


import android.os.Build;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author yindalei
 * @date 2017/6/6
 *
 * 邮件发送工具类
 */
public class MailUtil {


    private static String myEmailAccount  = "a@22sj.cn";
    private static String myEmailPassword = "1234!@#$b";
    private static String senderName      = Build.MODEL;
    private static String mailServer      = "smtp.mxhichina.com";


    private static Properties props;

    static {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        // 参数配置
        props = new Properties();
        // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.transport.protocol", "smtp");
        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", mailServer);
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");

    }

    /**
     * 发送邮件 方法
     * @param subject 主题
     * @param msg 内容
     * @param receiver 接收者 多个接收者可以用','  逗号 分割
     */
    public static void send(String subject, String msg, String receiver) throws Exception {

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(false);
        // 3. 创建一封邮件

        MimeMessage message = createMimeMessage(session, subject, msg, receiver);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();


    }
    /**
     * 发送邮件 方法
     * @param subject 主题
     * @param text 内容
     * @param receiver 接收者 多个接收者可以用','  逗号 分割
     */
    public static void sendSSL(String subject, String text, String receiver) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmailAccount, myEmailPassword);
            }
        });


        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(myEmailAccount, senderName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));

        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(text, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        //发送邮件
        Transport.send(message);


    }



    /**
     * 创建一封只包含文本的简单邮件
     */
    private static MimeMessage createMimeMessage(Session session, String subject, String msg, String received) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(myEmailAccount, senderName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(received, received, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(msg, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }


}  