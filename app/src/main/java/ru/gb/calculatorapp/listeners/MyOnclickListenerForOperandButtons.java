package ru.gb.calculatorapp.listeners;

import android.util.Log;
import android.view.View;

import ru.gb.calculatorapp.MyCalculator;
import ru.gb.calculatorapp.Operations;

public class MyOnclickListenerForOperandButtons implements View.OnClickListener {
    private MyCalculator myCalculator;
    private final String PACKAGE = "ru.gb.calculatorapp:id/";

    public MyOnclickListenerForOperandButtons(MyCalculator myCalculator) {
        this.myCalculator = myCalculator;
    }

    @Override
    public void onClick(View view) {
        String id = view.getContext().getResources().getResourceName(view.getId());
        if (id.equals(PACKAGE+Operations.PLUS.name())) {
            myCalculator.plus();
        } else if (id.equals(PACKAGE+Operations.MINUS.name())) {
            myCalculator.minus();
        } else if (id.equals(PACKAGE+Operations.DIVISION.name())) {
            myCalculator.division();
        } else if (id.equals(PACKAGE+Operations.MULTIPLE.name())) {
            myCalculator.multiple();
        } else if (id.equals(PACKAGE+Operations.PERCENT.name())) {
            myCalculator.percent();
        } else if (id.equals(PACKAGE+Operations.MC.name())) {
            myCalculator.mc();
        } else if (id.equals(PACKAGE+Operations.MR.name())) {
            myCalculator.mr();
        } else if (id.equals(PACKAGE+Operations.M_PLUS.name())) {
            myCalculator.m_plus();
        } else if (id.equals(PACKAGE+Operations.M_MINUS.name())) {
            myCalculator.m_minus();
        } else if (id.equals(PACKAGE+Operations.EQUALS.name())) {
            myCalculator.equals();
        }
    }
}
