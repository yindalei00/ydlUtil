package cn.sh.ideal.util.media;

import android.content.Context;
import android.media.MediaPlayer;


/**
 *
 * @author yindalei
 * @date 2018/07/26
 *
 *
 */
public class MP3Player implements MediaPlayer.OnCompletionListener {

    private static MP3Player sMP3Player = new MP3Player();

    private MP3Player() {
    }

    public static MP3Player getInstance() {
        return sMP3Player;
    }


    public void playTip(Context context) {

 /*       MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.di);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(this);*/

    }



    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        mp.release();
        mp = null;
    }
}
