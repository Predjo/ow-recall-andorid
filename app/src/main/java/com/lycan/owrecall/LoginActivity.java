package com.lycan.owrecall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btnBattlenetLogin;
    private SharedPreferences sharedPreferences;
    private Spinner regionSelectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        regionSelectSpinner = (Spinner) findViewById(R.id.regionSelect);
        addItemsToRegionSelectSpinner(regionSelectSpinner);

        btnBattlenetLogin = (Button) findViewById(R.id.btn_login);

        // Launch webview to connect in Battlenet
        btnBattlenetLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startOauthFlow();
                }
        });

        //clearCredentials();
    }

    /**
     * Starts the activity that takes care of the OAuth2 flow
     *
     */
    private void startOauthFlow() {

        final Intent intent = new Intent(this, BnOAuthAccessTokenActivity.class);
        // Send BnOAuth2Params
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, this.getBnCredentials());
        // Send redirect Activity
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, DestinyActivity.class);
        startActivity(intent);
    }

    public void addItemsToRegionSelectSpinner(Spinner spinner) {

        List<String> list = new ArrayList<String>();
        list.add(BnConstants.ZONE_EUROPE);
        list.add(BnConstants.ZONE_UNITED_STATES);
        list.add(BnConstants.ZONE_KOREA);
        list.add(BnConstants.ZONE_TAIWAN);
        list.add(BnConstants.ZONE_CHINA);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(0);
    }

    private BnOAuth2Params getBnCredentials() {
        return new BnOAuth2Params(
                getString(R.string.battlenet_key),
                getString(R.string.battlenet_secret),
                regionSelectSpinner.getSelectedItem().toString(),
                getString(R.string.battlenet_redirect),
                getString(R.string.battlenet_app_name)
        );
    }

    private void clearCredentials()  {
        try {
            new BnOAuth2Helper(sharedPreferences, this.getBnCredentials()).clearCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
