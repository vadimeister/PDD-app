package com.starishko.pdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.starishko.pdd.R
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.content.Intent
import com.starishko.pdd.RulesList
import android.annotation.SuppressLint

import android.widget.ProgressBar
import com.starishko.pdd.TicketsList
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.starishko.pdd.Question.Postman
import com.starishko.pdd.Question
import android.widget.HorizontalScrollView
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
import android.widget.Toast
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
import com.google.firebase.auth.FirebaseAuth

class TicketsAdapter internal constructor(numberOfItems: Int, parent: Context?) :
    RecyclerView.Adapter<TicketsViewHolder>() {
    private val numberTicket: Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        @SuppressLint("ResourceType") val view =
            inflater.inflate(R.layout.number_list_ticket, parent, false)
        return TicketsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(position + 1)
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, Ticket::class.java)
            intent.putExtra("numberTicket", position + 1)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return numberTicket
    }

    class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var NumberTicket: TextView
        var countGoodAnswers: TextView
        var textQuest: TextView
        var resultTicket: TextView
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(numberTicket: Int) {
            val database = FirebaseDatabase.getInstance().reference
            val currentUser= FirebaseAuth.getInstance().currentUser!!.uid
            database.child("Users").child(currentUser).child("Stats").child("countCorrectAnswers").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val value = dataSnapshot.child(numberTicket.toString()).value as Long
                    val correctCountAnswers = value.toInt()
                    countGoodAnswers.text = "$correctCountAnswers из 20"
                    textQuest.text = "вопросов решено"
                    if (correctCountAnswers == 20) {
                        resultTicket.text = "Билет пройден"
                        resultTicket.setTextColor(parent!!.resources.getColor(R.color.correctAnswer))

                        NumberTicket.text = "№ $numberTicket"
                        NumberTicket.setTextColor(parent!!.resources.getColor(R.color.correctAnswer))
                    } else if (correctCountAnswers== 0) {
                        resultTicket.text = "Билет не пройден"
                        resultTicket.setTextColor(parent!!.resources.getColor(R.color.white))

                        NumberTicket.text = "№ $numberTicket"
                        NumberTicket.setTextColor(parent!!.resources.getColor(R.color.white))
                    } else {
                        resultTicket.text = "Билет не пройден"
                        resultTicket.setTextColor(parent!!.resources.getColor(R.color.wrongAnswer))

                        NumberTicket.text = "№ $numberTicket"
                        NumberTicket.setTextColor(parent!!.resources.getColor(R.color.wrongAnswer))

                    }



                    /*if (correctCountAnswers == 20) {
                        NumberTicket.setTextColor(parent!!.resources.getColor(R.color.correctAnswer))
                        NumberTicket.text = "№ $numberTicket"
                    } else if (correctCountAnswers== 0) {
                        NumberTicket.setTextColor(parent!!.resources.getColor(R.color.white))
                        NumberTicket.text = "№ $numberTicket"
                    } else {

                    }*/
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })





            /*countGoodAnswers.setText(
                Stats.getResultQuestions(numberTicket - 1).toString() + " из 20"
            )
            textQuest.text = "вопросов решено"
            if (Stats.getResultQuestions(numberTicket - 1) == 20) {
                resultTicket.text = "Билет пройден"
                resultTicket.setTextColor(parent!!.resources.getColor(R.color.correctAnswer))
            } else if (Stats.getResultQuestions(numberTicket - 1) == 0) {
                resultTicket.text = "Билет не пройден"
                resultTicket.setTextColor(parent!!.resources.getColor(R.color.white))
            } else {
                resultTicket.text = "Билет не пройден"
                resultTicket.setTextColor(parent!!.resources.getColor(R.color.wrongAnswer))
            }
            if (Stats.getResultQuestions(numberTicket - 1) == 20) {
                NumberTicket.setTextColor(parent!!.resources.getColor(R.color.correctAnswer))
                NumberTicket.text = "№ $numberTicket"
            } else if (Stats.getResultQuestions(numberTicket - 1) == 0) {
                NumberTicket.setTextColor(parent!!.resources.getColor(R.color.white))
                NumberTicket.text = "№ $numberTicket"
            } else {
                NumberTicket.setTextColor(parent!!.resources.getColor(R.color.wrongAnswer))
                NumberTicket.text = "№ $numberTicket"
            }*/
        }

        init {
            NumberTicket = itemView.findViewById(R.id.numberTicket)
            countGoodAnswers = itemView.findViewById(R.id.numberGoodAnswers)
            textQuest = itemView.findViewById(R.id.textQuest)
            resultTicket = itemView.findViewById(R.id.reslutTicket)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var parent: Context? = null
    }

    init {
        numberTicket = numberOfItems + 1
        Companion.parent = parent
    }
}