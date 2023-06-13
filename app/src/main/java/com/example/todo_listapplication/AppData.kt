package com.example.todo_listapplication

class AppData{
    companion object DataHolder{

        var groups: MutableList<Group> = mutableListOf()

        fun initialize(){
            val item1 = Item("Bread",false)
            val item2 = Item("Milk",true)
            val item3 = Item("Water",false)
            val item4 = Item("Clothes",false)

            val group1 = Group("Home", mutableListOf(item1,item2))
            val group2 = Group("College", mutableListOf(item3,item4))

            groups = mutableListOf(group1,group2)
        }

    }


}