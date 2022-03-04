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
import android.app.Dialog
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
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class DialogClearSettings : AppCompatDialogFragment() {
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = "Сброс статистики"
        val message = "Вы уверены, что хотите сбросить всю информацию о пройденных билетах?"
        val button1String = "Да"
        val button2String = "Нет"
        val builder = Objects.requireNonNull(activity)?.let {
            AlertDialog.Builder(
                it, R.style.AlertDialog
            )
        }
        builder!!.setTitle(title) // заголовок
        builder!!.setMessage(message) // сообщение
        builder!!.setPositiveButton(button1String) { dialog, id ->
            val database = FirebaseDatabase.getInstance().reference
            val currentUser= FirebaseAuth.getInstance().currentUser!!.uid
            var numberTicket: String
            for(i in 1..40){
                numberTicket = i.toString()
                database.child("Users").child(currentUser).child("Stats").child("countCorrectAnswers").child(numberTicket).setValue(0)
                // database.child("Users").child(currentUser).child("Stats").child("passedTickets").child(numberTicket).setValue(0)
            }
            //MainMenu.Companion.pref!!.edit().clear().apply()
        }
        builder!!.setNegativeButton(button2String) { dialog, id -> dialog.cancel() }
        builder!!.setCancelable(true)
        builder!!.setOnCancelListener { }
        return builder!!.create()
    }
}