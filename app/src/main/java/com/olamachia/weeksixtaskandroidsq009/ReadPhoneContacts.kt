package com.olamachia.weeksixtaskandroidsq009

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReadPhoneContacts : AppCompatActivity() {
    private lateinit var contactView: RecyclerView
    private var contactList: MutableList<CellContactModel> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_phone_contacts)


        val readContactsBtn = findViewById<Button>(R.id.read_contacts_button)
        //when we click read contacts button
        readContactsBtn.setOnClickListener {

            requestReadPermission()

        }
    }
    // implementing the read contacts function to read phone contacts
    private fun readContacts() {
        if (contactList.isEmpty()) {
            val contacts = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null
            )
            var count = 0
            while (contacts!!.moveToNext() && count < 50) {
                val name = contacts.getString(
                    contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                )
                val number = contacts.getString(
                    contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
                )
                val obj = CellContactModel(name, number)
                contactList.add(obj)
                count++
            }
            contacts.close()
        }
        //setting view to recycler view and setting layout manager
        contactView = findViewById(R.id.cell_contacts_recycler)
        contactView.adapter = CellContactAdapter(contactList, this)
        contactView.layoutManager = LinearLayoutManager(this)

    }
    // to check if read permission is granted
    private fun readPermission() = ActivityCompat.checkSelfPermission(
        this, Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED
    //to request the read permission if it is not yet granted
    private fun requestReadPermission(){
        var request = mutableListOf<String>()
        if (!readPermission()) {
            request.add(Manifest.permission.READ_CONTACTS)
            ActivityCompat.requestPermissions(this, request.toTypedArray(), 0)
        }else {
            readContacts()
        }

    }




}