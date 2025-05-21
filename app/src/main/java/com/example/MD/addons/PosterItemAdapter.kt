package com.example.MD.addons

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.MD.R
import com.example.MD.databinding.PosterItemBinding

class PosterItemAdapter(private var posterList: ArrayList<PosterItem>) :
    RecyclerView.Adapter<PosterItemAdapter.MyHolder>() {
    class MyHolder(
        itemView: View,
        private var el: PosterItemBinding,
    ) : RecyclerView.ViewHolder(itemView) {

        fun drawItem(item: PosterItem) {
            val imageUri: String? = item.uri
            Glide.with(el.card.context).load(Uri.parse(imageUri)).into(el.card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.poster_item, parent, false)
        val el = PosterItemBinding.bind(itemView)
        return MyHolder(itemView, el)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.drawItem(posterList[position])
    }

    override fun getItemCount(): Int {
        return posterList.size
    }
}