package ru.gb.calculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import ru.gb.calculatorapp.listeners.MyOnclickForCommaButton;
import ru.gb.calculatorapp.listeners.MyOnclickListenerForClearButton;
import ru.gb.calculatorapp.listeners.MyOnclickListenerForDeleteDigitButton;
import ru.gb.calculatorapp.listeners.MyOnClickListenerForNumbButtons;
import ru.gb.calculatorapp.listeners.MyOnclickListenerForOperandButtons;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private MyCalculator myCalculator;
    private final String KEY_CALCULATOR = "MY_CALCULATOR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //Log.e("Oncreate.strForRestore", myCalculator.getStrForRestore());
        Log.e("Oncreate.number1", String.valueOf(myCalculator.getNumber1()));
        Log.e("Oncreate.number2", String.valueOf(myCalculator.getNumber2()));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_CALCULATOR, myCalculator);
        Log.e("Activity.strForRestore", myCalculator.getStrForRestore());
        Log.e("Activity.number1", String.valueOf(myCalculator.getNumber1()));
        Log.e("Activity.number2", String.valueOf(myCalculator.getNumber2()));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myCalculator = savedInstanceState.getParcelable(KEY_CALCULATOR);
        editText.setText(myCalculator.getStrForRestore());
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        editText = findViewById(R.id.calcEditText);
        myCalculator = new MyCalculator(editText);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            Button[] landOperationsButtons = new Button[12];
            landOperationsButtons [0] = findViewById(R.id.X_SQUARED);
            landOperationsButtons [1] = findViewById(R.id.X_CUBE);
            landOperationsButtons [2] = findViewById(R.id.ROOT);
            landOperationsButtons [3] = findViewById(R.id.EXHIBITOR);
            landOperationsButtons [4] = findViewById(R.id.LN);
            landOperationsButtons [5] = findViewById(R.id.X_FACTORIAL);
            landOperationsButtons [6] = findViewById(R.id.COS);
            landOperationsButtons [7] = findViewById(R.id.SIN);
            landOperationsButtons [8] = findViewById(R.id.COT);
            landOperationsButtons [9] = findViewById(R.id.RAD);
            landOperationsButtons [10] = findViewById(R.id.ONE_DIV_X);
            landOperationsButtons [11] = findViewById(R.id.TAN);
            for (Button btn: landOperationsButtons) {
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
        Button[] operationsButtons = new Button[10];
        operationsButtons[0] = findViewById(R.id.MULTIPLE);
        operationsButtons[1] = findViewById(R.id.DIVISION);
        operationsButtons[2] = findViewById(R.id.PLUS);
        operationsButtons[3] = findViewById(R.id.MINUS);
        operationsButtons[4] = findViewById(R.id.EQUALS);
        operationsButtons[5] = findViewById(R.id.PERCENT);
        operationsButtons[6] = findViewById(R.id.MC);
        operationsButtons[7] = findViewById(R.id.M_PLUS);
        operationsButtons[8] = findViewById(R.id.M_MINUS);
        operationsButtons[9] = findViewById(R.id.MC);
        for (Button btn : operationsButtons) {
            btn.setOnClickListener(new MyOnclickListenerForOperandButtons(myCalculator));
        }
        Button commaButton = findViewById(R.id.COMMA);
        commaButton.setOnClickListener(new MyOnclickForCommaButton(myCalculator));
    }
}