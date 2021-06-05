package am.perfectlook.perfectlook.activities

import am.perfectlook.perfectlook.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ThisShapeActivity : AppCompatActivity() {
    private lateinit var selectedShape: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_shape)

        selectedShape = intent.getStringExtra("shape")!!
        Log.d("mTag", "selected shape - $selectedShape")
    }
}