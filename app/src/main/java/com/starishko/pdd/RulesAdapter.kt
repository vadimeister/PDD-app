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

class RulesAdapter internal constructor(private val numberRules: Int, parent: Context?) :
    RecyclerView.Adapter<RulesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        @SuppressLint("ResourceType") val view =
            inflater.inflate(R.layout.number_list_rule, parent, false)
        return RulesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, Rule::class.java)
            intent.putExtra("numberTheme", position + 1)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return numberRules
    }

    class RulesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var NumberThemeRule: TextView
        fun bind(numberTheme: Int) {
            val database = FirebaseDatabase.getInstance().reference
            val myRefNumberTheme = database.child("Rules").child((numberTheme + 1).toString())
            myRefNumberTheme.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.child("Theme").getValue(
                        String::class.java
                    )
                    NumberThemeRule.text = value
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }

        init {
            NumberThemeRule = itemView.findViewById(R.id.number_list_rule)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var parent: Context? = null
    }

    init {
        Companion.parent = parent
    }
}