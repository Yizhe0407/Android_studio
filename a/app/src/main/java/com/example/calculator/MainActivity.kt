import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.calculator.R

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: List<Button>
    private lateinit var operators: List<String>
    private lateinit var modeButton: ImageButton
    private lateinit var layout: ConstraintLayout
    private lateinit var editText: TextView
    private lateinit var decimal: TextView

    private var isBlackBackground = false
    private var isEqualPressed = false
    private var calc = false
    private var check = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        decimal = findViewById(R.id.decimal)
        editText.text = ""
        buttons = listOf(
                findViewById(R.id.one),
                findViewById(R.id.plus),
                findViewById(R.id.minus),
                findViewById(R.id.multiplication),
                findViewById(R.id.division),
                findViewById(R.id.equal),
                findViewById(R.id.delete),
                findViewById(R.id.C),
        )
        operators = listOf("+", "-", "×", "÷")
        modeButton = findViewById(R.id.mode)
        layout = findViewById(R.id.rootLayout)

        modeButton.setOnClickListener {
            isBlackBackground = !isBlackBackground
            val backgroundColor = if (isBlackBackground) R.drawable.light_background else R.drawable.dark_background
            val textColor = if (isBlackBackground) Color.BLACK else Color.WHITE
            val editTextBackground = if (isBlackBackground) R.drawable.round_edittext_light else R.drawable.round_edittext_dark

            layout.setBackgroundResource(backgroundColor)
            modeButton.setImageResource(if (isBlackBackground) R.drawable.dark_mode else R.drawable.light_mode)
            editText.setBackgroundResource(editTextBackground)
            editText.setTextColor(textColor)
            decimal.setTextColor(textColor)

            buttons.forEach {
                it.setTextColor(textColor)
                it.setBackgroundResource(if (isBlackBackground) R.drawable.round_corner_white else R.drawable.round_corner_dark)
            }
        }

        buttons[0].setOnClickListener {
            if (calc) {
                editText.append("1")
                calc = false
                isEqualPressed = false
            } else {
                if (isEqualPressed) {
                    editText.text = ""
                    isEqualPressed = false
                }
                editText.append("1")
            }
        }

        for (i in 1 until buttons.size - 1) {
            buttons[i].setOnClickListener {
                val text = editText.text.toString()
                val operator = operators[i - 1]

                if (text.isEmpty()) {
                    editText.text = operator
                } else {
                    val lastOperatorIndex = operators.map { text.lastIndexOf(it) }.maxOrNull() ?: -1
                    val newText = if (lastOperatorIndex >= 0) text.substring(0, lastOperatorIndex) + operator else text + operator
                    editText.text = newText
                }

                calc = true
            }
        }

        buttons[buttons.size - 1].setOnClickListener {
            check = 0
            decimal.text = "Decimal : 0"
            editText.text = ""
        }

        buttons[buttons.size - 2].setOnClickListener {
            val text = editText.text.toString()

            if (check in 1..4) {
                val operators = listOf("+", "-", "×", "÷")
                if (text.endsWith(operators[check - 1])) {
                    check = 0
                }
            }

            if (!text.isEmpty()) {
                val newText = text.substring(0, text.length - 1)
                editText.text = newText
            }
        }

        buttons[buttons.size - 3].setOnClickListener {
            val text = editText.text.toString()
            if (!text.isEmpty()) {
                val parts = text.split("[+\\-×÷]".toRegex()).toTypedArray()
                if (parts.size == 2) {
                    val operand1Bits = parts[0].length
                    val operator = when {
                        text.contains("+") -> "+"
                        text.contains("-") -> "-"
                        text.contains("×") -> "×"
                        else -> "÷"
                    }
                    val operand2Bits = parts[1].length
                    var result = 0
                    var remainder = 0

                    when (operator) {
                        "+" -> result = operand1Bits + operand2Bits
                        "-" -> result = operand1Bits - operand2Bits
                        "×" -> result = operand1Bits * operand2Bits
                        "÷" -> {
                            if (operand2Bits != 0) {
                                result = operand1Bits / operand2Bits
                                remainder = operand1Bits % operand2Bits
                            }
                        }
                    }

                    val resultBuilder = StringBuilder()
                    if (result == 0) {
                        if (remainder != 0) {
                            repeat(remainder) { resultBuilder.append("1") }
                            resultBuilder.append("/")
                            repeat(operand2Bits) { resultBuilder.append("1") }
                        }
                    } else {
                        repeat(result) { resultBuilder.append("1") }
                        if (remainder > 0) {
                            resultBuilder.append("...")
                            repeat(remainder) { resultBuilder.append("1") }
                            resultBuilder.append("/")
                            repeat(operand2Bits) { resultBuilder.append("1") }
                        }
                    }
                    val resultText = resultBuilder.toString()

                    val decimalText: String = when {
                        result == 0 -> if (remainder == 0) "Decimal : 0" else "Decimal : $remainder/$operand2Bits"
                        result != 0 && remainder != 0 -> "Decimal : $result...$remainder"
                        else -> "Decimal : $result"
                    }

                    editText.text = resultText
                    decimal.text = decimalText

                    isEqualPressed = true
                    check = 0
                }
            }
        }
    }
}
