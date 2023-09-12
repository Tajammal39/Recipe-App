package com.example.recipeapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.PopularRvItemBinding

class RecyclerAdapter(val dataList: List<Recipe>, val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.viewHolder>() {
    class viewHolder(var binding: PopularRvItemBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var adaptingLayout =
            PopularRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(adaptingLayout)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.popularImg
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.popularImg)
        holder.binding.popularTxt.text = dataList.get(position).tittle

        var time = dataList.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        holder.binding.popularTime.text = time.get(0)
        holder.binding.popularImg.setOnClickListener {
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