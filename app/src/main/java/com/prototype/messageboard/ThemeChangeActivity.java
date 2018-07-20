package com.prototype.messageboard;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThemeChangeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = PreferenceManager.getDefaultSharedPreferences(this).getInt("ActivityTheme", R.style.AppTheme);
        setTheme(theme);
        super.onCreate(savedInstanceState);

    }
}
