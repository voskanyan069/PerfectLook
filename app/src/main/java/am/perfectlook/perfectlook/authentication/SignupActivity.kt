package am.perfectlook.perfectlook.authentication

import am.perfectlook.perfectlook.R
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

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
        signup()
    }

    private fun init() {
        fnameInput = findViewById(R.id.signup_fname_filed)
        lnameInput = findViewById(R.id.signup_lname_filed)
        emailInput = findViewById(R.id.signup_email_filed)
        passwordInput = findViewById(R.id.signup_password_filed)
        submitButton = findViewById(R.id.signup_submit)
    }

    private fun signup() {
        submitButton.setOnClickListener {
            val fname = fnameInput.text.toString()
            val lname = lnameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val emailPattern = Patterns.EMAIL_ADDRESS

            val errorMsg: String? = when {
                fname.isBlank() -> "Please firstly enter the first name"
                lname.isBlank() -> "Please firstly enter the last name"
                email.isBlank() -> "Please firstly enter the e-mail"
                !emailPattern.matcher(email).matches() -> "Please enter correct e-mail address"
                password.isBlank() -> "Please firstly enter the password"
                else -> null
            }

            if (errorMsg != null) {
                Snackbar.make(submitButton, errorMsg, Snackbar.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, CreateAccountActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("first_name", fname)
                intent.putExtra("last_name", lname)
                startActivity(intent)
            }
        }
    }
}