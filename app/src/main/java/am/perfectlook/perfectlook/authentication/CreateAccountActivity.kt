package am.perfectlook.perfectlook.authentication

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.account.MyAccountActivity
import am.perfectlook.perfectlook.views.CircleImageView
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateAccountActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val storeDb = Firebase.firestore
    private val storage = FirebaseStorage.getInstance().reference
    private val storageProfileImage = storage.child("users/profile_images")

    private lateinit var titleView: TextView
    private lateinit var profileImage: CircleImageView
    private lateinit var usernameInput: EditText
    private lateinit var countryInput: AutoCompleteTextView
    private lateinit var birthDateBtn: Button
    private lateinit var genderSpinner: Spinner
    private lateinit var submitBtn: Button

    private var isImageChanged = false
    private lateinit var imageUri: Uri
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var fname: String
    private lateinit var lname: String
    private lateinit var uname: String
    private lateinit var country: String
    private lateinit var birth: String
    private var isFemale = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        email = intent.getStringExtra("email")!!
        password = intent.getStringExtra("password")!!
        fname = intent.getStringExtra("first_name")!!
        lname = intent.getStringExtra("last_name")!!

        init()
    }

    private fun init() {
        titleView = findViewById(R.id.create_account_title)
        profileImage = findViewById(R.id.create_account_image)
        usernameInput = findViewById(R.id.create_account_username)
        countryInput = findViewById(R.id.create_account_country)
        birthDateBtn = findViewById(R.id.create_account_date_picker)
        genderSpinner = findViewById(R.id.create_account_gender)
        submitBtn = findViewById(R.id.create_account_submit)

        titleView.text = "$fname $lname"

        initCountryInput()
        initDatePicker()
        initGenderSelect()

        profileImage.setOnClickListener {
            onImageClick()
        }
        submitBtn.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {
        uname = usernameInput.text.toString()
        country = countryInput.text.toString()
        birth = birthDateBtn.text.toString()
        isFemale = genderSpinner.selectedItem == "Female"
        val user = hashMapOf(
            "email" to email,
            "first_name" to fname,
            "last_name" to lname,
            "username" to uname,
            "country" to country,
            "birth" to birth,
            "is_female" to isFemale,
        )
        if (isImageChanged) {
            user["image_url"] = imageUri.toString()
        }

        auth.currentUser!!
            .updateProfile(
                UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(uname)
                    .setPhotoUri(if (isImageChanged) imageUri else Uri.EMPTY)
                    .build()
            )

        storeDb
            .collection("users")
            .document(auth.currentUser!!.uid)
            .set(user)
            .addOnSuccessListener {
                Log.d(
                    "mTag",
                    "DocumentSnapshot added with ID: ${auth.currentUser!!.uid}"
                )
                val intent = Intent(this, MyAccountActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                Log.e("mTag", "Error adding document", e)
                Snackbar.make(submitBtn, e.message.toString(), Snackbar.LENGTH_LONG).show()
            }
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

    private fun initCountryInput() {
        val countries = getCountries()
        val countryAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            countries
        )
        countryInput.setAdapter(countryAdapter)
    }

    private fun initDatePicker() {
        birthDateBtn.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Select date")
                .build()
            datePicker.show(supportFragmentManager, "mTag")
            datePicker.addOnPositiveButtonClickListener {
                birthDateBtn.text = convertLongToTime(it)
            }
        }
    }

    private fun initGenderSelect() {
        ArrayAdapter.createFromResource(
            this,
            R.array.gender,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }
    }

    private fun getCountries(): ArrayList<String> {
        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }
        countries.sort()
        return countries
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd MMM, yyyy")
        return format.format(date)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            profileImage.setImageUri(uri)

            val storageFilePath = storageProfileImage.child("${auth.currentUser!!.uid}.jpg")
            storageFilePath.putFile(uri)
                .addOnCompleteListener {
                    storageFilePath
                        .downloadUrl
                        .addOnSuccessListener {
                            Log.d("mTag", "Profile image was updated - $it")
                            imageUri = it
                            isImageChanged = true
                        }
                }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Snackbar.make(profileImage, ImagePicker.getError(data), Snackbar.LENGTH_SHORT).show()
        }
    }
}