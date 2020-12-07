package com.example.semester_project

class FavoriteList {
    companion object{
        var myFavoriteList: MutableMap<String, Tour>? = null

        fun add(name: String, tour: Tour){
            myFavoriteList?.put(name, tour)
        }

        fun remove(name:String){
            myFavoriteList?.remove(name)
        }

        fun size():Int{
            return myFavoriteList?.count()!!
        }

        fun clear(){
            myFavoriteList?.clear()
        }
    }
}