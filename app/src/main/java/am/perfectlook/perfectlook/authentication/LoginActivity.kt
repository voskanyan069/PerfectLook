package am.perfectlook.perfectlook.authentication

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.account.MyAccountActivity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var forgotPasswordText: TextView
    private lateinit var submitButton: Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        login()
    }

    private fun init() {
        emailInput = findViewById(R.id.login_email_filed)
        passwordInput = findViewById(R.id.login_password_filed)
        forgotPasswordText = findViewById(R.id.login_forgot_password)
        submitButton = findViewById(R.id.login_submit)
    }

    private fun login() {
        submitButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val emailPattern = Patterns.EMAIL_ADDRESS

            val errorMsg: String? = when {
                email.isBlank() -> "Please firstly enter the e-mail"
                !emailPattern.matcher(email).matches() -> "Please enter correct e-mail address"
                password.isBlank() -> "Please firstly enter the password"
                else -> null
            }

            if (errorMsg != null) {
                Snackbar.make(submitButton, errorMsg, Snackbar.LENGTH_LONG).show()
            } else {
                auth
                    .signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val intent = Intent(this, MyAccountActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
            }
        }
    }
}