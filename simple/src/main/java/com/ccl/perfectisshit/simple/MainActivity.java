package com.ccl.perfectisshit.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
        findViewById(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayActivity();
            }
        });
    }

    private void startPayActivity() {
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.common_activity_start_slide_in, R.anim.common_activity_start_slide_out);
    }
}
