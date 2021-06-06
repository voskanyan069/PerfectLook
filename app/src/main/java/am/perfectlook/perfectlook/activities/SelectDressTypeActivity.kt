package am.perfectlook.perfectlook.activities

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.data.DressesTypes
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class SelectDressTypeActivity : AppCompatActivity() {
    private lateinit var dressesTypesContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_dress_type)

        val dressesTypes = intent.getStringArrayListExtra("dresses_types")!!
        Log.d("mTag", "dresses_types - $dressesTypes")

        init(dressesTypes)
    }

    private fun init(dressesTypes: List<String>) {
        dressesTypesContainer = findViewById(R.id.dresses_types_container)
        for (type in dressesTypes) {
            Log.d("mTag", "type - $type")
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(85, 10, 85, 10)
            val typeface: Typeface = ResourcesCompat.getFont(this, R.font.roboto_light)!!
            val typeButton = Button(this)
            typeButton.text = type
            typeButton.setBackgroundColor(Color.parseColor("#00ffffff"))
            typeButton.background = ContextCompat.getDrawable(this, R.drawable.top_bottom_border_button)
            typeButton.textSize = 20f
            typeButton.setTextColor(Color.parseColor("#333333"))
            typeButton.isAllCaps = false
            typeButton.typeface = typeface
            typeButton.layoutParams = layoutParams
            typeButton.setOnClickListener {
                onTypeClick(type.lowercase())
            }
            dressesTypesContainer.addView(typeButton)
        }
    }

    private fun onTypeClick(name: String) {
        Log.d("mTag", "name - $name")
        if (name == "tops" || name == "bottoms") {
            dressesTypesContainer.removeAllViews()
            init(DressesTypes.childTypes[name]!!)
        } else {
            val intent = Intent(this, SuitedDressesTypesActivity::class.java)
            intent.putExtra("dress_type", name)
            startActivity(intent)
        }
    }
}