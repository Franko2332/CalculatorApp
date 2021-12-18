package ru.gb.calculatorapp;

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

    public MyCalculator(EditText editText)  {
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
        strForRestore="0";
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
        strForRestore = str.toString();
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

    public String getStrForRestore() {
        return strForRestore;
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
}
