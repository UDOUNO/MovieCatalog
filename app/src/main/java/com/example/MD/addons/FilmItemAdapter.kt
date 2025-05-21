package com.example.MD.addons

import android.R.attr.src
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.NetworkOnMainThreadException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.View.inflate
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.MD.R
import com.example.MD.databinding.FilmItemBinding
import java.net.HttpURLConnection
import java.net.URL

class FilmItemAdapter(private var filmItemList: ArrayList<FilmItem>) :
    RecyclerView.Adapter<FilmItemAdapter.MyHolder>() {
    class MyHolder(
        itemView: View,
        private var el: FilmItemBinding,
    ) : RecyclerView.ViewHolder(itemView) {

        fun drawItem(item: FilmItem) {
            el.rating.text = item.rating.toString()
            el.rating.background.setTint(ColorUtils.blendARGB(Color.RED, Color.GREEN,item.rating/10.0f))
            if (item.liked) {
                el.inFav.visibility = VISIBLE
            } else {
                el.inFav.visibility = GONE
            }
            val imageUri:String? = item.uri
            Glide.with(el.card.context).load(Uri.parse(imageUri)).into(el.card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        val el = FilmItemBinding.bind(itemView)
        return MyHolder(itemView, el)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.drawItem(filmItemList[position])
    }

    override fun getItemCount(): Int {
        return filmItemList.size
    }
}