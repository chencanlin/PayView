package com.ccl.perfectisshit.payview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ccl.perfectisshit.payview.R;
import com.ccl.perfectisshit.payview.util.Utils;

/**
 * Created by ccl on 2017/9/14.
 */

public class KeyboardView extends FrameLayout implements View.OnClickListener {

    private Paint mPaint;
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private OnInputListener mOnInputListener;
    private int mSingleHeight;
    private int mSingleWidth;

    public KeyboardView(Context context) {
        this(context, null);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_keyboard, this, true);
        findViewById(R.id.tv_one).setOnClickListener(this);
        findViewById(R.id.tv_two).setOnClickListener(this);
        findViewById(R.id.tv_three).setOnClickListener(this);
        findViewById(R.id.tv_four).setOnClickListener(this);
        findViewById(R.id.tv_five).setOnClickListener(this);
        findViewById(R.id.tv_six).setOnClickListener(this);
        findViewById(R.id.tv_seven).setOnClickListener(this);
        findViewById(R.id.tv_eight).setOnClickListener(this);
        findViewById(R.id.tv_nine).setOnClickListener(this);
        findViewById(R.id.tv_zero).setOnClickListener(this);
        findViewById(R.id.rl_delete).setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
        mSingleHeight = findViewById(R.id.tv_zero).getMeasuredHeight();
        mSingleWidth = findViewById(R.id.tv_zero).getMeasuredWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(Utils.getScreenSize(getContext(), Utils.KEY_GET_WIDTH), getMeasuredHeight());
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(0xFFE0E0E0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_hide:
            case R.id.rl_delete:
            case R.id.tv_zero:
            case R.id.tv_one:
            case R.id.tv_two:
            case R.id.tv_three:
            case R.id.tv_four:
            case R.id.tv_five:
            case R.id.tv_six:
            case R.id.tv_seven:
            case R.id.tv_eight:
            case R.id.tv_nine:
                if (mOnInputListener != null) {
                    mOnInputListener.onInput(v);
                }
                break;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawBorder(canvas);
        drawGridLine(canvas);
    }

    private void drawGridLine(Canvas canvas) {
        mPaint.setStrokeWidth(1);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(0, Utils.dp2px(getContext(), 20) + mSingleHeight * i, mMeasuredWidth, Utils.dp2px(getContext(), 20) + mSingleHeight * i,mPaint);
        }

        for (int i = 0; i < 2; i++) {
            canvas.drawLine((i+1)*mSingleWidth,Utils.dp2px(getContext(),20),(i+1)*mSingleWidth,mMeasuredHeight, mPaint);
        }
    }

    private void drawBorder(Canvas canvas) {
        mPaint.setStrokeWidth(2);
        canvas.drawRect(0, 0, mMeasuredWidth, mMeasuredHeight, mPaint);
    }

    public interface OnInputListener {
        void onInput(View view);
    }

    public void setInputListener(OnInputListener onInputListener) {
        mOnInputListener = onInputListener;
    }
}
