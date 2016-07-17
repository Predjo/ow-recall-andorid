package com.lycan.owrecall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

public class LoginActivity extends AppCompatActivity {

    private Button btnBattlenetLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Create BnOAuthParams to send
        final BnOAuth2Params bnOAuth2Params = new BnOAuth2Params(
                getString(R.string.battlenet_key),
                getString(R.string.battlenet_secret),
                BnConstants.ZONE_EUROPE,
                getString(R.string.battlenet_redirect),
                getString(R.string.battlenet_app_name)
        );

        btnBattlenetLogin = (Button) findViewById(R.id.btn_login);

        // Launch webview to connect in Battlenet
        btnBattlenetLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startOauthFlow(bnOAuth2Params);
            }
        });
    }

    /**
     * Starts the activity that takes care of the OAuth2 flow
     *
     */
    private void startOauthFlow(final BnOAuth2Params bnOAuth2Params) {
        final Intent intent = new Intent(this, BnOAuthAccessTokenActivity.class);
        // Send BnOAuth2Params
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        // Send redirect Activity
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, DestinyActivity.class);
        startActivity(intent);
    }

}
