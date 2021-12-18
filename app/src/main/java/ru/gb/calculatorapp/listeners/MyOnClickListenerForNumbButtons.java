package ru.gb.calculatorapp.listeners;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.gb.calculatorapp.MyCalculator;

public class MyOnClickListenerForNumbButtons implements View.OnClickListener {
    private final EditText editText;
    private final MyCalculator myCalculator;

    public MyOnClickListenerForNumbButtons(EditText editText, MyCalculator myCalculator) {
        this.editText = editText;
        this.myCalculator = myCalculator;
    }


    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        myCalculator.setDigitToEditText(btn.getText());
    }
}
