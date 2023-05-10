package com.morestudio.craftify

import android.os.Bundle
import android.view.WindowManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.morestudio.craftify.adapter.ViewPagerAdapter
import com.morestudio.craftify.data.database.NoteDatabase
import com.morestudio.craftify.databinding.ActivityMainBinding
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.helpers.Helpers.going
import com.morestudio.craftify.view.AddNote

class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val window = this.window
        if (Helpers.isDarkThemeEnabled(this)) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.md_theme_dark_background)
        } else {
            window.statusBarColor = this.resources.getColor(R.color.md_theme_light_background)
        }


    }

    override fun onStart() {
        super.onStart()
        val noteSize = NoteDatabase.getDBInstance(applicationContext)?.noteDAO()?.getNoteCount()
            ?.let { Integer.valueOf(it) }

        if (noteSize == 0) {
            binding.notesSize.text = noteSize.toString()
        } else {
            binding.notesSize.text = noteSize?.plus(1).toString()
        }


        binding.fab.setOnClickListener {
            going(this@MainActivity, AddNote::class.java)
        }

    }

}