package com.morestudio.craftify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.morestudio.craftify.adapter.ViewPagerAdapter
import com.morestudio.craftify.data.NoteDatabase
import com.morestudio.craftify.databinding.ActivityMainBinding
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.model.Note

val tabArray = arrayOf(
    "Notlarım",
    "Pinlediklerim"
)

class MainActivity : AppCompatActivity() {
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
            tab.text = tabArray[position]
        }.attach()

        var noteSize = NoteDatabase.getDBInstance(applicationContext)?.noteDAO()?.getNoteCount()
            ?.let { Integer.valueOf(it )}

                binding.notesSize.text = noteSize.toString()



    }
}