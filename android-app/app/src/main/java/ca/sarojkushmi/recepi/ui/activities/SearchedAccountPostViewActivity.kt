package ca.sarojkushmi.recepi.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ca.sarojkushmi.recepi.R
import ca.sarojkushmi.recepi.data.models.roomModels.UserModel
import ca.sarojkushmi.recepi.data.models.roomModels.UserPost
import de.hdodenhof.circleimageview.CircleImageView

class SearchedAccountPostViewActivity : AppCompatActivity() {
    private lateinit var profilePic : CircleImageView
    private lateinit var usernameTV : TextView
    private lateinit var postPic : ImageView
    private lateinit var captionTV : TextView
    private lateinit var searchButton : ImageButton
    private lateinit var homeButton : ImageButton
    private lateinit var post : UserPost
    private lateinit var user : UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searched_account_profile_post)

        profilePic = findViewById(R.id.profileImage)
        usernameTV = findViewById(R.id.username)
        postPic = findViewById(R.id.postImage)
        captionTV = findViewById(R.id.caption)
        searchButton = findViewById(R.id.searchPageButton)
        homeButton = findViewById(R.id.homeButton)

        post = intent.getParcelableExtra("post")!!
        user = intent.getParcelableExtra("account")!!

        captionTV.text = post.caption
        usernameTV.text = user.username
        postPic.setImageURI(Uri.parse(post.uriImgPathString))
        profilePic.setImageURI(Uri.parse(user.profilePicPathFromUri))

        searchButton.setOnClickListener {
            val searchPageIntent = Intent(this@SearchedAccountPostViewActivity, SearchAccountsActivity::class.java)
            startActivity(searchPageIntent)
        }

        homeButton.setOnClickListener {
            val myAccountPageIntent: Intent = Intent(this@SearchedAccountPostViewActivity, MainActivity::class.java)
            startActivity(myAccountPageIntent)
        }

    }
}