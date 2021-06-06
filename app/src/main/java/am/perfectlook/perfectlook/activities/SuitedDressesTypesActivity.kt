package am.perfectlook.perfectlook.activities

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.adapters.SuitedDressAdapter
import am.perfectlook.perfectlook.models.SuitedDress
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

class SuitedDressesTypesActivity : AppCompatActivity() {
    private lateinit var shapeThumbnail: ImageView
    private lateinit var suitedDressesContainer: DiscreteScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suited_dresses_types)

        init()
    }

    private fun init() {
        shapeThumbnail = findViewById(R.id.suited_dress_type_thumbnail)
        suitedDressesContainer = findViewById(R.id.suited_dress_type_list)
        suitedDressesContainer.adapter = SuitedDressAdapter(
            listOf(
                SuitedDress("Test1", R.drawable.shape_hourglass),
                SuitedDress("Test2", R.drawable.shape_round),
                SuitedDress("Test3", R.drawable.shape_triangle),
                SuitedDress("Test1", R.drawable.shape_hourglass),
                SuitedDress("Test2", R.drawable.shape_round),
                SuitedDress("Test3", R.drawable.shape_triangle),
                SuitedDress("Test1", R.drawable.shape_hourglass),
                SuitedDress("Test2", R.drawable.shape_round),
                SuitedDress("Test3", R.drawable.shape_triangle),
            )
        )
        suitedDressesContainer.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.BOTTOM)
                .build()
        )
    }
}