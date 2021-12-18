package ru.gb.calculatorapp.listeners;

import android.view.View;

import ru.gb.calculatorapp.MyCalculator;

public class MyOnclickListenerForClearButton implements View.OnClickListener {
    private MyCalculator myCalculator;

    public MyOnclickListenerForClearButton(MyCalculator myCalculator) {
        this.myCalculator = myCalculator;
    }

    @Override
    public void onClick(View view) {
     myCalculator.clear();
    }
}
