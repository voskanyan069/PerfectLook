package am.perfectlook.perfectlook.choosecalculate

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.activities.ThisShapeActivity
import am.perfectlook.perfectlook.states.BodyShape
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class ChooseBodyTypeActivity : AppCompatActivity() {
    private lateinit var hourglassBtn: ImageView
    private lateinit var roundBtn: ImageView
    private lateinit var triangleBtn: ImageView
    private lateinit var rectangleBtn: ImageView
    private lateinit var upturnedTriangleBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_body_type)

        init()
        chooseShape()
    }

    private fun init() {
        hourglassBtn = findViewById(R.id.choose_hourglass_btn)
        roundBtn = findViewById(R.id.choose_round_btn)
        triangleBtn = findViewById(R.id.choose_triangle_btn)
        rectangleBtn = findViewById(R.id.choose_rectangle_btn)
        upturnedTriangleBtn = findViewById(R.id.choose_upturned_triangle_btn)
    }

    private fun chooseShape() {
        hourglassBtn.setOnClickListener(shapeClickListener(BodyShape.HOURGLASS))
        roundBtn.setOnClickListener(shapeClickListener(BodyShape.ROUND))
        triangleBtn.setOnClickListener(shapeClickListener(BodyShape.TRIANGLE))
        rectangleBtn.setOnClickListener(shapeClickListener(BodyShape.RECTANGLE))
        upturnedTriangleBtn.setOnClickListener(shapeClickListener(BodyShape.UPTURNED_TRIANGLE))
    }

    private fun shapeClickListener(shape: BodyShape): View.OnClickListener? {
        val intent = Intent(this, ThisShapeActivity::class.java)
        intent.putExtra("shape", shape.value)
        startActivity(intent)
        return null
    }
}