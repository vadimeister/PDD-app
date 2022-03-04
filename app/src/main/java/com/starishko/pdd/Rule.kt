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
import android.widget.*

class Rule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rule)
        val arguments = intent.extras!!
        val numberTheme = arguments.getInt("numberTheme")
        val textRule = findViewById<TextView>(R.id.rule)
        val textThemeRule = findViewById<TextView>(R.id.ruleTheme)
        val database = FirebaseDatabase.getInstance().reference
        val myRefRule = database.child("Rules").child(numberTheme.toString())
        val myRefThemeRule = database.child("Rules").child(numberTheme.toString())
        myRefRule.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.child("textRule").getValue(
                    String::class.java
                )
                textRule.text = value
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        myRefThemeRule.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.child("Theme").getValue(
                    String::class.java
                )
                textThemeRule.text = value
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        val button_back = findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener {
            val intent = Intent(this@Rule, RulesList::class.java)
            startActivity(intent)
            finish()
        }
    }

    //Системная кнопка "назад"
    override fun onBackPressed() {
        val intent = Intent(this@Rule, RulesList::class.java)
        startActivity(intent)
        finish()
    }
}