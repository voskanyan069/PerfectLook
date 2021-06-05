package am.perfectlook.perfectlook.activities

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.models.BodyShapes
import am.perfectlook.perfectlook.models.Shape
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ThisShapeActivity : AppCompatActivity() {
    private lateinit var shapeTitle: TextView
    private lateinit var shapeThumbnail: ImageView
    private lateinit var shapeDescription: TextView

    private lateinit var selectedShapeString: String
    private lateinit var selectedShape: Shape

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_shape)

        selectedShapeString = intent.getStringExtra("shape")!!
        selectedShape = BodyShapes.shapes[selectedShapeString]!!

        init()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    private fun init() {
        shapeTitle = findViewById(R.id.this_shape_title)
        shapeThumbnail = findViewById(R.id.this_shape_thumbnail)
        shapeDescription = findViewById(R.id.this_shape_description)

        shapeTitle.text = getString(R.string.your_shape) + selectedShape.title
        shapeTitle.setTextColor(Color.parseColor(selectedShape.titleColor))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shapeThumbnail.setImageDrawable(getDrawable(selectedShape.thumbnail))
        }
        shapeDescription.text = selectedShape.description
    }
}