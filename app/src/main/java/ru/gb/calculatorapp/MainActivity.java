package ru.gb.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        editText = findViewById(R.id.calcEditText);
        myCalculator = new MyCalculator(editText);
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