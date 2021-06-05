package am.perfectlook.perfectlook.activities

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.authentication.LoginActivity
import am.perfectlook.perfectlook.authentication.SignupActivity
import am.perfectlook.perfectlook.choosecalculate.ChooseCalculateActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var skipButton: Button
    private lateinit var termsPolicyButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        changeActivity()
    }

    private fun init() {
        loginButton = findViewById(R.id.home_login_btn)
        signupButton = findViewById(R.id.home_signup_btn)
        skipButton = findViewById(R.id.home_skip_btn)
        termsPolicyButton = findViewById(R.id.home_terms_policy_btn)
    }

    private fun changeActivity() {
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        skipButton.setOnClickListener {
            startActivity(Intent(this, ChooseCalculateActivity::class.java))
        }
    }
}