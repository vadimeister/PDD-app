package com.starishko.pdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.starishko.pdd.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.annotation.SuppressLint
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
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import java.io.File
import java.io.IOException
import java.lang.ClassCastException
import java.util.*

class ExamQuestion : Fragment(), View.OnClickListener {
    interface Postman {
        fun fragmentMail(numberOfCorrect: Int, numberQuestion: Int)
    }

    var counter = 0
    var numberQuestion = 0
    var numberQuestion_ = 0
    var activity: Activity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            activity = context
        }
    }

    private var correctAnswer: CharSequence? = null
    private var answer1: TextView? = null
    private var answer2: TextView? = null
    private var answer3: TextView? = null
    private var answer4: TextView? = null
    private var answer_correct_id: TextView? = null
    private var question: TextView? = null
    private var value1: String? = null
    private var value2: String? = null
    private var value3: String? = null
    private var value4: String? = null
    private var tip: TextView? = null
    private var valueTip: String? = null
    private var textSquare: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var buttonCorrectAnswer: TextView? = null

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.question, container, false)
        val args = arguments
        val numberTicket: Int = args?.getInt("numberTicket") ?: forFirstQuestion()
        numberQuestion = args?.getInt("numberQuestion") ?: 1
        chooseTextSquare(numberQuestion)
        textSquare!!.setBackgroundResource(R.drawable.now)

        numberQuestion_ = numberQuestion
        if (numberQuestion in 21..25) {
            numberQuestion = args?.getInt("firstWrongQuestion") ?: 1
        }
        if (numberQuestion in 26..30) {
            numberQuestion= args?.getInt("secondWrongQuestion") ?: 1
        }
        tip = view.findViewById(R.id.tip)
        answer1 = view.findViewById(R.id.answer1)
        answer2 = view.findViewById(R.id.answer2)
        answer3 = view.findViewById(R.id.answer3)
        answer4 = view.findViewById(R.id.answer4)
        question = view.findViewById(R.id.question)
        val Image = view.findViewById<View>(R.id.ticketImageView) as ImageView
        val database = FirebaseDatabase.getInstance().reference
        val myRefQuest = database.child("Tickets").child(numberTicket.toString())
            .child(numberQuestion.toString())
        val myRefAnswers = database.child("Tickets").child(numberTicket.toString())
            .child(numberQuestion.toString()).child("Answers")
        val myRefCorrect = database.child("Tickets").child(numberTicket.toString())
            .child(numberQuestion.toString())
        val photoNumberTicket = "" + numberTicket
        val photoNumberQuestion = numberTicket.toString() + "_" + numberQuestion
        val islandRef =
            FirebaseStorage.getInstance().reference.child("Tickets").child(photoNumberTicket).child(
                "$photoNumberQuestion.jpg"
            )
        GlideApp.with(requireContext())
            .load(islandRef)
            .into(Image)


        //showImage(islandRef, Image, photoNumberQuestion)
        myRefQuest.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.child("question").getValue(
                    String::class.java
                )
                question!!.setText(value)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        myRefAnswers.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                value1 = dataSnapshot.child("Answer1").getValue(String::class.java)
                answer1!!.setText(value1)
                value2 = dataSnapshot.child("Answer2").getValue(String::class.java)
                answer2!!.setText(value2)
                value3 = dataSnapshot.child("Answer3").getValue(String::class.java)
                if (value3 != null) answer3!!.setText(value3) else {
                    answer3!!.setVisibility(View.GONE)
                    answer3!!.setEnabled(false)
                }
                value4 = dataSnapshot.child("Answer4").getValue(String::class.java)
                if (value4 != null) answer4!!.setText(value4) else {
                    answer4!!.setVisibility(View.GONE)
                    answer4!!.setEnabled(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        answer_correct_id = view.findViewById(R.id.Correct_answer_id)
        myRefCorrect.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var a = 0
                if (snapshot.exists() && snapshot.child("Correct_answer_id").value != null) a =
                    (Objects.requireNonNull(snapshot.child("Correct_answer_id").value) as Long).toInt()
                val value = a.toString()
                answer_correct_id!!.setText(value)
            }

            override fun onCancelled(error: DatabaseError) {}
        })


        tip!!.setOnClickListener {
            val myRefTip= database.child("Tickets").child(numberTicket.toString())
                .child(numberQuestion.toString())
            myRefTip.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    valueTip = dataSnapshot.child("answer_tip").getValue(String::class.java)
                    tip!!.text = valueTip
                    tip!!.gravity = Gravity.NO_GRAVITY
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }
        answer1!!.setOnClickListener(this)
        answer2!!.setOnClickListener(this)
        answer3!!.setOnClickListener(this)
        answer4!!.setOnClickListener(this)
        return view
    }

    private fun forFirstQuestion(): Int {
        val textnumberForFirstQuestion = requireActivity().findViewById<TextView>(R.id.hidden)
        val stringForFirstQuestion = textnumberForFirstQuestion.text.toString()
        val numberTicket = stringForFirstQuestion.substring(0).toInt()
        return numberTicket
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onDetach() {
        super.onDetach()
        val bgAlready = textSquare!!.background.constantState ===
                Objects.requireNonNull(
                    Objects.requireNonNull(
                        context
                    )?.let {
                        ContextCompat.getDrawable(
                            it, R.drawable.now
                        )
                    }
                )?.constantState
        if (bgAlready) {
            textSquare!!.setBackgroundResource(R.color.darkBlue)
            // assertThat(shadowOf((BitmapDrawable)view.getBackground()).getCreatedFromResId()).isEqualTo(R.drawable.an_image);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onClick(view: View) {
        correct()
        textSquare!!.isEnabled = false
        val answerGiven: CharSequence
        when (view.id) {
            R.id.answer1 -> {
                answerGiven = answer1!!.text
                answer1!!.setBackgroundResource(R.color.answerExam)
                textSquare!!.setBackgroundResource(R.color.answerExam)
                if (answerGiven === correctAnswer) { //да - это верный ответ
                    counter++
                    try {
                        (activity as Postman?)!!.fragmentMail(counter, numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                } else {
                    try {
                        (activity as Postman?)!!.fragmentMail(counter, numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                }
                disableButtons()
            }
            R.id.answer2 -> {
                answerGiven = answer2!!.text
                answer2!!.setBackgroundResource(R.color.answerExam)
                textSquare!!.setBackgroundResource(R.color.answerExam)
                if (answerGiven === correctAnswer) { //да - это верный ответ
                    counter++
                    try {
                        (activity as Postman?)!!.fragmentMail(counter,numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                } else {
                    try {
                        (activity as Postman?)!!.fragmentMail(counter,numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                }
                disableButtons()
            }
            R.id.answer3 -> {
                answerGiven = answer3!!.text
                answer3!!.setBackgroundResource(R.color.answerExam)
                textSquare!!.setBackgroundResource(R.color.answerExam)
                if (answerGiven === correctAnswer) { //да - это верный ответ
                    counter++
                    try {
                        (activity as Postman?)!!.fragmentMail(counter,numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                } else {
                    try {
                        (activity as Postman?)!!.fragmentMail(counter,numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                }
                disableButtons()
            }
            R.id.answer4 -> {
                //то же самое, что и предыдущий case, только используем следующую кнопку
                answerGiven = answer4!!.text
                answer4!!.setBackgroundResource(R.color.answerExam)
                textSquare!!.setBackgroundResource(R.color.answerExam)
                if (answerGiven === correctAnswer) { //да - это верный ответ
                    counter++
                    try {
                        (activity as Postman?)!!.fragmentMail(counter,numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                } else { //нет, неверно!
                    try {
                        (activity as Postman?)!!.fragmentMail(counter,numberQuestion_)
                    } catch (ignored: ClassCastException) {
                    }
                }
                disableButtons()
            }
        }
    }

    private fun correct() {
        val correct = answer_correct_id!!.text.toString()
        val correct_id = correct.toInt()
        if (correct_id == 1) {
            correctAnswer = answer1!!.text as CharSequence
            buttonCorrectAnswer = answer1
        }
        if (correct_id == 2) {
            correctAnswer = answer2!!.text as CharSequence
            buttonCorrectAnswer = answer2
        }
        if (correct_id == 3) {
            correctAnswer = answer3!!.text as CharSequence
            buttonCorrectAnswer = answer3
        }
        if (correct_id == 4) {
            correctAnswer = answer4!!.text as CharSequence
            buttonCorrectAnswer = answer4
        }
    }

    private fun disableButtons() {
        answer1!!.isEnabled = false
        answer2!!.isEnabled = false
        answer3!!.isEnabled = false
        answer4!!.isEnabled = false
    }

    private fun showImage(
        islandRef: StorageReference,
        Image: ImageView,
        photoNumberQuestion: String
    ) {
        var localFile: File? = null
        try {
            localFile = File.createTempFile(photoNumberQuestion, "jpg")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val finalLocalFile = localFile
        islandRef.getFile(localFile!!).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(finalLocalFile!!.absolutePath)
            Image.setImageBitmap(bitmap)

            // Local temp file has been created
        }
            .addOnFailureListener { Image.setImageBitmap(null) }
    }

    fun showToast(s: String?) {
        Toast.makeText(requireActivity().applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private fun chooseTextSquare(numberQuestion: Int) {
        when (numberQuestion) {
            1 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square1)
            2 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square2)
            3 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square3)
            4 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square4)
            5 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square5)
            6 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square6)
            7 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square7)
            8 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square8)
            9 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square9)
            10 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square10)
            11 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square11)
            12 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square12)
            13 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square13)
            14 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square14)
            15 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square15)
            16 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square16)
            17 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square17)
            18 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square18)
            19 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square19)
            20 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square20)
            21 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square21)
            22 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square22)
            23 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square23)
            24 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square24)
            25 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square25)
            26 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square26)
            27 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square27)
            28-> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square28)
            29 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square29)
            30 -> textSquare =
                Objects.requireNonNull(getActivity())!!.findViewById(R.id.text_square30)

        }
    }
}

