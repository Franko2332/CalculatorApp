package ru.gb.calculatorapp;

import android.text.Editable;
import android.widget.EditText;

public class MyCalculator {

    private EditText editText;
    private boolean operandMode;
    private String operand;
    private float number1;


    public MyCalculator(EditText editText) {
        this.editText = editText;
        operandMode = false;
    }

    public void plus() {
      checkOperatonInputMode();
    }

    public void minus() {
        checkOperatonInputMode();
    }

    public void multiple() {
        checkOperatonInputMode();
    }

    public void division() {
        checkOperatonInputMode();
    }

    public void percent() {
        float fl = Float.parseFloat(editText.getText().toString());
        fl = fl / 100;
        if(fl%1==0){
            editText.setText(Integer.toString((int)fl));
        } else {
            editText.setText(Float.toString(fl));
        }
    }

    public void mr(){}

    public void m_plus(){}

    public void m_minus(){}

    public void mc() {}

    public void comma() {
        Editable editable = editText.getText();
        if (isOperationInputMode()) {
            setNumber1(Float.parseFloat(editable.toString()));
            editable.clear();
            uncheckOperationInputMode();
            editable.append("0.");
        } else if (!editable.toString().contains(".")) {
            editable.append(".");
        }
    }

    public void clear() {
        editText.setText("0");
    }


    public void deleteDigit() {
        uncheckOperationInputMode();
        if (editText.getText().toString().length() > 0) {
            if (editText.getText().toString().length() == 1) {
                editText.setText("0");
            } else {
                editText.getText().replace(0, editText.getText().toString().length(),
                        editText.getText().toString(), 0, editText.getText().toString().length() - 1);
            }
        }
    }

    private void uncheckOperationInputMode() {
        operandMode = false;
    }

    private void checkOperatonInputMode() {
        operandMode = true;
    }

    private boolean isOperationInputMode() {
        return operandMode;
    }

    private void setNumber1(float number1) {
        this.number1 = number1;
    }
}
