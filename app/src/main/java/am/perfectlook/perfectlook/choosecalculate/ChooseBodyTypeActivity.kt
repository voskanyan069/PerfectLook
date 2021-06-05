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
        hourglassBtn.setOnClickListener { shapeClick(BodyShape.HOURGLASS) }
        roundBtn.setOnClickListener{ shapeClick(BodyShape.ROUND) }
        triangleBtn.setOnClickListener{ shapeClick(BodyShape.TRIANGLE) }
        rectangleBtn.setOnClickListener{ shapeClick(BodyShape.RECTANGLE) }
        upturnedTriangleBtn.setOnClickListener{ shapeClick(BodyShape.UPTURNED_TRIANGLE) }
    }

    private fun shapeClick(shape: BodyShape) {
        val intent = Intent(this, ThisShapeActivity::class.java)
        intent.putExtra("shape", shape.value)
        startActivity(intent)
    }
}