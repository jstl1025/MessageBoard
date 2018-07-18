package com.prototype.messageboard;

import android.content.Intent;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class DisplaySettingActivity extends AppCompatActivity {
    SeekBar sb, sb2;
    TextView valueTxt, valueTxt2;
    Switch myswitch;
    public static int switch_on_tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // sets night theme defined in styles
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
            /*ConstraintLayout homeActivity = (ConstraintLayout) findViewById(R.id.homeLayout);
            homeActivity.setTheme(R.style.darktheme);*/
            /*Resources.Theme theme = HomeActivity.getTheme();
            theme.applyStyle(R.style.darktheme,true);*/
        }
        else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_display_setting);*/

        // night theme set my toggle switch
        myswitch = (Switch) findViewById(R.id.myswitch);
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    DisplaySettingActivity.this.recreate();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    DisplaySettingActivity.this.recreate();
                }
            }
        });

        // display seekbar value
        sb = (SeekBar) findViewById(R.id.seekBar);
        valueTxt = (TextView) findViewById(R.id.valueTxt);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueTxt.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        sb2 = (SeekBar) findViewById(R.id.seekBar2);
        valueTxt2 = (TextView) findViewById(R.id.valueTxt2);

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueTxt2.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
