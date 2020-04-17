package com.solvabit.mate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.email_register
import kotlinx.android.synthetic.main.activity_registration.*



class registration : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                //or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)



        val auth = FirebaseAuth.getInstance()
        registerbtn_register.setOnClickListener{
            performRegister()

        }

    }

    private fun performRegister(){

        val email=email_register.text.toString()
        val password=password_register.text.toString()
        val fistname=name_register.text.toString()

        val phonenumber=phone_register.text.toString()
        val cnfPassword = cnf_password_register.text.toString()

        if(password == cnfPassword) {
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter credentials", Toast.LENGTH_SHORT).show()
                return
            }



            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Please Try again !!!! or User already registered",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@addOnCompleteListener
                    } else {
                        Log.d(
                            "Main",
                            "Account created successfully with uid : $(it.result.user.uid)"
                        )


                        val auth = FirebaseAuth.getInstance()
                        val user = auth.currentUser

                        user?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("Main", "Email sent.")
                                }
                            }
                        Toast.makeText(
                            this,
                            "Successfully registered. Check your email for verification",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, login::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)



                        saveUserToFirebaseDatabase()


                    }


                }
                .addOnFailureListener {
                    Log.d("Main", "Failed to create account : $(it.message)")
                }
        }
        else
        {
            Toast.makeText(
                this,
                "Password didn't matched ",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun saveUserToFirebaseDatabase(){
            val uid=FirebaseAuth.getInstance().uid ?: ""
            val ref=FirebaseDatabase.getInstance().getReference("/State/Maharastra/Army Institute Of Technology/Users/$uid")

            val user=User(uid, name_register.text.toString(),phone_register.text.toString())

        Log.d("Main", "location to enter data ")

            ref.setValue(user)
                .addOnSuccessListener {
                    Log.d("Main", "details saved successfully in database : $(it.result.user.uid)")
                }
    }

    class User(val uid: String, val firstname:String , val phonenumber : String)
}




