package ru.gb.calculatorapp;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public class MyCalculator {

    private EditText editText;
    private boolean operandMode;
    private String operand;
    private String approvedOperand;
    private float number1;
    private float number2;


    public MyCalculator(EditText editText) {
        this.editText = editText;
        operandMode = false;
    }


    public void plus() {
        resulting();
        checkOperandMode();
        setNumber1(Float.parseFloat(editText.getText().toString()));
        operand = "+";
    }

    public void minus() {
        resulting();
        checkOperandMode();
        setNumber1(Float.parseFloat(editText.getText().toString()));
        operand = "-";
    }

    public void multiple() {
        resulting();
        checkOperandMode();
        setNumber1(Float.parseFloat(editText.getText().toString()));
        operand = "*";
    }

    public void division() {
        resulting();
        checkOperandMode();
        setNumber1(Float.parseFloat(editText.getText().toString()));
        operand = "/";
    }

    public void percent() {
        resulting();
        float fl = Float.parseFloat(editText.getText().toString());
        fl = fl / 100;
        if (fl % 1 == 0) {
            editText.setText(Integer.toString((int) fl));
        } else {
            editText.setText(Float.toString(fl));
        }
    }

    public void equals() {
        resulting();
    }

    public void mr() {
    }

    public void m_plus() {
    }

    public void m_minus() {
    }

    public void mc() {
    }

    public void comma() {
        Editable editable = editText.getText();
        if (isOperationInputMode()) {
            setNumber1(Float.parseFloat(editable.toString()));
            editable.clear();
            uncheckOperandMode();
            editable.append("0.");
        } else if (!editable.toString().contains(".")) {
            editable.append(".");
        }
    }

    public void clear() {
        editText.setText("0");
        number1 = 0;
        number2 = 0;
    }


    public void deleteDigit() {
        uncheckOperandMode();
        if (editText.getText().toString().length() > 0) {
            if (editText.getText().toString().length() == 1) {
                editText.setText("0");
            } else {
                editText.getText().replace(0, editText.getText().toString().length(),
                        editText.getText().toString(), 0, editText.getText().toString().length() - 1);
            }
        }
    }

    public void setDigitToEditText(CharSequence ch) {
        Editable str = editText.getText();
        if (operandMode) {
            approvedOperand = new String(operand);
            str.clear();
            uncheckOperandMode();
        }
        if ("0".equals(str.toString())) {
            str.clear();
        }
        str.append(ch);
    }

    private void uncheckOperandMode() {
        operandMode = false;
    }


    private void checkOperandMode() {
        operandMode = true;
    }

    private boolean isOperationInputMode() {
        return operandMode;
    }

    private void setNumber1(float number1) {
        this.number1 = number1;
    }

    private void resulting() {
        if (approvedOperand != null) {
            Log.e("resulting", "from resulting");
            if (approvedOperand.equals("+")) {
                number2 = number1 + Float.parseFloat(editText.getText().toString());
            } else if (approvedOperand.equals("-")) {
                number2 = number1 - Float.parseFloat(editText.getText().toString());
            } else if (approvedOperand.equals("*")) {
                number2 = number1 * Float.parseFloat(editText.getText().toString());
            } else if (approvedOperand.equals("/")) {
                number2 = number1 / Float.parseFloat(editText.getText().toString());
            }
            editText.getText().clear();
            if (number2 % 1 == 0) {
                setDigitToEditText(String.valueOf(Math.round(number2)));
            } else {
                setDigitToEditText(String.valueOf(number2));
            }
            number1 = number2;
            approvedOperand = null;
        }

    }
}
