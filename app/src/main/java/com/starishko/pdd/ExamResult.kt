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
import com.google.firebase.auth.FirebaseAuth

class ExamResult : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_ticket)

        //кнопка "назад"
        val button_back = findViewById<View>(R.id.button_back) as Button
        button_back.setOnClickListener {
            val intent = Intent(this@ExamResult, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
        val arguments = intent.extras!!
        val count = arguments.getInt("goodAnswers")
        var allAnswers = arguments.getInt("allAnswers")
        var wrongCount = 0
        val result = findViewById<TextView>(R.id.result)
        val database = FirebaseDatabase.getInstance().reference
        val currentUser= FirebaseAuth.getInstance().currentUser!!.uid

        if (allAnswers == 20) {
            wrongCount = allAnswers - count
            if (count == 20)
                result.text = "Экзамен пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
            else
                result.text = "Экзамен не пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
        }
        if (allAnswers == 25) {
            wrongCount = allAnswers - count
            if (count == 24)
                result.text = "Экзамен пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
            else
                result.text = "Экзамен не пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
        }
        if (allAnswers == 30) {
            wrongCount = allAnswers - count
            if (count == 28)
                result.text = "Экзамен пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
            else
                result.text = "Экзамен не пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
        }
        if (allAnswers < 20 ) {
            allAnswers = 20
            wrongCount = allAnswers - count
            result.text = "Время вышло!\nЭкзамен не пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
        }
        if (allAnswers in 21..24 ) {
            allAnswers = 25
            wrongCount = allAnswers - count
            result.text = "Время вышло!\nЭкзамен не пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
        }
        if (allAnswers in 26..29) {
            allAnswers = 30
            wrongCount = allAnswers - count
            result.text = "Время вышло!\nЭкзамен не пройден:\n$wrongCount ошибок\n$allAnswers вопросов"
        }


    }

    //Системная кнопка "назад"
    override fun onBackPressed() {
        val intent = Intent(this@ExamResult, MainMenu::class.java)
        startActivity(intent)
        finish()
    }


}