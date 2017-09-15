package com.ccl.perfectisshit.simple;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ccl.perfectisshit.payview.widget.KeyboardView;
import com.ccl.perfectisshit.payview.widget.PayView;

/**
 * Created by ccl on 2017/9/14.
 */

public class PayActivity extends Activity {

    private PayView mPayView;
    private KeyboardView mKeyBoardView;

    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        mPayView = findViewById(R.id.pv);
        mKeyBoardView = findViewById(R.id.kbv);
        mPayView.setKeyBoardView(mKeyBoardView);

        findViewById(R.id.tv_switch_mode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                mPayView.setPasswordMode(position % 2);
                Toast.makeText(PayActivity.this, "switch success", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PayActivity.this, mPayView.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(R.anim.common_activity_finish_slide_in, R.anim.common_activity_finish_slide_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
