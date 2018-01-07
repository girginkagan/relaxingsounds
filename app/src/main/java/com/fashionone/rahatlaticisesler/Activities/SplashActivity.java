package com.fashionone.rahatlaticisesler.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fashionone.rahatlaticisesler.R;
import com.fashionone.rahatlaticisesler.Utils.FavoritesUtil;

import org.json.JSONException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            FavoritesUtil.LoadFavorites(this);
        } catch (JSONException e) {

        }
        //Wait 2 second to pass splash
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 2000);
    }
}
