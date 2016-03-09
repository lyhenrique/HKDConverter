package com.ericlau.hkdconverter;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by Eric.Lau on 5/2/2016.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize PreferenceUtil
        PreferenceUtil.init(this);
        //According to the language set last time, set it again
        switchLanguage(PreferenceUtil.getString("language", "zh"));
    }


    protected void switchLanguage(String language) {
        //set language type
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("en")) {
            config.locale = Locale.ENGLISH;
        } else if(language.equals("zh")){
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }else if(language.equals("hk")){
            config.locale = Locale.TRADITIONAL_CHINESE;
        }
        resources.updateConfiguration(config, dm);

        //save language settings
        PreferenceUtil.commitString("language", language);
    }
}
