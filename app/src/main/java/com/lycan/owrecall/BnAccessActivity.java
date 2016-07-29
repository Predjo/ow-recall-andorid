package com.lycan.owrecall;

import android.app.Activity;
import android.os.Bundle;

import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;

public class BnAccessActivity extends BnOAuthAccessTokenActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bn_access);
    }
}
