package ru.gb.calculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import ru.gb.calculatorapp.listeners.MyOnclickForCommaButton;
import ru.gb.calculatorapp.listeners.MyOnclickListenerForClearButton;
import ru.gb.calculatorapp.listeners.MyOnclickListenerForDeleteDigitButton;
import ru.gb.calculatorapp.listeners.MyOnClickListenerForNumbButtons;
import ru.gb.calculatorapp.listeners.MyOnclickListenerForOperandButtons;

import static android.content.res.Configuration.UI_MODE_NIGHT_MASK;

public class MainActivity extends AppCompatActivity {
    private Boolean darkMode;
    private EditText editText;
    private MyCalculator myCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setMyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        restore();
        //Log.e("Oncreate.strForRestore", myCalculator.getStrForRestore());
        Log.e("Oncreate.number1", String.valueOf(myCalculator.getNumber1()));
        Log.e("Oncreate.number2", String.valueOf(myCalculator.getNumber2()));
    }

    private void restore() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            myCalculator.setApprovedOperand(bundle.getString(Constants.KEY_APPROVED_OPERAND));
            myCalculator.setOperand(bundle.getString(Constants.KEY_OPERAND));
            myCalculator.setOperandMode(bundle.getBoolean(Constants.KEY_OPERAND_MODE));
            editText.setText(bundle.getString(Constants.EDIT_TEXT_KEY_EXTRA));
        }
          // editText.setText();
    }

    @SuppressLint("ResourceAsColor")
    private void setMyTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences("Themes", Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString("Theme", null);
        if (theme!=null) {
            if (theme.equals(Constants.PACKAGE + Constants.LIGHT_RADIO_BUTTON)) {
                setTheme(R.style.CalculatorLightTheme);
            } else if (theme.equals(Constants.PACKAGE + Constants.GRAY_RADIO_BUTTON)) {
                setTheme(R.style.CalculatorGrayTheme);
            } else if (theme.equals(Constants.PACKAGE + Constants.DARK_RADIO_BUTTON)) {
                setTheme(R.style.CalculatorDarkTheme);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.KEY_CALCULATOR, myCalculator);
        Log.e("Activity.strForRestore", myCalculator.getStrForRestore());
        Log.e("Activity.number1", String.valueOf(myCalculator.getNumber1()));
        Log.e("Activity.number2", String.valueOf(myCalculator.getNumber2()));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myCalculator = savedInstanceState.getParcelable(Constants.KEY_CALCULATOR);
        editText.setText(myCalculator.getStrForRestore());
    }

    @SuppressLint("SetTextI18n")
    private void init() {
//        loadPrefs();
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        editText = findViewById(R.id.calcEditText);
        myCalculator = new MyCalculator(editText);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Button[] landOperationsButtons = new Button[10];
            landOperationsButtons[0] = findViewById(R.id.X_SQUARED);
            landOperationsButtons[1] = findViewById(R.id.X_CUBE);
            landOperationsButtons[2] = findViewById(R.id.ROOT);
            landOperationsButtons[3] = findViewById(R.id.EXHIBITOR);
            landOperationsButtons[4] = findViewById(R.id.LN);
            landOperationsButtons[5] = findViewById(R.id.COS);
            landOperationsButtons[6] = findViewById(R.id.SIN);
            landOperationsButtons[7] = findViewById(R.id.RAD);
            landOperationsButtons[8] = findViewById(R.id.ONE_DIV_X);
            landOperationsButtons[9] = findViewById(R.id.TAN);
            for (Button btn : landOperationsButtons) {
                btn.setOnClickListener(new MyOnclickListenerForOperandButtons(myCalculator));
            }
        }
        Button[] numberButtons = new Button[10];
        numberButtons[0] = findViewById(R.id.zero);
        numberButtons[1] = findViewById(R.id.one);
        numberButtons[2] = findViewById(R.id.two);
        numberButtons[3] = findViewById(R.id.three);
        numberButtons[4] = findViewById(R.id.four);
        numberButtons[5] = findViewById(R.id.five);
        numberButtons[6] = findViewById(R.id.six);
        numberButtons[7] = findViewById(R.id.seven);
        numberButtons[8] = findViewById(R.id.eight);
        numberButtons[9] = findViewById(R.id.nine);
        for (Button btn : numberButtons) {
            btn.setOnClickListener(new MyOnClickListenerForNumbButtons(editText, myCalculator));
        }
        Button clearButton = findViewById(R.id.clear);
        Button deleteDigit = findViewById(R.id.delete_digit);
        clearButton.setOnClickListener(new MyOnclickListenerForClearButton(myCalculator));
        deleteDigit.setOnClickListener(new MyOnclickListenerForDeleteDigitButton(myCalculator));
        Button[] operationsButtons = new Button[6];
        operationsButtons[0] = findViewById(R.id.MULTIPLE);
        operationsButtons[1] = findViewById(R.id.DIVISION);
        operationsButtons[2] = findViewById(R.id.PLUS);
        operationsButtons[3] = findViewById(R.id.MINUS);
        operationsButtons[4] = findViewById(R.id.EQUALS);
        operationsButtons[5] = findViewById(R.id.PERCENT);

        for (Button btn : operationsButtons) {
            btn.setOnClickListener(new MyOnclickListenerForOperandButtons(myCalculator));
        }
        Button commaButton = findViewById(R.id.COMMA);
        commaButton.setOnClickListener(new MyOnclickForCommaButton(myCalculator));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.calc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startChangeThemeActivity();
        return true;
    }

    private void startChangeThemeActivity() {
        Intent intent = new Intent(MainActivity.this, ChangeThemeActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("restore_values", MODE_PRIVATE);
        intent.putExtra(Constants.NUMBER1_KEY_EXTRA, myCalculator.getNumber1());
        intent.putExtra(Constants.NUMBER2_KEY_EXTRA, myCalculator.getNumber2());
        intent.putExtra(Constants.STR_FOR_RESTORE_KEY_EXTRA, myCalculator.getStrForRestore());
        intent.putExtra(Constants.EDIT_TEXT_KEY_EXTRA, editText.getText().toString());
        intent.putExtra(Constants.KEY_OPERAND, myCalculator.getOperand());
        intent.putExtra(Constants.KEY_APPROVED_OPERAND, myCalculator.getApprovedOperand());
        intent.putExtra(Constants.KEY_OPERAND_MODE, myCalculator.isOperandMode());

        startActivity(intent);
    }
}