package com.example.android.littleblack;

/**
 * Created by Eric Ju on 2016/9/17.
 */
import com.example.android.littleblack.GestureUtils;
import com.example.android.littleblack.GestureUtils.Screen;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class BuileGestureExt {
    public static final int GESTURE_UP=0;
    public static final int GESTURE_DOWN=1;
    public static final int GESTURE_LEFT=2;
    public static final int GESTURE_RIGHT=3;
    private OnGestureResult onGestureResult;
    private Context mContext;

    public BuileGestureExt(Context c, OnGestureResult onGestureResult) {
        this.mContext=c;
        this.onGestureResult=onGestureResult;
        screen = GestureUtils.getScreenPix(c);
    }
    public GestureDetector Buile()
    {
        return new GestureDetector(mContext, onGestureListener);
    }

    private Screen screen;
    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener(){

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            int result = -1;
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            //限制必须得划过屏幕的1/4才能算划过
            float x_limit = screen.widthPixels / 4;
            float y_limit = screen.heightPixels / 4;
            float x_abs = Math.abs(x);
            float y_abs = Math.abs(y);
            if(x_abs >= y_abs){
                //gesture left or right
                if(x > x_limit || x < -x_limit){
                    if(x>0){
                        //right
                        result = GESTURE_RIGHT;
                    }else if(x<=0){
                        //left
                        result = GESTURE_LEFT;
                    }
                }
            }else{
                //gesture down or up
                if(y > y_limit || y < -y_limit){
                    if(y>0){
                        //down
                        result = GESTURE_DOWN;
                    }else if(y<=0){
                        //up
                        result = GESTURE_UP;
                    }
                }
            }
            EventResult r = new EventResult(EventResult.FILING,result);
            doResult(r);
            return true;
        }
        @Override
        public boolean onDown(MotionEvent e) {
            EventResult r = new EventResult(EventResult.DOWN,0);
            doResult(r);
            return true;
        }

    };


    public void doResult(EventResult r)
    {
        if(onGestureResult!=null)
        {
            switch (r.action) {
                case EventResult.FILING:
                    onGestureResult.onFilingResult(r.result);
                    break;
                case EventResult.DOWN:
                    onGestureResult.onDownResult();
                    break;
                default:
                    break;
            }

        }
    }

    public interface OnGestureResult
    {
        public void onFilingResult(int direction);
        public void onDownResult();
    }
    class EventResult {

        public static final int FILING = 0;
        public static final int DOWN = 1;

        EventResult(int act,int rst) {
            action = act;
            result = rst;
        }

        int action;
        int result;
    }
}