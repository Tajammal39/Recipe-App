package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapp.databinding.ActivityCategoryMainBinding

class CategoryActivity : AppCompatActivity() {
    /* lateinit var  dataList:ArrayList<Recipe>*/
    private lateinit var binding: ActivityCategoryMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.title.text = intent.getStringExtra("TITTLE")

        //current activity destroyed
        binding.backIcon.setOnClickListener {
            finish()
        }
        

        var dataList = ArrayList<Recipe>()

        binding.rvCategory.layoutManager =
            LinearLayoutManager(this)

        var db = Room.databaseBuilder(this, AppDataBase::class.java, "db_name")
            .createFromAsset("recipe.db").allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()

        var daoObject = db.getDao()
        var recipe = daoObject.getAllRecipe()

        var rvAdapter:CategoryAdapter
      for(i in recipe.indices){
          if(recipe[i].category.contains(intent.getStringExtra("CATEGORY")!!)){
              dataList.add(recipe[i])
          }

      }
        rvAdapter  = CategoryAdapter(this,dataList)
        binding.rvCategory.adapter = rvAdapter
    }
}