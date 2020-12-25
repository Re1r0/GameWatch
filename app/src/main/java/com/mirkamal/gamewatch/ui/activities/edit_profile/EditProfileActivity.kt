package com.mirkamal.gamewatch.ui.activities.edit_profile

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.mirkamal.gamewatch.R
import com.mirkamal.gamewatch.model.parcel.ProfileData
import com.mirkamal.gamewatch.utils.*
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var profileData: ProfileData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }

        setOnClickListeners()
        handleExtraData()
    }

    override fun onStart() {
        super.onStart()

        imageViewProfilePicture.loadImage(profileData.profilePictureURL)
    }

    private fun handleExtraData() {
        profileData = intent.getParcelableExtra(EXTRA_PROFILE_DATA_KEY)!!

        textInputEditTextUsername.setText(profileData.username)
        textInputEditTextBio.setText(profileData.bio)

        textInputEditTextSteamDisplayName.setText(profileData.steam[DISPLAY_NAME_KEY] ?: "")
        textInputEditTextSteamURL.setText(profileData.steam[URL_KEY] ?: "")

        textInputEditTextEpicGamesDisplayName.setText(profileData.epicGames[DISPLAY_NAME_KEY] ?: "")
        textInputEditTextEpicGamesURL.setText(profileData.epicGames[URL_KEY] ?: "")

        textInputEditTextUplayDisplayName.setText(profileData.uplay[DISPLAY_NAME_KEY] ?: "")
        textInputEditTextUplayURL.setText(profileData.uplay[URL_KEY] ?: "")

        textInputEditTextDiscordDisplayName.setText(profileData.discord[DISPLAY_NAME_KEY] ?: "")
        textInputEditTextDiscordURL.setText(profileData.discord[URL_KEY] ?: "")
    }

    private fun setOnClickListeners() {
        imageViewGoBack.setOnClickListener {
            finish()
        }
        cardViewSteam.setOnClickListener {
            updateCardVisibilities(STEAM_KEY)
        }
        cardViewEpicGames.setOnClickListener {
            updateCardVisibilities(EPIC_GAMES_KEY)
        }
        cardViewUplay.setOnClickListener {
            updateCardVisibilities(UPLAY_KEY)
        }
        cardViewDiscord.setOnClickListener {
            updateCardVisibilities(DISCORD_KEY)
        }
    }

    private fun updateCardVisibilities(account: String) {
        val layouts = listOf(
            linearLayoutSteamEdit,
            linearLayoutEpicGamesEdit,
            linearLayoutUplayEdit,
            linearLayoutDiscordEdit
        )
        when (account) {
            STEAM_KEY -> {
                layouts[0].isVisible = !layouts[0].isVisible
                for (layout in layouts.subList(1, 3)) {
                    layout.isVisible = false
                }
            }
            EPIC_GAMES_KEY -> {
                layouts[1].isVisible = !layouts[1].isVisible
                layouts[0].isVisible = false
                layouts[2].isVisible = false
                layouts[3].isVisible = false
            }
            UPLAY_KEY -> {
                layouts[2].isVisible = !layouts[2].isVisible
                layouts[0].isVisible = false
                layouts[1].isVisible = false
                layouts[3].isVisible = false
            }
            DISCORD_KEY -> {
                layouts[3].isVisible = !layouts[3].isVisible
                layouts[0].isVisible = false
                layouts[1].isVisible = false
                layouts[2].isVisible = false
            }
        }
    }
}