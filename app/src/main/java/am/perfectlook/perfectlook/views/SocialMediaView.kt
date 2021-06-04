package am.perfectlook.perfectlook.views

import am.perfectlook.perfectlook.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout

class SocialMediaView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
    private val facebookBtn: ImageView
    private val twitterBtn: ImageView
    private val googleBtn: ImageView

    init {
        LayoutInflater
            .from(context)
            .inflate(
                R.layout.social_media_view,
                this,
                true
            )

        facebookBtn = findViewById(R.id.login_fb)
        twitterBtn = findViewById(R.id.login_tw)
        googleBtn = findViewById(R.id.login_gp)

        onFacebook()
        onTwitter()
        onGoogle()
    }

    private fun onFacebook() {
        facebookBtn.setOnClickListener {
            Log.d("mTag", "facebook login")
        }
    }

    private fun onTwitter() {
        twitterBtn.setOnClickListener {
            Log.d("mTag", "twitter login")
        }
    }

    private fun onGoogle() {
        googleBtn.setOnClickListener {
            Log.d("mTag", "google login")
        }
    }
}