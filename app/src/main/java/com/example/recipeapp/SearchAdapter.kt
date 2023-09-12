package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.SearchRvBinding


class SearchAdapter(var dataList: List<Recipe>, val context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(var binding: SearchRvBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutAdpater = SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
       return ViewHolder(layoutAdpater)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

     Glide.with(context).load(dataList.get(position).img).into(holder.binding.sampleImage)
        holder.binding.imageText.text = dataList.get(position).tittle

        holder.itemView.setOnClickListener{
            var  intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("img",dataList.get(position).img)
            intent.putExtra("title",dataList.get(position).tittle)
            intent.putExtra("des",dataList.get(position).des)
            intent.putExtra("ing",dataList.get(position).ing)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }


    fun filterList(filterList:ArrayList<Recipe>){
        dataList = filterList
        notifyDataSetChanged()
    }
}
