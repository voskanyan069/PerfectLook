package am.perfectlook.perfectlook.views

import am.perfectlook.perfectlook.R
import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class CircleImageView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
    private val image: ImageView

    init {
        LayoutInflater
            .from(context)
            .inflate(
                R.layout.circle_image_view,
                this,
                true
            )

        attrs.let {
            image = findViewById(R.id.circle_image_view_image)

            val styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.CircleImageView, 0, 0)
            val imageSize = styledAttributes.getInt(R.styleable.CircleImageView_image_size, 0)
            val imageSource = styledAttributes.getDrawable(R.styleable.CircleImageView_src)
            val layoutParams = image.layoutParams
            val imageSizeDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                imageSize.toFloat(),
                resources.displayMetrics
            ).roundToInt()

            layoutParams.width = imageSizeDp
            layoutParams.height = imageSizeDp

            image.setImageDrawable(imageSource)
            image.layoutParams = layoutParams
            styledAttributes.recycle()
        }
    }

    fun setImageUri(uri: Uri?) {
        Picasso.get().load(uri).error(R.drawable.account_placeholder).into(image)
    }
}