package ru.gb.calculatorapp.listeners;

import android.view.View;

import ru.gb.calculatorapp.MyCalculator;

public class MyOnclickListenerForDeleteDigitButton implements View.OnClickListener {
    private MyCalculator myCalculator;

    public MyOnclickListenerForDeleteDigitButton(MyCalculator myCalculator) {
        this.myCalculator = myCalculator;
    }

    @Override
    public void onClick(View view) {
        myCalculator.deleteDigit();
    }

}
