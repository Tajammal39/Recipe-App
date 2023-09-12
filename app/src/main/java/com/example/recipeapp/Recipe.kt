package com.example.recipeapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    var img: String,
    var tittle: String,
    var des: String,
    var category: String,
    var ing:String,
    @PrimaryKey(autoGenerate = true)
    var uid:Int = 0
)