package am.perfectlook.perfectlook.activities

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.adapters.SuitedDressAdapter
import am.perfectlook.perfectlook.data.BodyShapes
import am.perfectlook.perfectlook.data.Config
import am.perfectlook.perfectlook.models.SuitedDress
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

class SuitedDressesTypesActivity : AppCompatActivity() {
    private lateinit var shapeThumbnail: ImageView
    private lateinit var suitedDressesContainer: DiscreteScrollView
    private lateinit var suitedDressName: TextView

    private val suitedClothesList = ArrayList<SuitedDress>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suited_dresses_types)

        val dressType = intent.getStringExtra("dress_type")!!
        val suitedClothesList = intent.getStringArrayListExtra("suited_clothes")!!

        init()
        fillList(dressType, suitedClothesList)
    }

    private fun init() {
        shapeThumbnail = findViewById(R.id.suited_dress_type_thumbnail)
        suitedDressesContainer = findViewById(R.id.suited_dress_type_list)
        suitedDressName = findViewById(R.id.suited_dress_type_name)

        shapeThumbnail.setImageDrawable(ContextCompat
            .getDrawable(
                this,
                BodyShapes.shapes[Config.selectedShape.value]!!.thumbnail)
        )

        suitedDressesContainer.adapter = SuitedDressAdapter(suitedClothesList)
        suitedDressesContainer.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.BOTTOM)
                .build()
        )

        suitedDressesContainer.addOnItemChangedListener { _, adapterPosition ->
            suitedDressName.text = suitedClothesList[adapterPosition].name
        }
    }

    private fun fillList(clothType: String, clothesList: ArrayList<String>) {
        suitedClothesList.clear()
        for (cloth in clothesList) {
            val imageName = cloth.lowercase()
                .replace(' ', '_')
                .replace('-', '_')
            Log.d("mTag", "image_name - ${clothType}_${imageName}")
            val imageResId = resources.getIdentifier("${clothType}_${imageName}", "drawable", packageName)
            suitedClothesList.add(
                SuitedDress(cloth, imageResId)
            )
            Log.d("mTag", "added to list - $cloth")
        }
        suitedDressesContainer.adapter!!.notifyDataSetChanged()
    }
}