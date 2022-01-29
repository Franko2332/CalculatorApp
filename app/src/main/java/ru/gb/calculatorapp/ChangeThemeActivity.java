package ru.gb.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;

public class ChangeThemeActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPreferences;
    private RadioGroup radioGroup;
    private float number1;
    private float number2;
    private String editTextNumber;
    private String strForRestore;
    private String operande;
    private String approvedOperand;
    private boolean operandMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setMyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_theme);
        init();
    }

    public void init(){
        radioGroup = findViewById(R.id.radio_group);
        setMyTheme();
        Intent intent = getIntent();
        number1 = intent.getExtras().getFloat(Constants.NUMBER1_KEY_EXTRA);
        number2 = intent.getExtras().getFloat(Constants.NUMBER2_KEY_EXTRA);
        editTextNumber = intent.getExtras().getString(Constants.EDIT_TEXT_KEY_EXTRA);
        strForRestore = intent.getExtras().getString(Constants.STR_FOR_RESTORE_KEY_EXTRA);
        operande = intent.getExtras().getString(Constants.KEY_OPERAND);
        approvedOperand = intent.getExtras().getString(Constants.KEY_APPROVED_OPERAND);
        operandMode = intent.getExtras().getBoolean(Constants.KEY_OPERAND_MODE);
        MaterialButton saveButton = findViewById(R.id.save_theme_button);
        saveButton.setOnClickListener(this);
    }

    private void setMyTheme() {
        sharedPreferences = getSharedPreferences("Themes", Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString("Theme", "default");
        if (theme.equals(Constants.PACKAGE + Constants.GRAY_RADIO_BUTTON)) {
            setTheme(R.style.CalculatorGrayTheme);
        } else if (theme.equals(Constants.PACKAGE + Constants.DARK_RADIO_BUTTON)) {
            setTheme(R.style.CalculatorDarkTheme);
        } else if (theme.equals(Constants.PACKAGE + Constants.LIGHT_RADIO_BUTTON)) {
            setTheme(R.style.CalculatorLightTheme);
        }
    }

    @Override
    public void onClick(View view) {
      //  getIntent().getStIntent
        Intent intent = new Intent(ChangeThemeActivity.this, MainActivity.class);
        intent.putExtra(Constants.NUMBER1_KEY_EXTRA, number1);
        intent.putExtra(Constants.NUMBER2_KEY_EXTRA, number2);
        intent.putExtra(Constants.STR_FOR_RESTORE_KEY_EXTRA, strForRestore);
        intent.putExtra(Constants.EDIT_TEXT_KEY_EXTRA, editTextNumber);
        intent.putExtra(Constants.KEY_OPERAND, operande);
        intent.putExtra(Constants.KEY_APPROVED_OPERAND, approvedOperand);
        intent.putExtra(Constants.KEY_OPERAND_MODE, operandMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Theme", this.getResources().getResourceName(radioGroup.getCheckedRadioButtonId())).apply();
        startActivity(intent);
    }
}