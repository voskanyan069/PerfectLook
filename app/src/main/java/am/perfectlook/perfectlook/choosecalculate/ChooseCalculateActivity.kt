package am.perfectlook.perfectlook.choosecalculate

import am.perfectlook.perfectlook.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseCalculateActivity : AppCompatActivity() {
    private lateinit var chooseBtn: Button
    private lateinit var calculateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_calculate)

        init()
        changeActivity()
    }

    private fun init() {
        chooseBtn = findViewById(R.id.choose_btn)
        calculateBtn = findViewById(R.id.calculate_btn)
    }

    private fun changeActivity() {
        chooseBtn.setOnClickListener {
            startActivity(Intent(this, ChooseActivity::class.java))
        }
        calculateBtn.setOnClickListener {
            startActivity(Intent(this, CalculateActivity::class.java))
        }
    }
}