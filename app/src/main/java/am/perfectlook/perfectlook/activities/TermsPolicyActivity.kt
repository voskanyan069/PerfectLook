package am.perfectlook.perfectlook.activities

import am.perfectlook.perfectlook.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TermsPolicyActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView

    private lateinit var title: String
    private lateinit var content: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_policy)

        title = intent.getStringExtra("title")!!
        content = intent.getStringExtra("content")!!

        init()
    }

    private fun init() {
        titleTextView = findViewById(R.id.terms_policy_title)
        contentTextView = findViewById(R.id.terms_policy_content)

        titleTextView.text = title
        contentTextView.text = content
    }
}