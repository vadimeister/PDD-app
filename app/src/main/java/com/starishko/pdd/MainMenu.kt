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
import com.starishko.pdd.MainMenu
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
import java.lang.Exception

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //database
        //pref = getSharedPreferences("Data", MODE_PRIVATE)
        val startButton = findViewById<View>(R.id.startButton) as Button
        startButton.setOnClickListener {
            try {
                val intent = Intent(this@MainMenu, TicketsList::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
            }
        }
        val examButton = findViewById<View>(R.id.examButton) as Button
        examButton.setOnClickListener {
            try {
                val intent = Intent(this@MainMenu, Exam::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
            }
        }
        val rulesButton = findViewById<View>(R.id.ruleButton) as Button
        rulesButton.setOnClickListener {
            try {
                val intent = Intent(this@MainMenu, RulesList::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
            }
        }
        val statButton = findViewById<View>(R.id.statButton) as Button
        statButton.setOnClickListener {
            try {
                val intent = Intent(this@MainMenu, Stats::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
            }
        }
        val optionsButton = findViewById<View>(R.id.optionsButton) as Button
        optionsButton.setOnClickListener {
            try {
                val intent = Intent(this@MainMenu, Settings::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    /*ompanion object {
        private var mDatabase: FirebaseDatabase? = null
        var pref: SharedPreferences? = null

        /*private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                });
    }*/
        val database: Unit
            get() {
                if (mDatabase == null) {
                    mDatabase = FirebaseDatabase.getInstance()
                    mDatabase!!.setPersistenceEnabled(true)
                }
            }
    }*/
}