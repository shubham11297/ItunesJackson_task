package com.example.itunesjackson_task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.itunesjackson_task.R

class SongAdapter(
    private val context: Context,
    private val image: List<String>,
    private val collection: List<String>,
    private val artist: List<String>,
    private val price: List<Double>,
    private val itemClick: (Int) -> Unit
): RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.songlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return artist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.collection.text = collection[position]
        holder.artist.text = "by ${artist[position]}"
        holder.price.text = "$ " + price[position].toString()

        Glide
            .with(context)
            .load(image[position])
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.artwork)


        holder.itemView.setOnClickListener {
            itemClick(position)
        }

    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val artwork = v.findViewById<ImageView>(R.id.artwork)
        val collection = v.findViewById<TextView>(R.id.collectionName)
        val artist = v.findViewById<TextView>(R.id.artistName)
        val price = v.findViewById<TextView>(R.id.songPrice)
    }
}