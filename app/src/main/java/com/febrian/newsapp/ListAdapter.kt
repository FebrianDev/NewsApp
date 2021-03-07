package com.febrian.newsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.febrian.newsapp.databinding.ListNewsBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var list : ArrayList<DataItem>
    private lateinit var binding : ListNewsBinding

    fun setList(list : ArrayList<DataItem>){
        this.list = list
    }

    inner class ViewHolder(binding: ListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        binding.title.text = data.title
        Glide.with(holder.itemView.context)
            .load(data.img)
            .apply(RequestOptions().override(120,120))
            .into(binding.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("url", data.url)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}