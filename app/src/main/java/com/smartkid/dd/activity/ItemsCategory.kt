package com.smartkid.dd.activity

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity

import android.widget.TextView
import com.smartkid.dd.activity.ui.category.IDENTIFICATION
import com.smartkid.dd.activity.ui.main.SectionsPagerAdapter
import com.smartkid.dd.databinding.ActivityItemsCategoryBinding
import com.smartkid.dd.R

class ItemsCategory : AppCompatActivity() {

    private lateinit var binding: ActivityItemsCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab

        val id = intent.getStringExtra(IDENTIFICATION)
        val title = findViewById<TextView>(R.id.title).apply {
            text = id
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}