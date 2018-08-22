package cn.sh.ideal.util.face;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.util.Log;
import android.view.View;

import cn.sh.ideal.ydlutil.R;

/**
 * @author yindalei
 *
 *
 * 检测图 显示框
 */
public class MyFaceView extends View {


    private int imageWidth, imageHeight;

    /**
     *   最大检测的人脸数
     */
    private int numberOfFace = 50;
    /**
     * 人脸识别类的实例
     */
    private FaceDetector        myFaceDetect;
    /**
     * 存储多张人脸的数组变量
     */
    private FaceDetector.Face[] myFace;
    /**
     * 两眼之间的距离
     */
    float  myEyesDistance;
    /**
     * 实际检测到的人脸数
     */
    int    numberOfFaceDetected;
    Bitmap myBitmap;


    public MyFaceView(Context context) {
        super(context);
        BitmapFactory.Options options = new BitmapFactory.Options();
        //构造位图生成的参数，必须为565。类名+enum
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ddd, options);


        imageWidth = myBitmap.getWidth();
        imageHeight = myBitmap.getHeight();

        //分配人脸数组空间
        myFace = new FaceDetector.Face[numberOfFace];
        myFaceDetect = new FaceDetector(imageWidth, imageHeight, numberOfFace);
        //FaceDetector 构造实例并解析人脸
        numberOfFaceDetected = myFaceDetect.findFaces(myBitmap, myFace);
        Log.i("APP", "numberOfFaceDetected is " + numberOfFaceDetected);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画出位图
        canvas.drawBitmap(myBitmap, 0, 0, null);

        Paint myPaint = new Paint();
        myPaint.setColor(Color.GREEN);
        myPaint.setStyle(Paint.Style.STROKE);
        //设置位图上paint操作的参数
        myPaint.setStrokeWidth(3);

        for (int i = 0; i < numberOfFaceDetected; i++) {
            FaceDetector.Face face = myFace[i];
            PointF myMidPoint = new PointF();
            face.getMidPoint(myMidPoint);
            //得到人脸中心点和眼间距离参数，并对每个人脸进行画框
            myEyesDistance = face.eyesDistance();

            //矩形框的位置参数
            canvas.drawRect(
                    (int) (myMidPoint.x - myEyesDistance),
                    (int) (myMidPoint.y - myEyesDistance),
                    (int) (myMidPoint.x + myEyesDistance),
                    (int) (myMidPoint.y + myEyesDistance),
                    myPaint);
        }


    }
}