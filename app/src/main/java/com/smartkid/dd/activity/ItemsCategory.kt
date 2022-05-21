package com.smartkid.dd.activity

import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.smartkid.dd.activity.ui.category.IDENTIFICATION
import com.smartkid.dd.activity.ui.main.SectionsPagerAdapter
import com.smartkid.dd.databinding.ActivityItemsCategoryBinding
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.main.tabs.EducationGamesFragment
import com.smartkid.dd.activity.ui.main.tabs.VideoFragment
import kotlin.coroutines.EmptyCoroutineContext

class ItemsCategory : AppCompatActivity() {

    private lateinit var binding: ActivityItemsCategoryBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabs
        viewPager = binding.viewPager

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        sectionsPagerAdapter.addFragment(EducationGamesFragment(), "Educational Games")
        sectionsPagerAdapter.addFragment(VideoFragment(EmptyCoroutineContext), "Video")
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        val id = intent.getStringExtra(IDENTIFICATION)
        val title = findViewById<TextView>(R.id.title).apply {
            text = "SmartKids"
        }
    }
}