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

//    private EditText editText;
//    private MyCalculator myCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init();
    }


}