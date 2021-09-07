package ca.sarojkushmi.recepi.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ca.sarojkushmi.recepi.R
import ca.sarojkushmi.recepi.viewModels.AuthViewModel
import ca.sarojkushmi.recepi.viewModels.SignedInUserViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEnterText: EditText
    private lateinit var passwordEnterText: EditText
    private lateinit var errorMessageTV : TextView
    private lateinit var loginButton : MaterialButton
    private lateinit var authViewModel: AuthViewModel
    private lateinit var signedInUserVM : SignedInUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        signedInUserVM = ViewModelProvider(this).get(SignedInUserViewModel::class.java)

        emailEnterText = findViewById(R.id.emailEnter)
        passwordEnterText = findViewById(R.id.passwordEnter)

        errorMessageTV = findViewById(R.id.errorMessage)

        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val email: String = emailEnterText.text.toString()
            val password: String = passwordEnterText.text.toString()
            if ((TextUtils.isEmpty(emailEnterText.text)) || (TextUtils.isEmpty(passwordEnterText.text))){
                errorMessageTV.text = "Email and/or Password are Empty"
            }
            else if (!checkFormatOfEmail(email)){
                errorMessageTV.text = "Email Format is Invalid"
            }
            else{
                val context : Context = this
                CoroutineScope(Dispatchers.IO).launch{
                    val check: Int = authViewModel.loginWithEmailAndPassword(email, password)
                    if (check == 1){
                        withContext(Dispatchers.Main){
                            errorMessageTV.text = "Email and/or Password are Incorrect"
                        }
                    }
                    else if (check == 2){
                        withContext(Dispatchers.Main){
                            errorMessageTV.text = "Account does not Exist"
                        }
                    }
                    else{
                        errorMessageTV.text=""
                        CoroutineScope(Dispatchers.IO).launch{
                            val idToken = authViewModel.getUserIdToken()
                            if (idToken != null) {
                                val checkSuccess = signedInUserVM.logInUser(context, idToken)
                                if (checkSuccess) {
                                    withContext(Dispatchers.Main) {
                                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                        startActivity(intent)
                                        Toast.makeText(baseContext, "Logged In", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private fun checkFormatOfEmail(email: String): Boolean{
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun goToSignUpPage(view: View){
        val signUpIntent: Intent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }

    fun forgotLogin(view: View){
        val forgotLoginIntent: Intent = Intent(this@LoginActivity, FindAccountActivity::class.java)
        startActivity(forgotLoginIntent)
    }
}