package com.mirkamal.gamewatch.ui.fragments.mygames.recyclerviews

import androidx.recyclerview.widget.RecyclerView
import com.mirkamal.gamewatch.databinding.ItemMyGamesBinding
import com.mirkamal.gamewatch.model.entity.Game

/**
 * Created by Mirkamal on 24 October 2020
 */
class MyGamesListViewHolder private constructor(private val binding: ItemMyGamesBinding, private val listener: (game: Game) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game) {
        binding.apply {
            this.game = game
            executePendingBindings()
        }
        itemView.setOnClickListener {
            listener(game)
        }
    }

    companion object {
        fun from(binding: ItemMyGamesBinding, listener: (game: Game) -> Unit) : MyGamesListViewHolder {
            return MyGamesListViewHolder(binding, listener)
        }
    }
}