package com.morestudio.craftify

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.morestudio.craftify.adapter.ViewPagerAdapter
import com.morestudio.craftify.data.database.NoteDatabase
import com.morestudio.craftify.databinding.ActivityMainBinding
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.helpers.Helpers.going
import com.morestudio.craftify.view.AddNote

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Tab Adapter
        setTabAdapter()
        //Window Management
        windowManagement()

    }

    override fun onStart() {
        super.onStart()

        //Note Size
        val noteSize = NoteDatabase.getDBInstance(applicationContext)?.noteDAO()?.getNoteCount()
            ?.let { Integer.valueOf(it) }

        binding.tvNoteSize.text = noteSize.toString()


        binding.fab.setOnClickListener {
            going(this@MainActivity, AddNote::class.java)
        }

    }


    //Set Adapter
    private fun setTabAdapter(){
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.notes)
                1 -> tab.text = getString(R.string.pinned)
            }
        }.attach()
    }


    //Window Manager
    private fun windowManagement(){
        val window = window
        val color = if (Helpers.isDarkThemeEnabled(this)) {
            ContextCompat.getColor(this, R.color.md_theme_dark_background)
        } else {
            ContextCompat.getColor(this, R.color.md_theme_light_background)
        }
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = color
        }
    }

}