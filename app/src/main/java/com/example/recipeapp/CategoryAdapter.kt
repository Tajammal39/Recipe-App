package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.CategoryRvBinding


class CategoryAdapter(private val context: Context, private val dataList:List<Recipe>):Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder( var binding:CategoryRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutAdapter = CategoryRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(layoutAdapter)
    }

    override fun getItemCount(): Int {
     return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    Glide.with(context).load(dataList.get(position).img).into(holder.binding.img)
        holder.binding.categorytitle.text = dataList.get(position).tittle

        var temp = dataList.get(position).ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.clockTime.text = temp[0]

        holder.binding.moveIcon.setOnClickListener {
            var  intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("img",dataList.get(position).img)
            intent.putExtra("title",dataList.get(position).tittle)
            intent.putExtra("des",dataList.get(position).des)
            intent.putExtra("ing",dataList.get(position).ing)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}