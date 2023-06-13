package com.example.todo_listapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GroupsActivity : AppCompatActivity() , OnGroupClickListener{
   private var groupsAdapter:GroupsAdapter? = null
    private lateinit var groupsRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.groups)

        groupsRecyclerView = findViewById(R.id.groupsRecyclerView)
        groupsRecyclerView.layoutManager = LinearLayoutManager(this)

        AppData.initialize()
       groupsAdapter = GroupsAdapter(AppData.groups,this)
        groupsRecyclerView.adapter = groupsAdapter
    }

    override fun onResume() {
        super.onResume()
        groupsAdapter!!.notifyDataSetChanged()
    }
    fun createNewGroup(v: View){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Group")
        builder.setMessage("Please enter a name for your new Group")

        val myInput = EditText(this)
        myInput.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(myInput)

        builder.setPositiveButton("save"){
            _ , _->

            val groupName:String = myInput.text.toString()
            val newGroup = Group(groupName, mutableListOf())

            AppData.groups.add(newGroup)
            groupsAdapter!!.notifyItemInserted(AppData.groups.count())

        }

        builder.setNegativeButton("Cancel"){
                _, _ ->
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun groupClicked(index: Int) {
        val intent = Intent(this,ItemsActivity::class.java)
        intent.putExtra("groupIndex",index)
        startActivity(intent)
    }

    override fun groupLongClicked(index: Int) {
       AppData.groups.removeAt(index)
        groupsAdapter!!.notifyItemRemoved(index)
    }
}


