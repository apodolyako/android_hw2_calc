package com.example.calc.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.calc.R;
import com.example.calc.domain.CalculatorImpl;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class CalculatorActivity extends BaseActivity implements CalculatorView {

    private CalculatorPresenter presenter;

    private TextView resultText;

    private TextView resultVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initThemeChooser();

        initView();
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonLight), AppThemeLightCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonDark), AppThemeDarkCodeStyle);

        RadioGroup rg = findViewById(R.id.radioButtons);

        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(AppThemeLightCodeStyle))).setChecked(true);

    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme(codeStyle);
//                перезасздаем активити чтобы тема применилась
                recreate();

               }

            private void setAppTheme(int codeStyle) {
                SharedPreferences sharePref =
                        getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharePref.edit();
                editor.putInt(AppTheme, codeStyle);
                editor.apply();
            }
        });
    }

    private void initView() {
        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        resultText = findViewById(R.id.current);
        resultVal = findViewById(R.id.result);

        showResult("0");

        initViewClick();
    }

    private void initViewClick() {

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("1");
            }
        });

        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("2");
            }
        });

        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("3");
            }
        });

        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("4");
            }
        });

        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("5");
            }
        });

        findViewById(R.id.btn_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("6");
            }
        });

        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("7");
            }
        });

        findViewById(R.id.btn_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("8");
            }
        });

        findViewById(R.id.btn_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("9");
            }
        });

        findViewById(R.id.btn_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Clicked("0");
            }
        });

        findViewById(R.id.btn_Plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Plus_Clicked();
            }
        });

        findViewById(R.id.btn_Sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Sub_Clicked();
            }
        });

        findViewById(R.id.btn_Mult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Mult_Clicked();
            }
        });

        findViewById(R.id.btn_Div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtn_Div_Clicked();
            }
        });

    }


    @Override
    public void showExpression(String result) {
        // Обновить текстовое поле результата когда скажут
        resultText.setText(result);
    }

    @Override
    public void showResult(String result) {
        // Обновить текстовое поле результата когда скажут
        resultVal.setText(result);

    }
    
}