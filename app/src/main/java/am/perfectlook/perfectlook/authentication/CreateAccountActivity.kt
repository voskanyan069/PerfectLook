package am.perfectlook.perfectlook.authentication

import am.perfectlook.perfectlook.R
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateAccountActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val storeDb = Firebase.firestore

    private lateinit var titleView: TextView
    private lateinit var usernameInput: EditText
    private lateinit var countryInput: AutoCompleteTextView
    private lateinit var birthDateBtn: Button
    private lateinit var genderSpinner: Spinner
    private lateinit var submitBtn: Button

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var fname: String
    private lateinit var lname: String
    private lateinit var uname: String
    private lateinit var country: String
    private lateinit var birth: String
    private var isFemale: Boolean = true

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
        usernameInput = findViewById(R.id.create_account_username)
        countryInput = findViewById(R.id.create_account_country)
        birthDateBtn = findViewById(R.id.create_account_date_picker)
        genderSpinner = findViewById(R.id.create_account_gender)
        submitBtn = findViewById(R.id.create_account_submit)

        titleView.text = "$fname $lname"

        initCountryInput()
        initDatePicker()
        initGenderSelect()

        submitBtn.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {
        uname = usernameInput.text.toString()
        country = countryInput.text.toString()
        birth = birthDateBtn.text.toString()
        isFemale = genderSpinner.selectedItem == "Female"
        auth.createUserWithEmailAndPassword(email, password)
        val user = hashMapOf(
            "email" to email,
            "first_name" to fname,
            "last_name" to lname,
            "username" to uname,
            "country" to country,
            "birth" to birth,
            "is_female" to isFemale
        )

        storeDb
            .collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("mTag", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("mTag", "Error adding document", e)
            }
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
}