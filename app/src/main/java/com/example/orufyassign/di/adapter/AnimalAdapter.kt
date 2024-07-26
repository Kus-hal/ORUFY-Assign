package com.example.orufyassign.di.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orufyassign.R
import com.example.orufyassign.di.model.AnimalResponse

class AnimalAdapter(private val animalList: MutableList<AnimalResponse>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animalImageView: ImageView = itemView.findViewById(R.id.animalImageView)
        val animalNameTextView: TextView = itemView.findViewById(R.id.animalNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalList[position]
        holder.animalNameTextView.text = animal.tags

        Glide.with(holder.itemView.context)
            .load(animal.webformatURL)
            .into(holder.animalImageView)
    }

    override fun getItemCount(): Int = animalList.size

    fun addData(newData: List<AnimalResponse>) {
        val startPosition = animalList.size
        animalList.addAll(newData)
        notifyItemRangeInserted(startPosition, newData.size)
    }
}