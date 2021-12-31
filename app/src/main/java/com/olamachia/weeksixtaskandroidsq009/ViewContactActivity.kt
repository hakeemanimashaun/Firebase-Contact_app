package com.olamachia.weeksixtaskandroidsq009

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class ViewContactActivity : AppCompatActivity() {
    lateinit var call: ImageView
    lateinit var update: Button
    lateinit var share: ImageView
    lateinit var delete: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contact)

        call = findViewById(R.id.call_contact)
        update = findViewById(R.id.edit_contact)
         share = findViewById(R.id.share_contact)
         delete = findViewById(R.id.delete_contact)
    }
    /*fun onUpdateClicked(databaseModel: DatabaseModel) {
        var name = findViewById<EditText>(R.id.contact_name).text.toString().trim()
        var number = findViewById<EditText>(R.id.contact_number).text.toString().trim()
        var id = reference.push().key
        var model = DatabaseModel( id, name, number)
        //reference.child(databaseModel.id!!).setValue(databaseModel)
    }*/

}