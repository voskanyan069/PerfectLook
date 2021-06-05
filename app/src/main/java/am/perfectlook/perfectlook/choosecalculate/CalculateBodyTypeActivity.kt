package am.perfectlook.perfectlook.choosecalculate

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.activities.ThisShapeActivity
import am.perfectlook.perfectlook.states.BodyShape
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CalculateBodyTypeActivity : AppCompatActivity() {
    private lateinit var shouldersInput: EditText
    private lateinit var waistInput: EditText
    private lateinit var hipsInput: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_body_type)

        init()
        onCalculate()
    }

    private fun init() {
        shouldersInput = findViewById(R.id.calc_shoulders_input)
        waistInput = findViewById(R.id.calc_waist_input)
        hipsInput = findViewById(R.id.calc_hips_input)
        submitBtn = findViewById(R.id.calculator_submit_btn)
    }

    private fun onCalculate() {
        submitBtn.setOnClickListener {
            val shoulders = shouldersInput.text.toString().toInt()
            val waist = waistInput.text.toString().toInt()
            val hips = hipsInput.text.toString().toInt()
            val x = shoulders / waist
            val y = hips / waist
            calculate(x, y)
        }
    }

//    TODO: CALCULATOR WORKING INCORRECT
    private fun calculate(x: Int, y: Int) {
        val shape: BodyShape = if (((x > 1.25 && x < 2) && (y > 0.85 && y < 1.25)) ||
            ((x in 2 downTo 1) && (y > 0.25 && y < 0.85))
        ) {
            BodyShape.UPTURNED_TRIANGLE
        } else if (((x > 0.25 && x < 1) && (y > 1.25 && y < 2)) ||
            ((x > 0.25 && x < 0.85) && (y > 0.85 && y < 1.25))
        ) {
            BodyShape.TRIANGLE
        } else if (((x > 0.25 && x < 1) && (y > 0.25 && y < 0.85))) {
            BodyShape.ROUND
        } else if (((x > 0.85 && x < 1.25) && (y > 0.85 && y < 1.25))) {
            BodyShape.RECTANGLE
        } else if (((x > 1.25 && x < 2) && (y > 1.25 && y < 2))) {
            BodyShape.HOURGLASS
        } else {
            BodyShape.NONE
        }
        val intent = Intent(this, ThisShapeActivity::class.java)
        intent.putExtra("shape", shape.value)
        startActivity(intent)
    }
}