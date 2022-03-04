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
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

class Ticket : FragmentActivity(), Postman {
    var goodAnswers = 0
    var allAnswers = 0
    var numberTicket = 0
    var numberQuestion = 0
    @SuppressLint("Assert", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ticket)
        val button_back = findViewById<View>(R.id.button_back) as Button
        button_back.setOnClickListener {
            val intent = Intent(this@Ticket, TicketsList::class.java)
            startActivity(intent)
            finish()
        }
        val arguments = intent.extras!!
        numberTicket = arguments.getInt("numberTicket")

        // номер текущего вопроса
        val questionNumberTextView = findViewById<TextView>(R.id.text_numberOfTicket)
        questionNumberTextView.text = "Билет №$numberTicket"
    }

    fun Сhange(view: View) {
        val cool: TextView
        var fragment: Fragment? = null
        fragment = Question()
        when (view.id) {
            R.id.text_square1 -> {
                numberQuestion = 1
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square2 -> {
                numberQuestion = 2
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square3 -> {
                numberQuestion = 3
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square4 -> {
                numberQuestion = 4
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square5 -> {
                numberQuestion = 5
                cool = findViewById(R.id.text_square2)
            }
            R.id.text_square6 -> {
                numberQuestion = 6
                cool = findViewById(R.id.text_square3)
            }
            R.id.text_square7 -> {
                numberQuestion = 7
                cool = findViewById(R.id.text_square4)
            }
            R.id.text_square8 -> {
                numberQuestion = 8
                cool = findViewById(R.id.text_square5)
            }
            R.id.text_square9 -> {
                numberQuestion = 9
                cool = findViewById(R.id.text_square6)
            }
            R.id.text_square10 -> {
                cool = findViewById(R.id.text_square7)
                numberQuestion = 10
            }
            R.id.text_square11 -> {
                cool = findViewById(R.id.text_square8)
                numberQuestion = 11
            }
            R.id.text_square12 -> {
                cool = findViewById(R.id.text_square9)
                numberQuestion = 12
            }
            R.id.text_square13 -> {
                cool = findViewById(R.id.text_square10)
                numberQuestion = 13
            }
            R.id.text_square14 -> {
                numberQuestion = 14
                cool = findViewById(R.id.text_square11)
            }
            R.id.text_square15 -> {
                numberQuestion = 15
                cool = findViewById(R.id.text_square12)
            }
            R.id.text_square16 -> {
                numberQuestion = 16
                cool = findViewById(R.id.text_square13)
            }
            R.id.text_square17 -> {
                numberQuestion = 17
                cool = findViewById(R.id.text_square14)
            }
            R.id.text_square18 -> {
                numberQuestion = 18
                cool = findViewById(R.id.text_square15)
            }
            R.id.text_square19 -> {
                numberQuestion = 19
                cool = findViewById(R.id.text_square16)
            }
            R.id.text_square20 -> {
                cool = findViewById(R.id.text_square17)
                numberQuestion = 20
            }
            else -> throw IllegalStateException("Unexpected value: " + view.id)
        }
        val scrollQuestion = findViewById<HorizontalScrollView>(R.id.horizontalScrollView)
        val Cod = cool.x
        scrollQuestion.post { scrollQuestion.smoothScrollTo(Cod.toInt() - 55, Cod.toInt() - 55) }
        val args = Bundle()
        args.putInt("numberTicket", numberTicket)
        args.putInt("numberQuestion", numberQuestion)
        fragment.setArguments(args)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fr_place, fragment)
        ft.commit()
    }

    //Системная кнопка "назад"
    override fun onBackPressed() {
        val intent = Intent(this@Ticket, TicketsList::class.java)
        startActivity(intent)
        finish()
    }

    override fun fragmentMail(numberOfCorrect: Int) {
        goodAnswers = goodAnswers + numberOfCorrect
        allAnswers = allAnswers + 1
        if (allAnswers >= 20) {
            val intent = Intent(this@Ticket, Result::class.java)
            intent.putExtra("goodAnswers", goodAnswers)
            intent.putExtra("numberTicket", numberTicket)
            startActivity(intent)
            finish()
        }
    }
}