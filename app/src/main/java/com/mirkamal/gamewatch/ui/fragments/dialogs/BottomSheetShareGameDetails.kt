package com.mirkamal.gamewatch.ui.fragments.dialogs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mirkamal.gamewatch.R
import com.mirkamal.gamewatch.model.parcel.Game
import com.mirkamal.gamewatch.utils.libs.MessageGenerator
import kotlinx.android.synthetic.main.dialog_share_game_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Mirkamal on 23 November 2020
 */
class BottomSheetShareGameDetails : BottomSheetDialogFragment() {

    private lateinit var game: Game

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_share_game_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardViewContainerText.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Check this game out!")
            intent.putExtra(Intent.EXTRA_TEXT, MessageGenerator.generateShareGameText(game))
            startActivity(Intent.createChooser(intent, "Share using"))
            dismiss()
        }

        cardViewContainerImage.setOnClickListener {
            context?.let { c ->

                progressBar.isVisible = true
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    val bitmap = Glide.with(c).asBitmap()
                        .load(game.coverURL.replace("t_thumb", "t_screenshot_huge"))
                        .submit().get()
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "image/*"
                    val path = MessageGenerator.getImageFile(requireActivity().applicationContext.contentResolver, bitmap)
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path))
                    startActivity(Intent.createChooser(intent, "Share using"))
                    dismiss()
                }
            }
        }
    }

    fun injectGame(game: Game) {
        this.game = game
    }
}