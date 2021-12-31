package com.olamachia.weeksixtaskandroidsq009

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class GetData : AppCompatActivity(),ItemClickListener {
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private var list= ArrayList<DatabaseModel>()
    lateinit var recyclerView: RecyclerView
    lateinit  var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        getData()
    }
    //to display firebase contacts
    private fun getData(){
        reference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for(data in p0.children){
                    val model = data.getValue(DatabaseModel::class.java)
                    list.add(model as DatabaseModel)
                }
                if(list.size > 0){
                    adapter = DataAdapter(list, this@GetData)
                    recyclerView.adapter = adapter
                }


            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("cancel", p0.toString())
            }

        })
    }

    override fun onViewClicked(position: Int, item: DatabaseModel) {

        Toast.makeText(this, "${item.name}", Toast.LENGTH_SHORT).show()
        val viewContact= Intent(this, ViewContactActivity::class.java).apply {
            putExtra("name",list[position].name)
            putExtra("contact",list[position].number)
            putExtra("id",list[position].id)
        }

        startActivity(viewContact)
    }


}