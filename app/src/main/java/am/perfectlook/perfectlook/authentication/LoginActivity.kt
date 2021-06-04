package am.perfectlook.perfectlook.authentication

import am.perfectlook.perfectlook.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var forgotPasswordText: TextView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        emailInput = findViewById(R.id.login_email_filed)
        passwordInput = findViewById(R.id.login_password_filed)
        forgotPasswordText = findViewById(R.id.login_forgot_password)
        submitButton = findViewById(R.id.login_submit)
    }
}