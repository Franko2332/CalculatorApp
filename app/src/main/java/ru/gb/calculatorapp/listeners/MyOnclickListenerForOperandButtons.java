package ru.gb.calculatorapp.listeners;

import android.view.View;

import ru.gb.calculatorapp.MyCalculator;
import ru.gb.calculatorapp.Operations;

public class MyOnclickListenerForOperandButtons implements View.OnClickListener {
    private MyCalculator myCalculator;

    public MyOnclickListenerForOperandButtons(MyCalculator myCalculator) {
        this.myCalculator = myCalculator;
    }

    @Override
    public void onClick(View view) {
        String id = view.getContext().getResources().getResourceName(view.getId());
        if (id.equals(Operations.PLUS.name())) {
            myCalculator.plus();
        } else if (id.equals(Operations.MINUS.name())) {
            myCalculator.minus();
        } else if (id.equals(Operations.DIVISION.name())) {
            myCalculator.division();
        } else if (id.equals(Operations.MULTIPLE.name())) {
            myCalculator.multiple();
        } else if (id.equals(Operations.PERCENT.name())) {
            myCalculator.percent();
        } else if (id.equals(Operations.MC.name())) {
            myCalculator.mc();
        } else if (id.equals(Operations.MR.name())) {
            myCalculator.mr();
        } else if (id.equals(Operations.M_PLUS.name())) {
            myCalculator.m_plus();
        } else if (id.equals(Operations.M_MINUS.name())) {
            myCalculator.m_minus();
        }
    }
}
