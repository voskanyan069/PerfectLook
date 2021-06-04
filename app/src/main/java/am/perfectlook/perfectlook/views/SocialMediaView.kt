package am.perfectlook.perfectlook.views

import am.perfectlook.perfectlook.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class SocialMediaView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
        init {
            LayoutInflater
                .from(context)
                .inflate(
                    R.layout.social_media_view,
                    this,
                    true
                )
        }
}