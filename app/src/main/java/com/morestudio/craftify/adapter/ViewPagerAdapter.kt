package com.morestudio.craftify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.morestudio.craftify.ui.NoteFragment
import com.morestudio.craftify.ui.PinnedFragment

private const val NUM_TABS = 2

public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NoteFragment()
            1 -> return PinnedFragment()
        }
        return NoteFragment()
    }
}