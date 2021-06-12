package am.perfectlook.perfectlook.choosecalculate

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.activities.ThisShapeActivity
import am.perfectlook.perfectlook.states.BodyShape
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

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
            val errorMsg: String? = when {
                shouldersInput.text.isEmpty() -> "Please enter correct shoulders value"
                shouldersInput.text.toString().toInt() < 10 -> "Please enter correct shoulders value"
                waistInput.text.isEmpty() -> "Please enter correct waist value"
                waistInput.text.toString().toInt() < 10 -> "Please enter correct waist value"
                hipsInput.text.isEmpty() -> "Please enter correct hips value"
                hipsInput.text.toString().toInt() < 10 -> "Please enter correct hips value"
                else -> null
            }

            if (errorMsg == null) {
                val shoulders = shouldersInput.text.toString().toFloat()
                val waist = waistInput.text.toString().toFloat()
                val hips = hipsInput.text.toString().toFloat()
                val x: Float = shoulders.div(waist)
                val y: Float = hips.div(waist)
                calculate(x, y)
            } else {
                Snackbar.make(submitBtn, errorMsg.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculate(x: Float, y: Float) {
        val shape: BodyShape = when {
            (x > 1.25 && x < 2) && (y > 0.85 && y < 1.25) || (x > 1 && x < 2) && (y > 0.25 && y < 0.85) -> BodyShape.UPTURNED_TRIANGLE
            (x > 0.25 && x < 1) && (y > 1.25 && y < 2) || (x > 0.25 && x < 0.85) && (y > 0.85 && y < 1.25) -> BodyShape.TRIANGLE
            (x > 0.25 && x < 1) && (y > 0.25 && y < 0.85) -> BodyShape.ROUND
            (x > 0.85 && x < 1.25) && (y > 0.85 && y < 1.25) -> BodyShape.RECTANGLE
            (x > 1.25 && x < 2) && (y > 1.25 && y < 2) -> BodyShape.HOURGLASS
            else -> BodyShape.NONE
        }

        if (shape == BodyShape.NONE) {
            Snackbar.make(
                submitBtn,
                "Couldn\'t find your body shape, please try again with correct information",
                Snackbar.LENGTH_LONG
            ).show()
            return
        }
        val intent = Intent(this, ThisShapeActivity::class.java)
        intent.putExtra("shape", shape.value)
        startActivity(intent)
    }
}