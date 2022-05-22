package com.starishko.pdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.starishko.pdd.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.content.Intent
import com.starishko.pdd.RulesList
import android.annotation.SuppressLint

import com.starishko.pdd.TicketsList
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.starishko.pdd.Question.Postman
import com.starishko.pdd.Question
import android.app.Activity
import android.content.Context
import androidx.annotation.RequiresApi
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.FirebaseStorage
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnSuccessListener
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.tasks.OnFailureListener
import com.starishko.pdd.DialogClearSettings
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.starishko.pdd.RulesAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.starishko.pdd.TicketsAdapter
import com.starishko.pdd.RulesAdapter.RulesViewHolder
import com.starishko.pdd.TicketsAdapter.TicketsViewHolder
import androidx.appcompat.app.AppCompatDialogFragment
import android.content.DialogInterface
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Settings : AppCompatActivity() {
    var ad: AlertDialog.Builder? = null
    var context: Context? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        //кнопка "назад"
        val button_back = findViewById<View>(R.id.button_back) as Button
        button_back.setOnClickListener {
            val intent = Intent(this@Settings, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
        val clearSettings = findViewById<View>(R.id.statButton) as Button

        clearSettings.setOnClickListener {
            val myDialogFragment = DialogClearSettings()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            myDialogFragment.show(transaction, "dialog")
            //MainActivity.pref.edit().clear().apply();
        }
        val SignOut = findViewById<View>(R.id.signOutButton) as Button
        val title = "Выход из аккаунта"
        val message = "Вы уверены, что хотите выйти из аккаунта?"
        val button1String = "Да"
        val button2String = "Нет"
        SignOut.setOnClickListener {
            val myDialogFragment = AlertDialog.Builder(this@Settings, R.style.AlertDialog)
            myDialogFragment.setTitle(title) // заголовок
            myDialogFragment.setMessage(message) // сообщение
            myDialogFragment.setPositiveButton(button1String) { dialog, id ->
                FirebaseAuth.getInstance().signOut()
                try {
                    val intent = Intent(this@Settings, EmailPasswordActivity::class.java)
                    startActivity(intent)
                } catch (ignored: Exception) {
                }
            }
            myDialogFragment.setNegativeButton(button2String) { dialog, id -> dialog.cancel() }
            myDialogFragment.setCancelable(true)
            myDialogFragment.setOnCancelListener { }
            myDialogFragment.show()



        //val manager = supportFragmentManager
            //val transaction = manager.beginTransaction()
            //myDialogFragment.show(transaction, "dialog")
        }


        val versionApp = findViewById<View>(R.id.textVersion) as TextView

        versionApp.text = "Версия 0.1.1 \nот 18 мая 2022 г."
    }

    //Системная кнопка "назад"
    override fun onBackPressed() {
        val intent = Intent(this@Settings, MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}