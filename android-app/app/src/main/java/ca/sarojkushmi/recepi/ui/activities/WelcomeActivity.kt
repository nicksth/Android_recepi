package ca.sarojkushmi.recepi.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ca.sarojkushmi.recepi.R
import ca.sarojkushmi.recepi.data.models.roomModels.UserModel
import com.google.android.material.button.MaterialButton

class WelcomeActivity : AppCompatActivity() {
    private lateinit var nextButton: MaterialButton
    private lateinit var usernameTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val user = intent.getParcelableExtra("userAccount") as? UserModel
        val password = intent.getStringExtra("password")

        usernameTextView = findViewById(R.id.username)
        usernameTextView.text = user?.username

        nextButton = findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, AddProfilePhotoActivity::class.java)
            intent.putExtra("userAccount", user)
            intent.putExtra("password", password)
            startActivity(intent)
        }
    }
}