package com.smartkid.dd.activity.ui.category

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.ItemsCategory
import com.smartkid.dd.activity.ui.category.adapter.CategoryAdapter
import com.smartkid.dd.activity.ui.category.helper.CategoryHelper
import com.smartkid.dd.databinding.FragmentCategoryBinding
import com.smartkid.dd.tools.GridSpacingItemDecoration

const val IDENTIFICATION = "id"

class CategoryFragment : Fragment(), CategoryAdapter.ListItemClickListener {

    var categoryRecycler: RecyclerView? = null
    var categoryAdapter: RecyclerView.Adapter<*>? = null

    private var _binding: FragmentCategoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
                ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCategory
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val categoryRecyclerCaller: RecyclerView = binding.categoryRecycler
        categoryRecycler = categoryRecyclerCaller.findViewById(R.id.category_recycler)
        categoryRecycler()
        return root
    }

    private fun categoryRecycler() {
        var mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        categoryRecycler?.setHasFixedSize(true)
        categoryRecycler?.setLayoutManager(
            mLayoutManager
        )
        categoryRecycler?.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                dpToPx(10).toInt(), true
            )
        )
        categoryRecycler?.setItemAnimator(DefaultItemAnimator())

        val categoryHelperList: ArrayList<CategoryHelper> = ArrayList()
        categoryHelperList.add(CategoryHelper(R.drawable.quiz_logo_modified, "QCM"))
        categoryHelperList.add(CategoryHelper(R.drawable.music_instrument_categ, "Music Instruments"))
        categoryHelperList.add(CategoryHelper(R.drawable.animal_categ, "Animaux"))
        categoryHelperList.add(CategoryHelper(R.drawable.abc, "Letters and Numbers"))
        categoryAdapter = CategoryAdapter(categoryHelperList, this)
        categoryRecycler?.setAdapter(categoryAdapter)
    }

    private fun dpToPx(dp: Int): Int {
        val r: Resources = Resources.getSystem()
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.getDisplayMetrics()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryListClick(title: String?) {
        val intent = Intent(this.context, ItemsCategory::class.java).apply {
            putExtra(IDENTIFICATION, title)
        }
        startActivity(intent)
    }
}