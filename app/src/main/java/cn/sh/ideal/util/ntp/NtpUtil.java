package cn.sh.ideal.util.ntp;

import android.os.SystemClock;

/**
 *
 * @author yindalei
 * @date 2018/08/17
 *
 *
 *
 *
 */
public class NtpUtil {

    /**
     * 获取时间 从 传入服务器
     * @param ntpServer ntpServer
     * @return 时间戳
     */
    public static long getTimeFromNtpServer(String ntpServer) {

        SntpClient client = new SntpClient();

        boolean isSuccess = client.requestTime(ntpServer, 3000);

        if (isSuccess) {
            return client.getNtpTime()+ SystemClock.elapsedRealtime() - client.getNtpTimeReference();
        }
        return -1;

    }


}
