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
import android.view.View
import android.widget.*

class Tickets : AppCompatActivity(), View.OnClickListener {
    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null
    var numberTicket = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tickets)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("message")

        //кнопка "назад"
        val button_back = findViewById<View>(R.id.button_back) as Button
        button_back.setOnClickListener {
            val intent = Intent(this@Tickets, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
        val textView1 = findViewById<View>(R.id.textView1) as TextView
        val textView2 = findViewById<View>(R.id.textView2) as TextView
        val textView3 = findViewById<View>(R.id.textView3) as TextView
        val textView4 = findViewById<View>(R.id.textView4) as TextView
        val textView5 = findViewById<View>(R.id.textView5) as TextView
        val textView6 = findViewById<View>(R.id.textView6) as TextView
        val textView7 = findViewById<View>(R.id.textView7) as TextView
        val textView8 = findViewById<View>(R.id.textView8) as TextView
        val textView9 = findViewById<View>(R.id.textView9) as TextView
        val textView10 = findViewById<View>(R.id.textView10) as TextView
        val textView11 = findViewById<View>(R.id.textView11) as TextView
        val textView12 = findViewById<View>(R.id.textView12) as TextView
        val textView13 = findViewById<View>(R.id.textView13) as TextView
        val textView14 = findViewById<View>(R.id.textView14) as TextView
        val textView15 = findViewById<View>(R.id.textView15) as TextView
        val textView16 = findViewById<View>(R.id.textView16) as TextView
        val textView17 = findViewById<View>(R.id.textView17) as TextView
        val textView18 = findViewById<View>(R.id.textView18) as TextView
        val textView19 = findViewById<View>(R.id.textView19) as TextView
        val textView20 = findViewById<View>(R.id.textView20) as TextView
        val textView21 = findViewById<View>(R.id.textView21) as TextView
        val textView22 = findViewById<View>(R.id.textView22) as TextView
        val textView23 = findViewById<View>(R.id.textView23) as TextView
        val textView24 = findViewById<View>(R.id.textView24) as TextView
        val textView25 = findViewById<View>(R.id.textView25) as TextView
        val textView26 = findViewById<View>(R.id.textView26) as TextView
        val textView27 = findViewById<View>(R.id.textView27) as TextView
        val textView28 = findViewById<View>(R.id.textView28) as TextView
        val textView29 = findViewById<View>(R.id.textView29) as TextView
        val textView30 = findViewById<View>(R.id.textView30) as TextView
        val textView31 = findViewById<View>(R.id.textView31) as TextView
        val textView32 = findViewById<View>(R.id.textView32) as TextView
        val textView33 = findViewById<View>(R.id.textView33) as TextView
        val textView34 = findViewById<View>(R.id.textView34) as TextView
        val textView35 = findViewById<View>(R.id.textView35) as TextView
        val textView36 = findViewById<View>(R.id.textView36) as TextView
        val textView37 = findViewById<View>(R.id.textView37) as TextView
        val textView38 = findViewById<View>(R.id.textView38) as TextView
        val textView39 = findViewById<View>(R.id.textView39) as TextView
        val textView40 = findViewById<View>(R.id.textView40) as TextView
        textView1.setOnClickListener(this)
        textView2.setOnClickListener(this)
        textView3.setOnClickListener(this)
        textView4.setOnClickListener(this)
        textView5.setOnClickListener(this)
        textView6.setOnClickListener(this)
        textView7.setOnClickListener(this)
        textView8.setOnClickListener(this)
        textView9.setOnClickListener(this)
        textView10.setOnClickListener(this)
        textView11.setOnClickListener(this)
        textView12.setOnClickListener(this)
        textView13.setOnClickListener(this)
        textView14.setOnClickListener(this)
        textView15.setOnClickListener(this)
        textView16.setOnClickListener(this)
        textView17.setOnClickListener(this)
        textView18.setOnClickListener(this)
        textView19.setOnClickListener(this)
        textView20.setOnClickListener(this)
        textView21.setOnClickListener(this)
        textView22.setOnClickListener(this)
        textView23.setOnClickListener(this)
        textView24.setOnClickListener(this)
        textView25.setOnClickListener(this)
        textView26.setOnClickListener(this)
        textView27.setOnClickListener(this)
        textView28.setOnClickListener(this)
        textView29.setOnClickListener(this)
        textView30.setOnClickListener(this)
        textView31.setOnClickListener(this)
        textView32.setOnClickListener(this)
        textView33.setOnClickListener(this)
        textView34.setOnClickListener(this)
        textView35.setOnClickListener(this)
        textView36.setOnClickListener(this)
        textView37.setOnClickListener(this)
        textView38.setOnClickListener(this)
        textView39.setOnClickListener(this)
        textView40.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = Intent(this@Tickets, Ticket::class.java)
        when (view.id) {
            R.id.textView1 -> numberTicket = 1
            R.id.textView2 -> numberTicket = 2
            R.id.textView3 -> numberTicket = 3
            R.id.textView4 -> numberTicket = 4
            R.id.textView5 -> numberTicket = 5
            R.id.textView6 -> numberTicket = 6
            R.id.textView7 -> numberTicket = 7
            R.id.textView8 -> numberTicket = 8
            R.id.textView9 -> numberTicket = 9
            R.id.textView10 -> numberTicket = 10
            R.id.textView11 -> numberTicket = 11
            R.id.textView12 -> numberTicket = 12
            R.id.textView13 -> numberTicket = 13
            R.id.textView14 -> numberTicket = 14
            R.id.textView15 -> numberTicket = 15
            R.id.textView16 -> numberTicket = 16
            R.id.textView17 -> numberTicket = 17
            R.id.textView18 -> numberTicket = 18
            R.id.textView19 -> numberTicket = 19
            R.id.textView20 -> numberTicket = 20
            R.id.textView21 -> numberTicket = 21
            R.id.textView22 -> numberTicket = 22
            R.id.textView23 -> numberTicket = 23
            R.id.textView24 -> numberTicket = 24
            R.id.textView25 -> numberTicket = 25
            R.id.textView26 -> numberTicket = 26
            R.id.textView27 -> numberTicket = 27
            R.id.textView28 -> numberTicket = 28
            R.id.textView29 -> numberTicket = 29
            R.id.textView30 -> numberTicket = 30
            R.id.textView31 -> numberTicket = 31
            R.id.textView32 -> numberTicket = 32
            R.id.textView33 -> numberTicket = 33
            R.id.textView34 -> numberTicket = 34
            R.id.textView35 -> numberTicket = 35
            R.id.textView36 -> numberTicket = 36
            R.id.textView37 -> numberTicket = 37
            R.id.textView38 -> numberTicket = 38
            R.id.textView39 -> numberTicket = 39
            R.id.textView40 -> numberTicket = 40
        }
        intent.putExtra("numberTicket", numberTicket)
        startActivity(intent)
        finish()
    }

    //Системная кнопка "назад"
    override fun onBackPressed() {
        val intent = Intent(this@Tickets, MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}