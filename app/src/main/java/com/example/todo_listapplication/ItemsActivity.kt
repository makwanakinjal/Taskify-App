package com.example.todo_listapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity(),OnItemClickListener {

    lateinit var thisgroup: Group
    var itemsAdapter : ItemsAdapter? = null

    override fun itemClicked(index: Int) {
        thisgroup.items[index].completed = !thisgroup.items[index].completed
        itemsAdapter!!.notifyDataSetChanged()
    }
    override fun itemLongClicked(index: Int) {

       thisgroup.items.removeAt(index)
       itemsAdapter!!.notifyItemRemoved(index)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var toolbarTitle: TextView
         lateinit var itemsRecyclerView: RecyclerView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        var selectIndex:Int = intent.getIntExtra("groupIndex",0)
         thisgroup = AppData.groups[selectIndex]

       toolbarTitle = findViewById(R.id.toolbarTitle)
        val myToolbar = findViewById<Toolbar>(R.id.myToolbar)

        toolbarTitle.text= thisgroup.name

       setSupportActionBar(myToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)



        itemsRecyclerView = findViewById(R.id.itemRecyclerView)
      itemsRecyclerView.layoutManager = LinearLayoutManager(this)

         itemsAdapter = ItemsAdapter(thisgroup,this)
        itemsRecyclerView.adapter = itemsAdapter

    val newItemEditText = findViewById<EditText>(R.id.newItemEditText)
    newItemEditText.setOnKeyListener{ view, keyCode, event ->

        if(keyCode == KeyEvent.KEYCODE_ENTER){

            if(event.action == KeyEvent.ACTION_DOWN){
                val name: String = newItemEditText.text.toString()
                val item = Item(name,false)
                thisgroup.items.add(item)
                itemsAdapter!!.notifyItemInserted(thisgroup.items.count())
                newItemEditText.text.clear()

                // It is used for keyboard hide
                val inputManager = getSystemService(Activity.INPUT_METHOD_SERVICE)
                        as InputMethodManager

                inputManager.hideSoftInputFromWindow(view.windowToken,0)

            }
        }

        false

    }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}