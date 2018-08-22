package cn.sh.ideal.util.face;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import cn.sh.ideal.util.DensityUtil;
import cn.sh.ideal.util.display.DisplayUtil;


/**
 * 覆盖层  绘制人脸框 及 其他信息
 *
 * @author yindalei
 */
public class FaceView extends View {

    Context mContext;

    Paint mLinePaint, mTextPaint, mPointPaint;
//    FaceInfo[] faceInfos;
    private int faceNum;
    int textSize = 22;
    private int surfaceW, surfaceH;
    double scale;
    
    int frameWidth = 480;
    int frameHight = 640;

    public void setSurfaceWH(int surfaceW, int surfaceH, int frameWidth, int frameHight) {
        this.surfaceW = surfaceW;
        this.surfaceH = surfaceH;
        this.frameWidth = frameWidth;
        this.frameHight = frameHight;
        scale = surfaceW * 1d / frameWidth;

    }

    /**
     * setFaces:设置人脸框和人脸关键点
     *
     * @param faceInfos  FaceInfo
     * @param faceNum 人脸数
     *
     */
   /* public void setFaces(FaceInfo[] faceInfos, int faceNum) {
        this.faceInfos = faceInfos;
        this.faceNum = faceNum;

        invalidate();
    }*/

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        initPaint();

    }

    private void initPaint() {
        textSize = DensityUtil.dip2px(mContext, 16);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // int color = Color.rgb(0, 150, 255);
        int color = Color.rgb(98, 212, 68);
        mLinePaint.setColor(color);
        mLinePaint.setStyle(Style.STROKE);
        mLinePaint.setStrokeWidth(5f);
        mLinePaint.setAlpha(180);

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setColor(color);
        mPointPaint.setStrokeWidth(10f);
        mPointPaint.setAlpha(180);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(color);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setAlpha(180);

    }

    @Override
    protected void onDraw(Canvas canvas) {

      /*  if (faceInfos != null) {

            for (int i = 0; i < faceNum; i++) {

                FaceInfo faceInfo = faceInfos[i];
                // 人脸坐标转换

                int left = (int) (faceInfo.x * scale);
                int top = (int) (faceInfo.y * scale);

                int right = (int) ((faceInfo.width + faceInfo.x) * scale);
                int bottom = (int) ((faceInfo.height + faceInfo.y) * scale);
                StringBuilder sb = new StringBuilder();
                sb
                // .append("追:")
//                        .append(faceInfo.trackId)
                        .append("质:")
                        .append(faceInfo.keyptScore);

//                				sb.append("x: " + faceInfo.x);
//                				sb.append("y: " + faceInfo.y);

                canvas.drawText(sb.toString(), left, top - textSize, mTextPaint);
                canvas.drawPoint(left, top, mTextPaint);
                canvas.drawPoint(right, right, mTextPaint);
                canvas.drawRect(new Rect(left, top, right, bottom), mLinePaint);
            }
            //
        }*/
    }

}
