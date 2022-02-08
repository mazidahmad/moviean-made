package com.mvzd.moviean.favourite.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvzd.moviean.core.databinding.ItemCardBinding
import com.mvzd.moviean.core.domain.model.Tv
import com.squareup.picasso.Picasso

class TvListFavouriteAdapter(private val listTv: List<Tv>) :
    RecyclerView.Adapter<TvListFavouriteAdapter.TvViewHolder>() {
    private lateinit var binding: ItemCardBinding
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class TvViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: Tv) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${tv.posterPath}")
                .resize(200, 300)
                .centerCrop()
                .into(binding.imageMovie)
            binding.titleItem.text = tv.name
            binding.descriptionMovie.text = tv.overview
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(tv)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = listTv.size

    interface OnItemClickCallback {
        fun onItemClicked(tv: Tv)
    }
}