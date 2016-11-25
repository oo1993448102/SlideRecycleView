package com.trailer.sliderecycleview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by EchoZhou on 2016/11/24.
 * 支持横向滑动LinearLayout
 */
public class MyLinearLayout extends LinearLayout {

    private int maxLength;
    private int mStartX = 0;
    private MyLinearLayout itemLayout;
    private int xDown, xMove, yDown, yMove, mTouchSlop;
    private Scroller mScroller;
    private boolean isOpen = true;

    public MyLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //修改右侧区域大小
    private void init(Context context) {
        //滑动到最小距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //滑动的最大距离
        maxLength = ((dp2px(90)));
        //初始化Scroller
        mScroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }


    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                xDown = x;
                yDown = y;
                itemLayout = this;
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                Log.i("touch", "move");

                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;

                if (Math.abs(dy) < mTouchSlop * 2 && Math.abs(dx) > mTouchSlop) {
                    int scrollX = itemLayout.getScrollX();
                    int newScrollX = mStartX - x;
                    if (newScrollX < 0 && scrollX <= 0) {
                        newScrollX = 0;
                    } else if (newScrollX > 0 && scrollX >= maxLength) {
                        newScrollX = 0;
                    }
                    itemLayout.scrollBy(newScrollX, 0);
                    Log.i("newScrollX", newScrollX + "");
                    Log.i("x", x + "");
                    if(newScrollX>0){
                        isOpen = true;
                    }else{
                        isOpen = false;
                    }

                }
            }
            break;
            case MotionEvent.ACTION_UP: {
                Log.i("touch", "up");
                int scrollX = itemLayout.getScrollX();
                if(isOpen) {
                    if (scrollX >= maxLength / 2) {
                        itemLayout.scrollTo(maxLength, 0);
                    } else {
                        itemLayout.scrollTo(0, 0);
//                    mScroller.startScroll(scrollX, 0, -scrollX, 0);
                        invalidate();
                    }
                }
                else {
                    itemLayout.scrollTo(0, 0);
                }
            }
            break;
        }
        mStartX = x;
        return super.onTouchEvent(event);
    }

    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            itemLayout.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
