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
import android.content.ActivityNotFoundException
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
import android.net.Uri
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class Info : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
        val database = FirebaseDatabase.getInstance().reference
        val currentUser= FirebaseAuth.getInstance().currentUser!!.uid
        var correctQuestions = 0
        var countCorrectAnswers: Int
        var correctTickets = 0

        database.child("Users").child(currentUser).child("Stats").child("countCorrectAnswers").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (numberTicket in 1..40) {
                    val value = dataSnapshot.child(numberTicket.toString()).value as Long
                    countCorrectAnswers = value.toInt()
                    correctQuestions += countCorrectAnswers
                    if (countCorrectAnswers == 20)
                        correctTickets += 1
                }
                val textGoodQuestionsOfAll = findViewById<View>(R.id.textViewGoodOfALL) as TextView
                textGoodQuestionsOfAll.text = "$correctQuestions/800"
                val progressQuestions = findViewById<View>(R.id.progressBarQuestions) as ProgressBar
                progressQuestions.max = 800
                progressQuestions.progress = correctQuestions
                val textGoodTickets = findViewById<View>(R.id.textViewTicketsRes) as TextView
                textGoodTickets.text = "$correctTickets/40"
                val progressTickets = findViewById<View>(R.id.progressBarTickets) as ProgressBar
                progressTickets.max = 40
                progressTickets.progress = correctTickets
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        val buttonCheckAuto= findViewById<View>(R.id.checkAuto) as Button
        buttonCheckAuto.setOnClickListener {
            try{
                val uri = Uri.parse("https://гибдд.рф/check/auto")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            catch(e : ActivityNotFoundException){
            }

        }
        val buttonCheckFine= findViewById<View>(R.id.checkFine) as Button
        buttonCheckFine.setOnClickListener {
            try{
                val uri = Uri.parse("https://гибдд.рф/check/fines")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            catch(e : ActivityNotFoundException){
            }

        }
        val buttonCheckDriver= findViewById<View>(R.id.checkDriver) as Button
        buttonCheckDriver.setOnClickListener {
            try{
                val uri = Uri.parse("https://гибдд.рф/check/driver#+")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            catch(e : ActivityNotFoundException){
            }

        }





        val button_back = findViewById<View>(R.id.button_back) as Button
        button_back.setOnClickListener {
            val intent = Intent(this@Info, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
    }

    //Системная кнопка "назад"
    override fun onBackPressed() {
        val intent = Intent(this@Info, MainMenu::class.java)
        startActivity(intent)
        finish()
    }


}