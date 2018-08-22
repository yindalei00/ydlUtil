package cn.sh.ideal.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.sh.ideal.ydlutil.MainActivity;

/**
 *
 * @author yindalei
 * @date 2018/05/28
 *
 *
 */
public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("OnBootReceiver", intent.getAction());
        Log.d("OnBootReceiver", intent.getAction());

        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.putExtra("1", 123456);
        context.startActivity(intent1);
    }
}
