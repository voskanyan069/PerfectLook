package am.perfectlook.perfectlook.authentication

import am.perfectlook.perfectlook.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignupActivity : AppCompatActivity() {
    private lateinit var fnameInput: EditText
    private lateinit var lnameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        init()
    }

    private fun init() {
        fnameInput = findViewById(R.id.signup_fname_filed)
        lnameInput = findViewById(R.id.signup_lname_filed)
        emailInput = findViewById(R.id.signup_email_filed)
        passwordInput = findViewById(R.id.signup_password_filed)
        submitButton = findViewById(R.id.signup_submit)
    }
}