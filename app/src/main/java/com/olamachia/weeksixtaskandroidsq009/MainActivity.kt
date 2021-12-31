package com.olamachia.weeksixtaskandroidsq009

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")
        //clicking add button
        findViewById<Button>(R.id.add_contact_button).setOnClickListener {
            sendData()
        }
        //clicking read_real_time_contacts button to read firebase contacts
        findViewById<Button>(R.id.read_realtime_button).setOnClickListener {
            startActivity(Intent(applicationContext, GetData::class.java))
        }
        //clicking read_cell_contacts button to open read phone contacts page
        findViewById<Button>(R.id.read_cell_button).setOnClickListener {
            startActivity(Intent(applicationContext, ReadPhoneContacts::class.java))
        }


    }
    // to add phone contact and name to firebase
    private fun sendData() {
        var name = findViewById<EditText>(R.id.contact_name).text.toString().trim()
        var number = findViewById<EditText>(R.id.contact_number).text.toString().trim()
        var id = reference.push().key
        var model = DatabaseModel( id, name, number)
        reference.child(id!!).setValue(model)
        if (name.isNotEmpty() && number.isNotEmpty()) {
            findViewById<EditText>(R.id.contact_name).setText("")
            findViewById<EditText>(R.id.contact_number).setText("")
            Toast.makeText(applicationContext, "contact added", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(applicationContext, "all fields required", Toast.LENGTH_LONG).show()
        }
    }

}