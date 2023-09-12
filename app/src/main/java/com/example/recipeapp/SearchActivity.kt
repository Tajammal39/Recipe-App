package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapp.databinding.ActivityHomeBinding
import com.example.recipeapp.databinding.ActivitySearchBinding
import com.example.recipeapp.databinding.SearchRvBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var dataList:ArrayList<Recipe>
    private lateinit var searchAdapter:SearchAdapter
    private lateinit var recipes:List<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db =
            Room.databaseBuilder(this, AppDataBase::class.java, "db_name").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset("recipe.db")
                .build()

        var daoObject = db.getDao()
        recipes = daoObject.getAllRecipe()

        setUpRecyclerView()
        binding.SearchView2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.toString() != " ") {
                    filterData(s.toString())

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.goBack.setOnClickListener {
            finish()
        }
    }

    private fun filterData(filterText:String) {

        val filterData = ArrayList<Recipe>()
        for(i in recipes.indices ){
            if(recipes[i].tittle.lowercase().contains(filterText.lowercase())){
                filterData.add(recipes[i])
            }
        }
        searchAdapter.filterList(filterData)
    }


    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        for(i in recipes.indices){
            if(recipes[i].category.contains("Popular")){
                dataList.add(recipes[i])
            }
            searchAdapter = SearchAdapter(dataList,this)

            binding.rvSearch.adapter = searchAdapter
        }
    }
}