package ru.gb.calculatorapp.listeners;

import android.view.View;

import ru.gb.calculatorapp.MyCalculator;

public class MyOnclickForCommaButton implements View.OnClickListener {
    private MyCalculator myCalculator;

    public MyOnclickForCommaButton(MyCalculator myCalculator) {
        this.myCalculator = myCalculator;
    }

    @Override
    public void onClick(View view) {
        myCalculator.comma();
    }
}
