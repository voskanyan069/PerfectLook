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
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var skipButton: Button
    private lateinit var termsPolicyButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        openTermsPolicyDialog()
        changeActivity()
    }

    private fun init() {
        loginButton = findViewById(R.id.home_login_btn)
        signupButton = findViewById(R.id.home_signup_btn)
        skipButton = findViewById(R.id.home_skip_btn)
        termsPolicyButton = findViewById(R.id.home_terms_policy_btn)
    }

    private fun openTermsPolicyDialog() {
        termsPolicyButton.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Terms and Privacy Policy")
                .setMessage("By clicking in continue, you agree to our " +
                        "Terms and Privacy Policy. We use a service " +
                        "that\'s pre-installed on your device to auto-update " +
                        "apps. You can turn off the service at any time.")
                .setNeutralButton("Continue") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("Policy") { _, _ ->
                    startTermsPolicyActivity(resources.getString(R.string.policy_title),
                        resources.getString(R.string.policy_content))
                }
                .setPositiveButton("Terms") { _, _ ->
                    startTermsPolicyActivity(resources.getString(R.string.terms_title),
                        resources.getString(R.string.terms_content))
                }
                .show()
        }
    }

    private fun startTermsPolicyActivity(title: String, content: String) {
        val intent = Intent(this, TermsPolicyActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("content", content)
        startActivity(intent)
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