package com.example.MD.addons

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.MD.Domain.MovieElementModel
import com.example.MD.R
import com.example.MD.View.MovieFragment
import com.example.MD.databinding.CycleItemBinding
import com.google.android.material.chip.Chip

class ViewPagerAdapter(
    private val cycleFilms: ArrayList<MovieElementModel>,
    private val fragment: MovieFragment
) :
    RecyclerView.Adapter<PagerVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.cycle_item, parent, false))

    override fun getItemCount(): Int = cycleFilms.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.bind(cycleFilms[position], fragment)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding = CycleItemBinding.bind(itemView)
    fun bind(movie: MovieElementModel, fragment: MovieFragment) {
        Glide.with(binding.cycleFilm.context)
            .load(Uri.parse(movie.poster)).into(binding.cycleFilm)
        binding.name.text = movie.name.toString()
        if(binding.flow.referencedIds.isEmpty()){
            for (i in 0..<movie.genres!!.size) {
                val view = fragment.layoutInflater.inflate(
                    R.layout.chip_item,
                    null
                ) as Chip
                view.id = View.generateViewId()
                view.text = movie.genres[i].name.toString()
                println(movie.genres[i].name)

                binding.container.addView(view)
                binding.flow.referencedIds = binding.flow.referencedIds.plus(view.id)
            }
        }
    }
}