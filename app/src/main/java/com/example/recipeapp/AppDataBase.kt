package com.example.recipeapp

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao(): Dao

//    var INSTANCE: AppDataBase? = null
//    fun getData(contex: Context): AppDataBase {
//
//        return INSTANCE?: Synchronized(this) {
//            val instance = Room.databaseBuilder(
//                contex,
//                AppDataBase::class.java, "RecipeData").createFromAsset("recipe.db")
//                .build()
//            INSTANCE = instance
//
//            instance
//        }
//
//    }

}