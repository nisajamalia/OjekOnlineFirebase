package com.nisa.ojekonlinefirebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.nisa.ojekonlinefirebase.R
import com.nisa.ojekonlinefirebase.model.Users
import com.nisa.ojekonlinefirebase.utils.Constan
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SignUpActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        signUpButton.onClick {
            if (signUpEmail.text.isNotEmpty() &&
                signUpName.text.isNotEmpty() &&
                signUpHp.text.isNotEmpty() &&
                signUpPassword.text.isNotEmpty() &&
                signUpConfirmPassword.text.isNotEmpty()

            ){
                authUserSignUp(
                    signUpEmail.text.toString(),
                    signUpPassword.text.toString()
                )

            }

        }
    }
    //todo 1
    //proses authentication
    private fun authUserSignUp(email:String, pass:String):Boolean?{
        auth = FirebaseAuth.getInstance()

        var status:Boolean? = null
        val TAG = "tag"

        auth?.createUserWithEmailAndPassword(email, pass)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful){
                    if (insertUser(
                            signUpName.text.toString(),
                            signUpEmail.text.toString(),
                            signUpHp.text.toString(),
                            task.result?.user!!
                        )){
                        startActivity<LoginActivity>()
                    }
                } else {
                    status = false
                }
            }
        return status
    }

    //todo 3
    //proses menambahkan data user ke realtime database
    private fun insertUser(
        name: String,
        email: String,
        hp: String,
        users: FirebaseUser
    ): Boolean {
        var user = Users()
        user.uid = users.uid
        user.name = name
        user.email = email
        user.hp = hp

        val database = FirebaseDatabase.getInstance()

        //todo 4


        //id yg masuk ke database
        var key = database.reference.push().key

        //nama table
        val myRef = database.getReference(Constan.tb_uaser)

        //menyimpan ke database
        myRef.child(key!!).setValue(user)

        return true
    }

}