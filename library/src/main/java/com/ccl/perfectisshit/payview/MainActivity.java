package com.ccl.perfectisshit.payview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        mTv = ((TextView) findViewById(R.id.tv));

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayActivity();
            }
        });
    }

    private void startPayActivity(){
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.common_activity_start_slide_in, R.anim.common_activity_start_slide_out);
    }
}
