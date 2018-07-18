package hu.aut.android.hwswforum

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun registerClick(view: View) {
        if (!isFormValid()) {
            return
        }

        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(etEmail.text.toString(),
                        etPassword.text.toString())
                .addOnSuccessListener {
                    it.user.updateProfile(
                            UserProfileChangeRequest.Builder()
                                    .setDisplayName(userNameFromEmail(it.user.email!!))
                                    .build()
                    )

                    Toast.makeText(this@LoginActivity,
                            "Registration OK", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this@LoginActivity,
                            "Error: ${it.message}", Toast.LENGTH_LONG).show()
                }
    }

    fun loginClick(view: View) {
        if (!isFormValid()) {
            return
        }

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                .addOnSuccessListener { authResult ->
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this@LoginActivity, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
    }


    private fun isFormValid(): Boolean {
        return when {
            etEmail.text.isEmpty() -> {
                etEmail.error = "This field can not be empty"
                false
            }
            etPassword.text.isEmpty() -> {
                etPassword.error = "This field can not be empty"
                false
            }
            else -> true
        }
    }

    private fun userNameFromEmail(email: String) = email.substringBefore("@")
}
