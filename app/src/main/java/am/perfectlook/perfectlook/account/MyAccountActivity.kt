package am.perfectlook.perfectlook.account

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.activities.MainActivity
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class MyAccountActivity : AppCompatActivity() {
    private lateinit var settingsBtn: ImageView
    private lateinit var profileImageView: ShapeableImageView
    private lateinit var profileNameTextView: TextView
    private lateinit var profileSignOutBtn: TextView
    private lateinit var favoritesBtn: Button
    private lateinit var notificationsBtn: Button
    private lateinit var infoBtn: Button
    private lateinit var bodyTypeBtn: Button
    private lateinit var supportTeamBtn: Button
    private lateinit var termsBtn: Button

    private val auth = FirebaseAuth.getInstance()
    private val storeDb = Firebase.firestore
    private val storage = FirebaseStorage.getInstance().reference
    private val storageProfileImage = storage.child("users/profile_images")
    private val currentUser = auth.currentUser!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        init()
        buttonsClick()
    }

    private fun init() {
        settingsBtn = findViewById(R.id.my_account_settings_btn)
        profileImageView = findViewById(R.id.my_account_profile_img_container)
        profileNameTextView = findViewById(R.id.my_account_profile_name)
        profileSignOutBtn = findViewById(R.id.my_account_sign_out)
        favoritesBtn = findViewById(R.id.my_account_favorite_btn)
        notificationsBtn = findViewById(R.id.my_account_notification_btn)
        infoBtn = findViewById(R.id.my_account_info_btn)
        bodyTypeBtn = findViewById(R.id.my_account_body_type_btn)
        supportTeamBtn = findViewById(R.id.my_account_support_btn)
        termsBtn = findViewById(R.id.my_account_terms_btn)

        profileNameTextView.text = currentUser.displayName
        Log.d("mTag", "currentUser.displayName = ${currentUser.displayName}")
        Log.d("mTag", "currentUser.photoUrl = ${currentUser.photoUrl} | ${currentUser.photoUrl!!.javaClass.canonicalName}")
        if (currentUser.photoUrl != null) {
            Picasso.get().load(currentUser.photoUrl).into(profileImageView)
        } else {
            Picasso.get().load(R.drawable.account_placeholder).into(profileImageView)
        }
    }

    private fun buttonsClick() {
        settingsBtn.setOnClickListener {  }
        profileImageView.setOnClickListener {
            onImageClick()
        }
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

    private fun onImageClick() {
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.gallery_camera_bottom_sheet)

        val fromCamera: LinearLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet_camera)!!
        val fromGallery: LinearLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet_gallery)!!

        fromCamera.setOnClickListener {
            openCamera()
        }
        fromGallery.setOnClickListener {
            openGallery()
        }
        bottomSheetDialog.show()
    }

    private fun openCamera() {
        ImagePicker.with(this)
            .cropSquare()
            .cameraOnly()
            .start()
    }

    private fun openGallery() {
        ImagePicker.with(this)
            .cropSquare()
            .galleryOnly()
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val storageFilePath = storageProfileImage.child("${auth.currentUser!!.uid}.jpg")
            val uri: Uri = data?.data!!
            Picasso.get().load(uri).into(profileImageView)

            currentUser.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setPhotoUri(uri)
                    .build()
            )
            storageFilePath.putFile(uri)
                .addOnCompleteListener {
                    storageFilePath
                        .downloadUrl
                        .addOnSuccessListener {
                            Log.d("mTag", "Profile image was updated - $it")
                            storeDb
                                .collection("users")
                                .document(currentUser.uid)
                                .set(Pair("image_url", it.toString()))
                        }
                }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Snackbar.make(profileImageView, ImagePicker.getError(data), Snackbar.LENGTH_SHORT).show()
        }
    }
}