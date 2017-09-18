package com.ccl.perfectisshit.payview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ccl.perfectisshit.payview.R;
import com.ccl.perfectisshit.payview.adapter.KeyboardAdapter;
import com.ccl.perfectisshit.payview.bean.KeyboardItemBean;
import com.ccl.perfectisshit.payview.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ccl on 2017/9/14.
 */

public class KeyboardView extends FrameLayout {

    private static final int[] KEYBOARD_NUMBER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int INDEX_BLANK_ITEM = 9;
    private RecyclerView mRv;

    private List<KeyboardItemBean> data = new ArrayList<>();
    private KeyboardAdapter mKeyboardAdapter;

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
        initView();
        initData();
        setData();
    }

    private void setData() {
        mKeyboardAdapter = new KeyboardAdapter(data);
        KeyboardRecyclerViewDecoration keyboardRecyclerViewDecoration = new KeyboardRecyclerViewDecoration(getResources().getDrawable(R.drawable.shape_keyboard_rv_line));
        mRv.addItemDecoration(keyboardRecyclerViewDecoration);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRv.setLayoutManager(gridLayoutManager);
        mRv.setAdapter(mKeyboardAdapter);
        mRv.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        List<Integer> numberArray = new ArrayList<>();
        for (int item : KEYBOARD_NUMBER) {
            numberArray.add(item);
        }
        Collections.shuffle(numberArray);
        for (Integer item : numberArray) {
            data.add(new KeyboardItemBean(String.valueOf(item), KeyboardItemBean.KeyboardTag.TAG_NUMBER));
        }
        data.add(INDEX_BLANK_ITEM, new KeyboardItemBean("", KeyboardItemBean.KeyboardTag.TAG_BLANK));
        data.add(new KeyboardItemBean("", KeyboardItemBean.KeyboardTag.TAG_DELETE));
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_keyboard, this, true);
        mRv = ((RecyclerView) findViewById(R.id.rv));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(Utils.getScreenSize(getContext(), Utils.KEY_GET_WIDTH), getMeasuredHeight());
    }

    public interface OnInputListener {
        void onInput(View view);
    }

    public void setInputListener(OnInputListener onInputListener) {
        mKeyboardAdapter.setOnInputListener(onInputListener);
    }
}
