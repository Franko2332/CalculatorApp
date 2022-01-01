package ru.gb.calculatorapp.listeners;

import android.util.Log;
import android.view.View;

import ru.gb.calculatorapp.MyCalculator;
import ru.gb.calculatorapp.Operations;

public class MyOnclickListenerForOperandButtons implements View.OnClickListener {
    private final MyCalculator myCalculator;
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
        } else if (id.equals(PACKAGE+Operations.EQUALS.name())) {
            myCalculator.equals();
        } else if (id.equals(PACKAGE+Operations.X_SQUARED.name())) {
            myCalculator.x_squared();
        } else if (id.equals(PACKAGE+Operations.X_CUBE.name())) {
            myCalculator.x_cube();
        } else if (id.equals(PACKAGE+Operations.ROOT.name())) {
            myCalculator.sqrt();
        } else if (id.equals(PACKAGE+Operations.LN.name())) {
            myCalculator.ln();
        } else if (id.equals(PACKAGE+Operations.COS.name())) {
            myCalculator.cos();
        } else if (id.equals(PACKAGE+Operations.SIN.name())) {
            myCalculator.sin();
        } else if (id.equals(PACKAGE+Operations.TAN.name())) {
            myCalculator.tan();
        }  else if (id.equals(PACKAGE+Operations.RAD.name())) {
            myCalculator.rad();
        } else if (id.equals(PACKAGE+Operations.EXHIBITOR.name())) {
            myCalculator.exp();
        } else if (id.equals(PACKAGE+Operations.ONE_DIV_X.name())) {
            myCalculator.oneDivisionX();
        }
    }
}
