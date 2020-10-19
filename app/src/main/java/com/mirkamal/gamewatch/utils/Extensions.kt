package com.mirkamal.gamewatch.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mirkamal.gamewatch.R
import com.mirkamal.gamewatch.model.entity.Game
import com.mirkamal.gamewatch.model.pojo.GamePOJO

/**
 * Created by Mirkamal on 19 October 2020
 */

fun GamePOJO.toGameEntity(): Game {
    return Game(
        this.id ?: 0,
        this.rating?.toInt() ?: 0,
        this.artworks ?: emptyList(),
        this.cover ?: 0,
        this.first_release_date ?: 0,
        this.genres ?: emptyList(),
        this.involved_companies ?: emptyList(),
        this.name ?: "null",
        this.platforms ?: emptyList(),
        this.screenshots ?: emptyList(),
        this.similar_games ?: emptyList(),
        this.summary ?: "null",
        this.url ?: "null",
        this.videos ?: emptyList(),
        "null"
    )
}

@BindingAdapter("imageURL")
fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .placeholder(R.drawable.drawable_loading_placeholder)
        .centerCrop()
        .into(this)
}