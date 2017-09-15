package com.ccl.perfectisshit.payview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.util.SparseArrayCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ccl.perfectisshit.payview.R;
import com.ccl.perfectisshit.payview.util.Utils;

import static com.ccl.perfectisshit.payview.util.Utils.dp2px;


/**
 * Created by ccl on 2017/9/14.
 */

public class PayView extends View {
    private Paint mPaint;

    private static final int KEY_GET_WIDTH = 0;
    private static final int KEY_GET_HEIGHT = 1;
    public static final int MODE_CIPHER_TEXT = 0;
    public static final int MODE_PLAIN_TEXT = 1;
    private static final int CIPHER_TEXT_CIRCLE_RADIU = 20;
    private int mMeasureWidth;
    private int mMeasuredHeight;
    private KeyboardView mKeyBoardView;

    private String password = "";
    private int passwordMode;
    private int mSingleWidth;

    private SparseArrayCompat centerPoint = new SparseArrayCompat();

    public PayView(Context context) {
        this(context, null);
    }

    public PayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PayView);
        passwordMode = typedArray.getInt(R.styleable.PayView_passwordMode, MODE_CIPHER_TEXT);
        typedArray.recycle();
        init();
    }

    private void init() {
        setBackgroundColor(getResources().getColor(android.R.color.white));
        setPadding(0, 0, 0, 0);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMeasureWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
        mSingleWidth = mMeasureWidth / 6;
        for (int i = 0; i < 6; i++) {
            centerPoint.put(i, new Point(mSingleWidth / 2 + mSingleWidth * i, mMeasuredHeight / 2));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, dp2px(getContext(), 50));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBorderLine(canvas);
        drawSplitLine(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        if (password.length() != 0) {
            for (int i = 0; i < password.length(); i++) {
                mPaint.setColor(getResources().getColor(android.R.color.black));
                mPaint.setStyle(Paint.Style.FILL);
                Point point = (Point) centerPoint.get(i);
                if (passwordMode == MODE_CIPHER_TEXT) {
                    canvas.drawCircle(point.x, point.y, CIPHER_TEXT_CIRCLE_RADIU, mPaint);
                } else {
                    mPaint.setTextSize(Utils.sp2Px(getContext(), 15));
                    Rect rect = new Rect();
                    String charAt = String.valueOf(password.charAt(i));
                    mPaint.getTextBounds(charAt, 0, charAt.length(), rect);
                    canvas.drawText(String.valueOf(password.charAt(i)), point.x - rect.width() / 2, point.y + rect.height()/2, mPaint);
                }
            }
        }
    }

    private void drawSplitLine(Canvas canvas) {
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getContext().getResources().getColor(R.color.inputBorderColor));
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(mSingleWidth + mSingleWidth * i, 0, mSingleWidth + mSingleWidth * i, mMeasuredHeight, mPaint);
        }
    }

    private void drawBorderLine(Canvas canvas) {
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getContext().getResources().getColor(R.color.inputBorderColor));
        canvas.drawRoundRect(0, 0, mMeasureWidth, mMeasuredHeight, 5, 5, mPaint);
    }

    public void setKeyBoardView(KeyboardView keyBoardView) {
        mKeyBoardView = keyBoardView;
        if (mKeyBoardView != null) {
            mKeyBoardView.setInputListener(new KeyboardView.OnInputListener() {
                @Override
                public void onInput(View view) {
                    if (view.getId() == R.id.rl_delete) {
                        if (password.length() != 0) {
                            password = password.substring(0, password.length() - 1);
                        }
                    } else {
                        if (password.trim().length() == 6) {
                            return;
                        }
                        password += ((TextView) view).getText().toString();
                    }
                    postInvalidate();
                }
            });
        }
    }

    public void setPasswordMode(int mode){
        passwordMode = mode;
        postInvalidate();
    }

    public String getPassword(){
        return password;
    }
}
