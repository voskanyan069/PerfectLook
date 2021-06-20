package am.perfectlook.perfectlook.account

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.activities.MainActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MyAccountActivity : AppCompatActivity() {
    private lateinit var settingsBtn: ImageView
    private lateinit var profileImageView: CircleImageView
    private lateinit var profileNameTextView: TextView
    private lateinit var profileSignOutBtn: TextView
    private lateinit var favoritesBtn: Button
    private lateinit var notificationsBtn: Button
    private lateinit var infoBtn: Button
    private lateinit var bodyTypeBtn: Button
    private lateinit var supportTeamBtn: Button
    private lateinit var termsBtn: Button

    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        init()
        buttonsClick()
    }

    private fun init() {
        settingsBtn = findViewById(R.id.my_account_settings_btn)
        profileImageView = findViewById(R.id.my_account_profile_img)
        profileNameTextView = findViewById(R.id.my_account_profile_name)
        profileSignOutBtn = findViewById(R.id.my_account_sign_out)
        favoritesBtn = findViewById(R.id.my_account_favorite_btn)
        notificationsBtn = findViewById(R.id.my_account_notification_btn)
        infoBtn = findViewById(R.id.my_account_info_btn)
        bodyTypeBtn = findViewById(R.id.my_account_body_type_btn)
        supportTeamBtn = findViewById(R.id.my_account_support_btn)
        termsBtn = findViewById(R.id.my_account_terms_btn)

        profileNameTextView.text = currentUser.displayName
        if (currentUser.photoUrl == Uri.EMPTY) {
            Picasso.get().load(R.drawable.account_placeholder).into(profileImageView)
        } else {
            Picasso.get().load(currentUser.photoUrl).into(profileImageView)
        }
    }

    private fun buttonsClick() {
        settingsBtn.setOnClickListener {  }
        profileImageView.setOnClickListener {  }
        profileSignOutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        favoritesBtn.setOnClickListener {  }
        notificationsBtn.setOnClickListener {  }
        infoBtn.setOnClickListener {  }
        bodyTypeBtn.setOnClickListener {  }
        supportTeamBtn.setOnClickListener {  }
        termsBtn.setOnClickListener {  }
    }
}