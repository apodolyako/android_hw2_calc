package com.example.calc.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calc.R;

public abstract class BaseActivity  extends AppCompatActivity {
    protected static final String NameSharedPreference ="MyCal";

    protected static final String AppTheme = "AppTheme";
    protected static final int AppThemeLightCodeStyle = 0;
    protected static final int AppThemeDarkCodeStyle = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getAppTheme(R.style.AppThemeLight));

    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleID(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle) {
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);

    }

    private int codeStyleToStyleID(int codeStyle) {
        switch (codeStyle){
            case AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
            default:
                return R.style.AppThemeLight;
        }
    }





}
