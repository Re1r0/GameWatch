package com.mirkamal.gamewatch.ui.fragments.intro

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Mirkamal on 16 October 2020
 */
class IntroPagerAdapter(fragment: Fragment, private val fragments: List<Fragment>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}