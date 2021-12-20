package ru.gb.calculatorapp;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public class MyCalculator implements Parcelable {
    private EditText editText;

    private static boolean operandMode;
    private static String operand;
    private static String approvedOperand;
    private static float number1;
    private static float number2;
    private static String strForRestore;

    public MyCalculator(EditText editText) {
        this.editText = editText;
        operandMode = false;
    }

    protected MyCalculator(Parcel in) {
        operandMode = in.readByte() != 0;
        operand = in.readString();
        approvedOperand = in.readString();
        number1 = in.readFloat();
        number2 = in.readFloat();
        strForRestore = in.readString();
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

    @SuppressLint("SetTextI18n")
    public void percent() {
        resulting();
        float fl = Float.parseFloat(editText.getText().toString());
        fl = fl / 100;
        if (fl % 1 == 0) {
            editText.setText(Integer.toString((int) fl));
        } else editText.setText(Float.toString(fl));
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
        Log.e("before.strForRestore", strForRestore);
        Log.e("before.number1", String.valueOf(number1));
        Log.e("before.number2", String.valueOf(number2));
        Editable editable = editText.getText();
        if (isOperationInputMode()) {
            uncheckOperandMode();
            editable.clear();
            editable.append("0.");
            approvedOperand = operand;
            strForRestore = editable.toString();
        } else if (!editable.toString().contains(".")) {
            editable.append(".");
        }
        Log.e("after.strForRestore", strForRestore);
        Log.e("after.number1", String.valueOf(number1));
        Log.e("after.number2", String.valueOf(number2));
        Log.e("after.operand", operand);
        Log.e("after.approved_operand", String.valueOf(approvedOperand!=null));
    }

    public void clear() {
        editText.setText("0");
        number1 = 0;
        number2 = 0;
        strForRestore = "0";
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
        strForRestore = editText.getText().toString();
    }

    public void setDigitToEditText(CharSequence ch) {
        Editable str = editText.getText();
        if (operandMode) {
            approvedOperand = operand;
            str.clear();
            uncheckOperandMode();
        }
        if ("0".equals(str.toString())) {
            str.clear();
        }
        str.append(ch);
        strForRestore = str.toString();
        Log.e("strForRestore", strForRestore);
        Log.e("number1", String.valueOf(number1));
        Log.e("number2", String.valueOf(number2));
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
        MyCalculator.number1 = number1;
    }

    private void resulting() {
        Log.e("resulting_don't_if", "from resulting");
        if (approvedOperand != null) {
            Log.e("resulting", "from resulting editText = "+editText.getText().toString());
            switch (approvedOperand) {
                case "+":
                    number2 = number1 + Float.parseFloat(editText.getText().toString());
                    break;
                case "-":
                    number2 = number1 - Float.parseFloat(editText.getText().toString());
                    break;
                case "*":
                    number2 = number1 * Float.parseFloat(editText.getText().toString());
                    break;
                case "/":
                    number2 = number1 / Float.parseFloat(editText.getText().toString());
                    break;
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

    public String getStrForRestore() {
        if (strForRestore!=null) {return strForRestore;
    } else return "0";
    }

    public float getNumber1() {
        return number1;
    }

    public float getNumber2() {
        return number2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (operandMode ? 1 : 0));
        parcel.writeString(operand);
        parcel.writeString(approvedOperand);
        parcel.writeFloat(number1);
        parcel.writeFloat(number2);
        parcel.writeString(strForRestore);
    }

    public static final Creator<MyCalculator> CREATOR = new Creator<MyCalculator>() {
        @Override
        public MyCalculator createFromParcel(Parcel in) {
            return new MyCalculator(in);
        }

        @Override
        public MyCalculator[] newArray(int size) {
            return new MyCalculator[size];
        }
    };
}
