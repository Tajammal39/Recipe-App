package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.sax.StartElementListener
import androidx.core.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapp.databinding.ActivityHomeBinding
import com.example.recipeapp.databinding.PopularRvItemBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: RecyclerAdapter
    private lateinit var dataList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUpRecyclerView()
        binding.SearchView.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

        binding.saladImage.setOnClickListener {
            val intent = Intent(this,CategoryActivity::class.java).putExtra("TITTLE","Salad").putExtra("CATEGORY","Salad")
            startActivity(intent)
        }

        binding.mainDish.setOnClickListener {
            val intent = Intent(this,CategoryActivity::class.java).putExtra("TITTLE","Main Dish").putExtra("CATEGORY","Dish")
            startActivity(intent)
        }

        binding.dessert.setOnClickListener {

            val intent = Intent(this,CategoryActivity::class.java).putExtra("TITTLE","Dessert").putExtra("CATEGORY","Desserts")
            startActivity(intent)
        }

        binding.drinks.setOnClickListener {

            val intent = Intent(this,CategoryActivity::class.java).putExtra("TITTLE","Drinks").putExtra("CATEGORY", "Drinks")
            startActivity(intent)
        }

    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var db =
            Room.databaseBuilder(this, AppDataBase::class.java, "db_name").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset("recipe.db")
                .build()

        var daoObject = db.getDao()
        var recipes = daoObject.getAllRecipe()

        for(i in recipes.indices){
            if(recipes[i].category.contains("Popular")){
                dataList.add(recipes[i])
            }
            rvAdapter = RecyclerAdapter(dataList,this)

            binding.rvPopular.adapter = rvAdapter
        }
    }
}