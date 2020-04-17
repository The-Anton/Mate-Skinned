package com.solvabit.mate

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login : BaseActivity() {

    var mAuth = FirebaseAuth.getInstance()

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                //or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)



        val loginBtn = findViewById<View>(R.id.registerbtn_login) as ImageView

        loginBtn.setOnClickListener(View.OnClickListener { view ->
            logi()
            //startActivity(Intent(this, Leaderboard::class.java))
        })


    }

    public fun logi () {
        val emailTxt = findViewById<View>(R.id.email_register) as EditText
        var email = emailTxt.text.toString()
        val passwordTxt = findViewById<View>(R.id.cnf_password_register) as EditText
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->

                val auth=FirebaseAuth.getInstance()
                val user=auth.currentUser
                if(user != null)
                {
                    if (user.isEmailVerified) {

                        val intent = Intent(this, home_screen::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

                        startActivity(intent)
                        Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_LONG).show()

                    }
                    else {



                        // No user is signed in

                        Toast.makeText(
                            this,
                            "Please verify your email before login.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }



                else {
                    Toast.makeText(this, "You are not registered.", Toast.LENGTH_SHORT).show()
                }
            })

        }else {
            Toast.makeText(this, "Please fill up the Credentials", Toast.LENGTH_SHORT).show()
        }



    }
}
