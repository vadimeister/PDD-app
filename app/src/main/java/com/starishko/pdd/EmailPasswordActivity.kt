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

import android.widget.EditText
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class EmailPasswordActivity : AppCompatActivity(), View.OnClickListener{
    private var mAuth: FirebaseAuth? = null
    private var mAuthListener: AuthStateListener? = null
    private var ETemail: EditText? = null
    private var ETpassword: EditText? = null
    var check = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password)
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
        }
        val arguments = intent.extras
        if(arguments!=null) 
            check = arguments.getInt("Check");
        ETemail = findViewById<View>(R.id.et_email) as EditText
        ETpassword = findViewById<View>(R.id.et_password) as EditText
        findViewById<View>(R.id.btn_sign_in).setOnClickListener(this)
        findViewById<View>(R.id.btn_registration).setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser!= null) {
            try {

                FirebaseDatabase.getInstance().setPersistenceEnabled(true)
                val intent = Intent(this@EmailPasswordActivity, MainMenu::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
            }
        } else {
            if (check == 0)
                FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        }
    }


    override fun onClick(view: View) {
        if (view.id == R.id.btn_sign_in) {
            signIn(ETemail!!.text.toString(), ETpassword!!.text.toString())
        } else if (view.id == R.id.btn_registration) {
            registration(ETemail!!.text.toString(), ETpassword!!.text.toString())
        }
    }

    fun signIn(email: String?, password: String?) {
        mAuth!!.signInWithEmailAndPassword(email!!, password!!).addOnCompleteListener(
            this
        ) { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@EmailPasswordActivity,
                    "Aвторизация успешна",
                    Toast.LENGTH_SHORT
                ).show()
                try {
                    val intent = Intent(this@EmailPasswordActivity, MainMenu::class.java)
                    startActivity(intent)
                    finish()
                } catch (ignored: Exception) { }
            } else Toast.makeText(
                this@EmailPasswordActivity,
                "Aвторизация провалена",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun registration(email: String?, password: String?) {
        mAuth!!.createUserWithEmailAndPassword(email!!, password!!).addOnCompleteListener(
            this
        ) { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@EmailPasswordActivity,
                    "Регистрация успешна",
                    Toast.LENGTH_SHORT
                ).show()
                val database = FirebaseDatabase.getInstance().reference
                val currentUser= FirebaseAuth.getInstance().currentUser!!.uid
                var numberTicket: String
                database.child("Users").child(currentUser).child("Email").setValue(email)
                for(i in 1..40){
                    numberTicket = i.toString()
                    database.child("Users").child(currentUser).child("Stats").child("countCorrectAnswers").child(numberTicket).setValue(0)
                }

            } else Toast.makeText(
                this@EmailPasswordActivity,
                "Регистрация провалена",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}