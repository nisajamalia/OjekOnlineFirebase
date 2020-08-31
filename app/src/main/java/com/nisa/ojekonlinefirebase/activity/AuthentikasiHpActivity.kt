package com.nisa.ojekonlinefirebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.nisa.ojekonlinefirebase.MainActivity
import com.nisa.ojekonlinefirebase.R
import com.nisa.ojekonlinefirebase.utils.Constan
import kotlinx.android.synthetic.main.activity_authentikasi_hp.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AuthentikasiHpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentikasi_hp)

        //todo 13
        val key = intent.getStringExtra(Constan.key)
        val database = FirebaseDatabase.getInstance()
        val myref = database.getReference(Constan.tb_uaser)

        //update realtime database
        authentikasiSubmit.onClick {
            //statement
            if (authentikasiNomerHp.text.toString().isNotEmpty())
            {
                myref.child(key).child("hp")
                    .setValue(authentikasiNomerHp.text.toString())
                startActivity<MainActivity>()
            }
            else toast("tidak boleh kosong")
        }
    }
}