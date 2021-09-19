package com.example.toyshop

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var btn2: Button
    lateinit var editTextemail: EditText
    lateinit var editTextpassward: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btn)
        editTextemail = findViewById(R.id.emailEt)
        editTextpassward = findViewById(R.id.passwordEt)
        btn2 = findViewById(R.id.btn2)

        btn2.setOnClickListener {
            intent = Intent(this, Ragistration::class.java)
            startActivity(intent)
        }
        val auth=FirebaseAuth.getInstance()
        btn.setOnClickListener {
            if (editTextemail.text.toString().isEmpty() && editTextpassward.length()==0) {
                editTextemail.setError("please enter email")
                editTextpassward.setError("please enter password")

            }
            else{
                auth.signInWithEmailAndPassword(editTextemail.text.toString(), editTextpassward.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.e(TAG, "Successfully signed in with email link!")
                            Toast.makeText(this,"suecc",Toast.LENGTH_LONG).show()
                            val result = task.result
                            val intent=Intent(this,Shop::class.java)
                            startActivity(intent)
                            // You can access the new user via result.getUser()
                            // Additional user info profile *not* available via:
                            // result.getAdditionalUserInfo().getProfile() == null
                            // You can check if the user is new or existing:
                            // result.getAdditionalUserInfo().isNewUser()
                        } else {
                            Log.e(TAG, "Error signing in with email link", task.exception)
                            Toast.makeText(this,"sign up your account",Toast.LENGTH_LONG).show()
                        }
                    }
            }
//                auth.signInWithEmailAndPassword(editTextemail.text.toString(),editTextpassward.text.toString())
//                    .addOnCompleteListener { task->
//                        if (task.isSuccessful){
//                            Toast.makeText(this,"suecc",Toast.LENGTH_LONG).show()
//                            val result=task.result
//                        }else{
//                            Toast.makeText(this,"eee",Toast.LENGTH_LONG).show()
//
//                        }
//                    }

//            } else if (editTextpassward.length()==0){
//                editTextpassward.setError("please enter password")


//        }else{  auth.createUserWithEmailAndPassword(editTextemail.text.toString(),editTextpassward.text.toString())
//            .addOnCompleteListener { task->
//                if (task.isSuccessful){
//                    storedata()
//                    Log.e("sucessfull","suseccesfull")
//                    val result= task.result
//
//                    Log.e("result>>>>",result.toString())
//                }else{
//                    Log.e("faile","faile")
//                }

            }

        }


    }
