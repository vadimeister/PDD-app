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
import com.starishko.pdd.ExamQuestion.Postman
import com.starishko.pdd.Exam
import android.app.Activity
import android.app.AlertDialog
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
import kotlinx.android.synthetic.main.exam.*
import java.lang.IllegalStateException
import android.os.CountDownTimer
import kotlinx.coroutines.NonCancellable.cancel
import java.text.DecimalFormat
import java.text.NumberFormat


class Exam : FragmentActivity(), ExamQuestion.Postman {
    var goodAnswersCount = 0
    var allAnswers = 0
    var numberTicket = 0
    var numberQuestion = 0
    var part1 = 0
    var part2 = 0
    var part3 = 0
    var part4 = 0
    var firstWrongQuestion = 0
    var secondWrongQuestion = 0
    val goodAnswers: Array<Int> = Array(30){0}
    val randomTickets: Array<Int> = Array(30){0}
    var BoolTimer: Boolean = true
    @SuppressLint("Assert", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exam)
        val button_back = findViewById<View>(R.id.button_back) as Button
        button_back.setOnClickListener {
            BoolTimer == false
            val intent = Intent(this@Exam, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
        val timer = findViewById<View>(R.id.timer) as TextView
        Timer(1201000, timer) //1200000
        for (i in 0..29) {
            randomTickets[i] = (1..40).random()
            if (i in 20..24){
                for (j in 20..24){
                    if (i==j)
                        break
                    while (randomTickets[i] == randomTickets[j])
                        randomTickets[i] = (1..40).random()
                }
            }
            if (i in 25..29){
                for (j in 25..29){
                    if (i==j)
                        break
                    while (randomTickets[i] == randomTickets[j])
                        randomTickets[i] = (1..40).random()
                }
            }
        }


        val forFirstQuestion = randomTickets[0]
        val forFirstQuestionText = findViewById<TextView>(R.id.hidden)
        forFirstQuestionText.text = "$forFirstQuestion"

    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    fun Сhange(view: View) {
        val cool: TextView
        var fragment: Fragment? = null
        fragment = ExamQuestion()
        when (view.id) {
            R.id.text_square1 -> {
                numberQuestion = 1
                numberTicket = randomTickets[0]
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square2 -> {
                numberQuestion = 2
                numberTicket = randomTickets[1]
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square3 -> {
                numberQuestion = 3
                numberTicket = randomTickets[2]
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square4 -> {
                numberQuestion = 4
                numberTicket = randomTickets[3]
                cool = findViewById(R.id.text_square1)
            }
            R.id.text_square5 -> {
                numberQuestion = 5
                numberTicket = randomTickets[4]
                cool = findViewById(R.id.text_square2)
            }
            R.id.text_square6 -> {
                numberQuestion = 6
                numberTicket = randomTickets[5]
                cool = findViewById(R.id.text_square3)
            }
            R.id.text_square7 -> {
                numberQuestion = 7
                numberTicket = randomTickets[6]
                cool = findViewById(R.id.text_square4)
            }
            R.id.text_square8 -> {
                numberQuestion = 8
                numberTicket = randomTickets[7]
                cool = findViewById(R.id.text_square5)
            }
            R.id.text_square9 -> {
                numberQuestion = 9
                numberTicket = randomTickets[8]
                cool = findViewById(R.id.text_square6)
            }
            R.id.text_square10 -> {
                cool = findViewById(R.id.text_square7)
                numberTicket = randomTickets[9]
                numberQuestion = 10
            }
            R.id.text_square11 -> {
                cool = findViewById(R.id.text_square8)
                numberQuestion = 11
                numberTicket = randomTickets[10]
            }
            R.id.text_square12 -> {
                cool = findViewById(R.id.text_square9)
                numberQuestion = 12
                numberTicket = randomTickets[11]
            }
            R.id.text_square13 -> {
                cool = findViewById(R.id.text_square10)
                numberQuestion = 13
                numberTicket = randomTickets[12]
            }
            R.id.text_square14 -> {
                numberQuestion = 14
                numberTicket = randomTickets[13]
                cool = findViewById(R.id.text_square11)
            }
            R.id.text_square15 -> {
                numberQuestion = 15
                numberTicket = randomTickets[14]
                cool = findViewById(R.id.text_square12)
            }
            R.id.text_square16 -> {
                numberQuestion = 16
                numberTicket = randomTickets[15]
                cool = findViewById(R.id.text_square13)
            }
            R.id.text_square17 -> {
                numberQuestion = 17
                numberTicket = randomTickets[16]
                cool = findViewById(R.id.text_square14)
            }
            R.id.text_square18 -> {
                numberQuestion = 18
                numberTicket = randomTickets[17]
                cool = findViewById(R.id.text_square15)
            }
            R.id.text_square19 -> {
                numberQuestion = 19
                numberTicket = randomTickets[18]
                cool = findViewById(R.id.text_square16)
            }
            R.id.text_square20 -> {
                numberTicket = randomTickets[19]
                cool = findViewById(R.id.text_square17)
                numberQuestion = 20
            }
            R.id.text_square21 -> {
                numberTicket = randomTickets[20]
                cool = findViewById(R.id.text_square18)
                numberQuestion = 21
            }
            R.id.text_square22 -> {
                numberTicket = randomTickets[21]
                cool = findViewById(R.id.text_square19)
                numberQuestion = 22
            }
            R.id.text_square23 -> {
                numberTicket = randomTickets[22]
                cool = findViewById(R.id.text_square20)
                numberQuestion = 23
            }
            R.id.text_square24 -> {
                numberTicket = randomTickets[23]
                cool = findViewById(R.id.text_square21)
                numberQuestion = 24
            }
            R.id.text_square25 -> {
                numberTicket = randomTickets[24]
                cool = findViewById(R.id.text_square22)
                numberQuestion = 25
            }
            R.id.text_square26 -> {
                numberTicket = randomTickets[25]
                cool = findViewById(R.id.text_square23)
                numberQuestion = 26
            }
            R.id.text_square27 -> {
                numberTicket = randomTickets[26]
                cool = findViewById(R.id.text_square24)
                numberQuestion = 27
            }
            R.id.text_square28 -> {
                numberTicket = randomTickets[27]
                cool = findViewById(R.id.text_square25)
                numberQuestion = 28
            }
            R.id.text_square29 -> {
                numberTicket = randomTickets[28]
                cool = findViewById(R.id.text_square26)
                numberQuestion = 29
            }
            R.id.text_square30 -> {
                numberTicket = randomTickets[29]
                cool = findViewById(R.id.text_square27)
                numberQuestion = 30
            }




            else -> throw IllegalStateException("Unexpected value: " + view.id)
        }
        val scrollQuestion = findViewById<HorizontalScrollView>(R.id.horizontalScrollView)
        val Cod = cool.x
        scrollQuestion.post { scrollQuestion.smoothScrollTo(Cod.toInt() - 55, Cod.toInt() - 55) }
        val args = Bundle()
        args.putInt("numberTicket", numberTicket)
        args.putInt("numberQuestion", numberQuestion)
        args.putInt("firstWrongQuestion", firstWrongQuestion)
        args.putInt("secondWrongQuestion", secondWrongQuestion)
        fragment.setArguments(args)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fr_place, fragment)
        ft.commit()
    }

    //Системная кнопка "назад"
    override fun onBackPressed() {
        BoolTimer == false
        val intent = Intent(this@Exam, MainMenu::class.java)
        startActivity(intent)
        finish()
    }

    @SuppressLint("CutPasteId")
    override fun fragmentMail(numberOfCorrect: Int, numberQuestion: Int) {
        goodAnswers[numberQuestion-1] = numberOfCorrect
        allAnswers = allAnswers + 1
        if (allAnswers == 20) {
            goodAnswersCount = 0
            for (i in goodAnswers)
                goodAnswersCount += i
            if (goodAnswersCount == 20 || goodAnswersCount < 18){
                BoolTimer == false
                val intent = Intent(this@Exam, ExamResult::class.java)
                intent.putExtra("goodAnswers", goodAnswersCount)
                intent.putExtra("allAnswers", allAnswers)
                startActivity(intent)
                finish()
            }
            else {
                for (i in 1..5)
                    part1 += goodAnswers[i-1]
                for (i in 6..10)
                    part2 += goodAnswers[i-1]
                for (i in 11..15)
                    part3 += goodAnswers[i-1]
                for (i in 16..20)
                    part4 += goodAnswers[i-1]
                if (part1 < 4 || part2 < 4 || part3 < 4 || part4 < 4 ) {
                    val intent = Intent(this@Exam, ExamResult::class.java)
                    intent.putExtra("goodAnswers", goodAnswersCount)
                    intent.putExtra("allAnswers", allAnswers)
                    startActivity(intent)
                    finish()
                }
                else {
                    var temp = 0
                    for (i in 0..19){
                        if (goodAnswers[i] == 0){
                            if (temp == 0){
                                firstWrongQuestion = i+1
                                temp += 1
                            }
                            else {
                                secondWrongQuestion = i + 1
                            }
                        }

                    }
                    if (secondWrongQuestion == 0){

                        BoolTimer = false
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Экзамен")
                        builder.setMessage("Была допущена 1 ошибка \nВам предложены 5 дополнительных вопросов")
                        builder.setPositiveButton("ОК") { dialog, id ->
                            dialog.cancel()
                            BoolTimer = true
                            Timer(301000, timer) ///600000
                            val scrollQuestion = findViewById<HorizontalScrollView>(R.id.horizontalScrollView)
                            val cool = findViewById<View>(R.id.text_square18)
                            val Cod = cool.x
                            scrollQuestion.post { scrollQuestion.smoothScrollTo(Cod.toInt() - 55, Cod.toInt() - 55) }
                        }
                        builder.show()
                    }
                    val textSquare21 = findViewById<View>(R.id.text_square21)
                    textSquare21.visibility = View.VISIBLE
                    val textSquare22 = findViewById<View>(R.id.text_square22)
                    textSquare22.visibility = View.VISIBLE
                    val textSquare23 = findViewById<View>(R.id.text_square23)
                    textSquare23.visibility = View.VISIBLE
                    val textSquare24 = findViewById<View>(R.id.text_square24)
                    textSquare24.visibility = View.VISIBLE
                    val textSquare25 = findViewById<View>(R.id.text_square25)
                    textSquare25.visibility = View.VISIBLE
                    if (secondWrongQuestion != 0){
                        BoolTimer = false
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Экзамен")
                        builder.setMessage("Были допущены 2 ошибки \nВам предложены 10 дополнительных вопросов")
                        builder.setPositiveButton("ОК") { dialog, id ->
                            dialog.cancel()
                            BoolTimer = true
                            Timer(601000, timer) ///300000
                            val scrollQuestion = findViewById<HorizontalScrollView>(R.id.horizontalScrollView)
                            val cool = findViewById<View>(R.id.text_square22)
                            val Cod = cool.x
                            scrollQuestion.post { scrollQuestion.smoothScrollTo(Cod.toInt() - 55, Cod.toInt() - 55) }
                        }
                        builder.show()
                        val textSquare26 = findViewById<View>(R.id.text_square26)
                        textSquare26.visibility = View.VISIBLE
                        val textSquare27 = findViewById<View>(R.id.text_square27)
                        textSquare27.visibility = View.VISIBLE
                        val textSquare28 = findViewById<View>(R.id.text_square28)
                        textSquare28.visibility = View.VISIBLE
                        val textSquare29 = findViewById<View>(R.id.text_square29)
                        textSquare29.visibility = View.VISIBLE
                        val textSquare30 = findViewById<View>(R.id.text_square30)
                        textSquare30.visibility = View.VISIBLE
                    }
                }
            }
        }
        if (allAnswers == 25){
            if (secondWrongQuestion == 0) {
                goodAnswersCount = 0
                for (i in goodAnswers)
                    goodAnswersCount += i
                val intent = Intent(this@Exam, ExamResult::class.java)
                intent.putExtra("goodAnswers", goodAnswersCount)
                intent.putExtra("allAnswers", allAnswers)
                startActivity(intent)
                finish()

            }
        }
        if (allAnswers == 30){
            goodAnswersCount = 0
            for (i in goodAnswers)
                goodAnswersCount += i
            val intent = Intent(this@Exam, ExamResult::class.java)
            intent.putExtra("goodAnswers", goodAnswersCount)
            intent.putExtra("allAnswers", allAnswers)
            startActivity(intent)
            finish()

            }
        }

    fun Timer(Seconds: Int, textview: TextView) {

        object : CountDownTimer(Seconds.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                textview.text = f.format(min) + ":" + f.format(sec)
                if (BoolTimer == false){
                    cancel()
                }
            }
            override fun onFinish() {
                val builder = AlertDialog.Builder(this@Exam)
                builder.setTitle("Экзамен")
                builder.setMessage("Время проведения экзамена вышло")
                builder.setPositiveButton("ОК") { dialog, id ->
                    goodAnswersCount = 0
                    for (i in goodAnswers)
                        goodAnswersCount += i
                    try {
                        val intent = Intent(this@Exam, ExamResult::class.java)
                        intent.putExtra("goodAnswers", goodAnswersCount)
                        intent.putExtra("allAnswers", allAnswers)
                        startActivity(intent)
                        finish()
                    } catch (ignored: Exception) {
                    }
                }
                builder.show()
            }
        }.start()



    }

}