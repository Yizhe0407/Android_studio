package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {
    Button One, Plus, Minus, Multiplication, Division, Equal, Delete, Clear;
    ImageButton Mode;
    ConstraintLayout layout;
    TextView EditText, Summand, Addend, Result, Operator, TextView;
    boolean isBlackBackground = false;
    boolean lock = true;
    private boolean isEqualPressed = false, calc = false;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText = findViewById(R.id.editText);
        EditText.setText("");
        One = findViewById(R.id.one);
        Plus = findViewById(R.id.plus);
        Minus = findViewById(R.id.minus);
        Multiplication = findViewById(R.id.multiplication);
        Division = findViewById(R.id.division);
        Equal = findViewById(R.id.equal);
        Delete = findViewById(R.id.delete);
        Clear = findViewById(R.id.C);
        Mode = findViewById(R.id.mode);
        layout = findViewById(R.id.rootLayout);
        Summand = findViewById(R.id.summand);
        Addend = findViewById(R.id.addend);
        Result = findViewById(R.id.result);
        Operator = findViewById(R.id.operator);
        TextView = findViewById(R.id.textView);

        Mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 檢查目前背景狀態並設定不同的背景
                if (isBlackBackground) {
                    layout.setBackgroundResource(R.drawable.light_background); // 切换回蓝色背景
                    Mode.setImageDrawable(getResources().getDrawable(R.drawable.dark_mode_black_36dp));
                    EditText.setBackgroundResource(R.drawable.round_edittext_light);
                    EditText.setTextColor(Color.BLACK);
                    Operator.setTextColor(Color.BLACK);
                    TextView.setTextColor(Color.BLACK);
                } else {
                    layout.setBackgroundResource(R.drawable.dark_background); // 切换到黑色背景
                    Mode.setImageDrawable(getResources().getDrawable(R.drawable.light_mode_white_36dp));
                    EditText.setBackgroundResource(R.drawable.round_edittext_dark);
                    EditText.setTextColor(Color.WHITE);
                    Operator.setTextColor(Color.WHITE);
                    TextView.setTextColor(Color.WHITE);
                }

                // 定義按鈕數組
                Button[] buttons = new Button[] { One, Plus, Minus, Multiplication, Division, Equal, Delete, Clear };

                // 設定按鈕文字顏色和背景
                int textColor = isBlackBackground ? Color.BLACK : Color.WHITE;
                int backgroundResource = isBlackBackground ? R.drawable.round_corner_white : R.drawable.round_corner_dark;

                for (Button button : buttons) {
                    button.setTextColor(textColor);
                    button.setBackgroundResource(backgroundResource);
                }

                // 更新背景狀態
                isBlackBackground = !isBlackBackground;
            }
        });

        EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 在文本改變之前執行的操作
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 文本改變時執行的操作
                updateSummandAndAddend(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 這裡不需要特別處理
            }

            private void updateSummandAndAddend(String text) {
                int indexOfPlus = text.indexOf("+");
                int indexOfMinus = text.indexOf("-");
                int indexOfMultiply = text.indexOf("×");
                int indexOfDivide = text.indexOf("÷");

                if (lock){
                    if (indexOfPlus != -1) {
                        // 處理加法
                        String summandText = text.substring(0, indexOfPlus);
                        String addendText = text.substring(indexOfPlus + 1);

                        int summandOneCount = countOnes(summandText);
                        int addendOneCount = countOnes(addendText);

                        Summand.setText(String.valueOf(summandOneCount));
                        Addend.setText(String.valueOf(addendOneCount));
                        Operator.setText("+");
                    } else if (indexOfMinus != -1) {
                        String summandText = text.substring(0, indexOfMinus);
                        String addendText = text.substring(indexOfMinus + 1);

                        int summandOneCount = countOnes(summandText);
                        int addendOneCount = countOnes(addendText);

                        // Assuming Summand and Addend are TextViews or similar UI elements
                        Summand.setText(String.valueOf(summandOneCount));
                        Addend.setText(String.valueOf(addendOneCount));
                        Operator.setText("-");
                    } else if (indexOfMultiply != -1) {
                        String summandText = text.substring(0, indexOfMultiply);
                        String addendText = text.substring(indexOfMultiply + 1);

                        int summandOneCount = countOnes(summandText);
                        int addendOneCount = countOnes(addendText);

                        // Assuming Summand and Addend are TextViews or similar UI elements
                        Summand.setText(String.valueOf(summandOneCount));
                        Addend.setText(String.valueOf(addendOneCount));
                        Operator.setText("×");
                    } else if (indexOfDivide != -1) {
                        String summandText = text.substring(0, indexOfDivide);
                        String addendText = text.substring(indexOfDivide + 1);

                        int summandOneCount = countOnes(summandText);
                        int addendOneCount = countOnes(addendText);

                        // Assuming Summand and Addend are TextViews or similar UI elements
                        Summand.setText(String.valueOf(summandOneCount));
                        Addend.setText(String.valueOf(addendOneCount));
                        Operator.setText("÷");
                    } else {
                        // 沒有操作符號，設置Summand和Addend為1的計數
                        int oneCount = countOnes(text);
                        Summand.setText(String.valueOf(oneCount));
                    }
                }
            }

            private int countOnes(String text) {
                int count = 0;
                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) == '1') {
                        count++;
                    }
                }
                return count;
            }
        });


        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lock = true;
                if (calc) {
                    EditText.append("1");
                    calc = false;
                    isEqualPressed = false;
                } else {
                    if (isEqualPressed) {
                        // 清除EditText並重置標誌
                        EditText.setText("");
                        isEqualPressed = false;
                    }

                    EditText.append("1");
                }
            }
        });


        Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = EditText.getText().toString();
                lock = true;

                if (text.isEmpty()) {
                    // 如果文本框为空，可以直接添加减号
                    text += "+";
                } else {
                    // 寻找最后一个操作符的位置
                    int lastOperatorIndex = Math.max(text.lastIndexOf("+"), text.lastIndexOf("-"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("×"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("÷"));

                    if (lastOperatorIndex >= 0) {
                        // 替换最后一个操作符为减号
                        StringBuilder newText = new StringBuilder(text);
                        newText.setCharAt(lastOperatorIndex, '+');
                        text = newText.toString();
                    } else {
                        // 如果没有操作符，可以直接添加减号
                        text += "+";
                    }
                }

                // 更新文本框的内容
                EditText.setText(text);
                calc = true;
            }
        });

        Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = EditText.getText().toString();
                lock = true;

                if (text.isEmpty()) {
                    // 如果文本框为空，可以直接添加减号
                    text += "-";
                } else {
                    // 寻找最后一个操作符的位置
                    int lastOperatorIndex = Math.max(text.lastIndexOf("+"), text.lastIndexOf("-"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("×"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("÷"));

                    if (lastOperatorIndex >= 0) {
                        // 替换最后一个操作符为减号
                        StringBuilder newText = new StringBuilder(text);
                        newText.setCharAt(lastOperatorIndex, '-');
                        text = newText.toString();
                    } else {
                        // 如果没有操作符，可以直接添加减号
                        text += "-";
                    }
                }

                // 更新文本框的内容
                EditText.setText(text);
                calc = true;
            }
        });

        Multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = EditText.getText().toString();
                lock = true;

                if (text.isEmpty()) {
                    // 如果文本框为空，可以直接添加乘号
                    text += "×";
                } else {
                    // 寻找最后一个操作符的位置
                    int lastOperatorIndex = Math.max(text.lastIndexOf("+"), text.lastIndexOf("-"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("×"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("÷"));

                    if (lastOperatorIndex >= 0) {
                        // 替换最后一个操作符为乘号
                        StringBuilder newText = new StringBuilder(text);
                        newText.setCharAt(lastOperatorIndex, '×');
                        text = newText.toString();
                    } else {
                        // 如果没有操作符，可以直接添加乘号
                        text += "×";
                    }
                }

                // 更新文本框的内容
                EditText.setText(text);
                calc = true;
            }
        });

        Division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = EditText.getText().toString();
                lock = true;

                if (text.isEmpty()) {
                    // 如果文本框为空，可以直接添加减号
                    text += "÷";
                } else {
                    // 寻找最后一个操作符的位置
                    int lastOperatorIndex = Math.max(text.lastIndexOf("+"), text.lastIndexOf("-"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("×"));
                    lastOperatorIndex = Math.max(lastOperatorIndex, text.lastIndexOf("÷"));

                    if (lastOperatorIndex >= 0) {
                        // 替换最后一个操作符为减号
                        StringBuilder newText = new StringBuilder(text);
                        newText.setCharAt(lastOperatorIndex, '÷');
                        text = newText.toString();
                    } else {
                        // 如果没有操作符，可以直接添加减号
                        text += "÷";
                    }
                }

                // 更新文本框的内容
                EditText.setText(text);
                calc = true;
            }
        });

        Equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lock = false;
                String text = EditText.getText().toString();
                if (!text.isEmpty()) {
                    text = text.replace(",", "");
                    String[] parts = text.split("[\\+\\-\\×\\÷]");
                    if (parts.length == 2) {
                        String operand1Str = parts[0];
                        String operator;
                        if (text.contains("+")) {
                            operator = "+";
                        } else if (text.contains("-")) {
                            operator = "-";
                        } else if (text.contains("×")) {
                            operator = "×";
                        } else {
                            operator = "÷";
                        }
                        String operand2Str = parts[1];

                        int operand1Bits = operand1Str.length();
                        int operand2Bits = operand2Str.length();
                        int result = 0;
                        int remainder = 0;  // 餘數

                        switch (operator) {
                            case "+":
                                result = operand1Bits + operand2Bits;
                                break;
                            case "-":
                                result = operand1Bits - operand2Bits;
                                break;
                            case "×":
                                result = operand1Bits * operand2Bits;
                                break;
                            case "÷":
                                if (operand2Bits != 0) {
                                    result = operand1Bits / operand2Bits;
                                    remainder = operand1Bits % operand2Bits; // 计算餘數
                                } else {
                                    // 處理除以零的情況
                                    result = 0;
                                }
                                break;
                        }

                        StringBuilder resultBuilder = new StringBuilder();
                        if (result == 0) {
                            if (remainder != 0) {
                                for (int i = 0; i < remainder; i++) {
                                    resultBuilder.append("1");
                                }
                                resultBuilder.append("/");
                                for (int i = 0; i < operand2Bits; i++) {
                                    resultBuilder.append("1");
                                }
                            }
                        } else {
                            for (int i = 0; i < result; i++) {
                                resultBuilder.append("1");
                            }
                            if (remainder > 0) {
                                resultBuilder.append("...");
                                for (int i = 0; i < remainder; i++) {
                                    resultBuilder.append("1");
                                }
                                resultBuilder.append("/");
                                for (int i = 0; i < operand2Bits; i++) {
                                    resultBuilder.append("1");
                                }
                            }
                        }
                        String resultText = resultBuilder.toString();

                        String decimalText;
                        if (result == 0) {
                            if (remainder == 0) {
                                decimalText = "0";
                            }
                            else {
                                decimalText = String.valueOf(remainder) + "/" + String.valueOf(operand2Bits);
                            }
                        } else {
                            if (remainder != 0) {
                                decimalText = String.valueOf(result) + "..." + String.valueOf(remainder);
                            }
                            else {
                                decimalText = String.valueOf(result);
                            }

                        }

                        EditText.setText(resultText);
                        Result.setText(decimalText);

                        isEqualPressed = true;
                        check = 0;
                    }
                }
            }
        });


        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = EditText.getText().toString();
                lock = true;

                if (check == 1 || check == 2 || check == 3 || check == 4) {
                    if (text.endsWith("+")) {
                        check = 0;
                    } else if (text.endsWith("-")) {
                        check = 0;
                    } else if (text.endsWith("×")) {
                        check = 0;
                    } else if (text.endsWith("÷")) {
                        check = 0;
                    }
                }

                if (!text.isEmpty()) {
                    text = text.substring(0, text.length() - 1);
                    EditText.setText(text);
                }
            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lock = true;
                check = 0;
                EditText.setText(""); // 清空文本框
                Summand.setText("");
                Addend.setText("");
                Result.setText("");
                Operator.setText("");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("EditTextText", EditText.getText().toString());
        outState.putString("SummandText", Summand.getText().toString());
        outState.putString("AddendText", Addend.getText().toString());
        outState.putString("ResultText", Result.getText().toString());
        outState.putString("OperatorText", Operator.getText().toString());
        outState.putBoolean("isBlackBackground", isBlackBackground);
        // 保存背景设置
        outState.putInt("layoutBackgroundResource", isBlackBackground ? R.drawable.dark_background : R.drawable.light_background);
        // 保存EditText的背景资源ID
        outState.putInt("editTextBackground", isBlackBackground ? R.drawable.round_edittext_dark : R.drawable.round_edittext_light);

        outState.putInt("oneTextColor", One.getCurrentTextColor());
        outState.putInt("plusTextColor", Plus.getCurrentTextColor());
        outState.putInt("minusTextColor", Minus.getCurrentTextColor());
        outState.putInt("multiplicationTextColor", Multiplication.getCurrentTextColor());
        outState.putInt("divisionTextColor", Division.getCurrentTextColor());
        outState.putInt("equalTextColor", Equal.getCurrentTextColor());
        outState.putInt("deleteTextColor", Delete.getCurrentTextColor());
        outState.putInt("clearTextColor", Clear.getCurrentTextColor());
        outState.putInt("operatorTextColor", Operator.getCurrentTextColor());
        outState.putInt("textViewTextColor", TextView.getCurrentTextColor());
        outState.putInt("modeImageButtonResource", Mode.getId());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String editTextText = savedInstanceState.getString("EditTextText");
        String summandText = savedInstanceState.getString("SummandText");
        String addendText = savedInstanceState.getString("AddendText");
        String resultText = savedInstanceState.getString("ResultText");
        String operatorText = savedInstanceState.getString("OperatorText");
        isBlackBackground = savedInstanceState.getBoolean("isBlackBackground");
        // 恢复背景设置
        int layoutBackgroundResource = savedInstanceState.getInt("layoutBackgroundResource");
        layout.setBackgroundResource(layoutBackgroundResource);

        // 恢复EditText的背景
        int editTextBackground = savedInstanceState.getInt("editTextBackground");
        EditText.setBackgroundResource(editTextBackground);

        // 恢复按钮的文本颜色
        One.setTextColor(savedInstanceState.getInt("oneTextColor"));
        Plus.setTextColor(savedInstanceState.getInt("plusTextColor"));
        Minus.setTextColor(savedInstanceState.getInt("minusTextColor"));
        Multiplication.setTextColor(savedInstanceState.getInt("multiplicationTextColor"));
        Division.setTextColor(savedInstanceState.getInt("divisionTextColor"));
        Equal.setTextColor(savedInstanceState.getInt("equalTextColor"));
        Delete.setTextColor(savedInstanceState.getInt("deleteTextColor"));
        Clear.setTextColor(savedInstanceState.getInt("clearTextColor"));

        // 恢复TextView的文本颜色
        Operator.setTextColor(savedInstanceState.getInt("operatorTextColor"));
        TextView.setTextColor(savedInstanceState.getInt("textViewTextColor"));

        // 恢复按钮的背景
        One.setBackgroundResource(savedInstanceState.getInt("oneBackground"));
        Plus.setBackgroundResource(savedInstanceState.getInt("plusBackground"));
        Minus.setBackgroundResource(savedInstanceState.getInt("minusBackground"));
        Multiplication.setBackgroundResource(savedInstanceState.getInt("multiplicationBackground"));
        Division.setBackgroundResource(savedInstanceState.getInt("divisionBackground"));
        Equal.setBackgroundResource(savedInstanceState.getInt("equalBackground"));
        Delete.setBackgroundResource(savedInstanceState.getInt("deleteBackground"));
        Clear.setBackgroundResource(savedInstanceState.getInt("clearBackground"));

        // 恢复其他需要的数据
        EditText.setText(editTextText);
        Summand.setText(summandText);
        Addend.setText(addendText);
        Result.setText(resultText);
        Operator.setText(operatorText);
        // 根据isBlackBackground设置文本颜色
        int textColor = isBlackBackground ? Color.WHITE : Color.BLACK;
        EditText.setTextColor(textColor);
        Summand.setTextColor(textColor);
        Addend.setTextColor(textColor);
        Result.setTextColor(textColor);
        Operator.setTextColor(textColor);

        int modeImageButtonResource = savedInstanceState.getInt("modeImageButtonResource");
        Mode.setImageResource(modeImageButtonResource);

    }
}