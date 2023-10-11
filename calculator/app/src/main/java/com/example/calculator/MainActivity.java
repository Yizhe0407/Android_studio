package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button One, Plus, Minus, Multiplication, Division, Equal, Delete, Clear;
    ImageButton Mode;
    ConstraintLayout layout;
    TextView EditText, Decimal;
    boolean isBlackBackground = false;
    private boolean isEqualPressed = false, calc = false;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText = findViewById(R.id.editText);
        Decimal = findViewById(R.id.decimal);
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

        Mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 檢查目前背景狀態並設定不同的背景
                if (isBlackBackground) {
                    layout.setBackgroundResource(R.drawable.light_background); // 切换回蓝色背景
                    Mode.setImageDrawable(getResources().getDrawable(R.drawable.dark_mode));
                    EditText.setBackgroundResource(R.drawable.round_edittext_light);
                    EditText.setTextColor(Color.BLACK);
                    Decimal.setTextColor(Color.BLACK);
                } else {
                    layout.setBackgroundResource(R.drawable.dark_background); // 切换到黑色背景
                    Mode.setImageDrawable(getResources().getDrawable(R.drawable.light_mode));
                    EditText.setBackgroundResource(R.drawable.round_edittext_dark);
                    EditText.setTextColor(Color.WHITE);
                    Decimal.setTextColor(Color.WHITE);
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


        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calc) {
                    EditText.append("1");
                    calc = false;
                    isEqualPressed = false;
                }
                else {
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
                String text = EditText.getText().toString();
                if (!text.isEmpty()) {
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
                                decimalText = "Decimal : 0";
                            }
                            else {
                                decimalText = "Decimal : " + String.valueOf(remainder) + "/" + String.valueOf(operand2Bits);
                            }
                        } else {
                            if (remainder != 0) {
                                decimalText = "Decimal : " + String.valueOf(result) + "..." + String.valueOf(remainder);
                            }
                            else {
                                decimalText = "Decimal : " + String.valueOf(result);
                            }

                        }

                        EditText.setText(resultText);
                        Decimal.setText(decimalText);

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
                check = 0;
                Decimal.setText("Decimal : 0");
                EditText.setText(""); // 清空文本框
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("EditTextText", EditText.getText().toString());
        outState.putString("DecimalText", Decimal.getText().toString());
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
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String editTextText = savedInstanceState.getString("EditTextText");
        String decimalText = savedInstanceState.getString("DecimalText");
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
        Decimal.setText(decimalText);
        // 根据isBlackBackground设置文本颜色
        int textColor = isBlackBackground ? Color.WHITE : Color.BLACK;
        EditText.setTextColor(textColor);
        Decimal.setTextColor(textColor);
    }
}