package com.smartkid.dd.activity.ui.home

import com.smartkid.dd.R
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.activity.ui.home.adapter.PopularAdapter
import com.smartkid.dd.activity.ui.home.adapter.RecentViewAdapter
import com.smartkid.dd.activity.ui.home.helper.PopularHelper
import com.smartkid.dd.activity.ui.home.helper.RecentViewHelper
import com.smartkid.dd.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), RecentViewAdapter.ListItemClickListener, PopularAdapter.ListItemClickListener {

    var recentViewRecycler: RecyclerView? = null
    var recentViewAdapter: RecyclerView.Adapter<*>? = null

    var popularViewRecycler: RecyclerView? = null
    var popularViewAdapter: RecyclerView.Adapter<*>? = null

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val recentViewRecyclerCaller: RecyclerView = binding.recentViewsRecycler
        recentViewRecycler = recentViewRecyclerCaller.findViewById(R.id.recentViews_recycler)
        recentViewRecycler()

        val popularViewRecyclerCaller: RecyclerView = binding.popularRecycler
        popularViewRecycler = popularViewRecyclerCaller.findViewById(R.id.popular_recycler)
        popularViewRecycler()

        return root
    }

    private fun recentViewRecycler() {

        //All Gradients
        val gradient2 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x2b341b, -0x2b341b)
        )
        val gradient1 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x852331, -0x852331)
        )
        val gradient3 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x83a61, -0x83a61)
        )
        val gradient4 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x47280b, -0x47280b)
        )
        recentViewRecycler?.setHasFixedSize(true)
        recentViewRecycler?.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        val recentViewlocations: ArrayList<RecentViewHelper> = ArrayList()
        recentViewlocations.add(RecentViewHelper(gradient1, R.drawable.ic_dashboard_black_24dp, "QCM"))
        recentViewlocations.add(RecentViewHelper(gradient4, R.drawable.ic_dashboard_black_24dp, "Animaux"))
        recentViewlocations.add(RecentViewHelper(gradient2, R.drawable.ic_dashboard_black_24dp, "Numbers & Letters"))
        recentViewlocations.add(RecentViewHelper(gradient4, R.drawable.ic_dashboard_black_24dp, "Musics"))
        recentViewlocations.add(RecentViewHelper(gradient2, R.drawable.ic_dashboard_black_24dp, "Colors"))
        recentViewAdapter = RecentViewAdapter(recentViewlocations, this)
        recentViewRecycler?.setAdapter(recentViewAdapter)
    }

    private fun popularViewRecycler() {

        popularViewRecycler?.setHasFixedSize(true)
        popularViewRecycler?.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        val popularCardList: ArrayList<PopularHelper> = ArrayList()
        popularCardList.add(PopularHelper(R.drawable.ic_dashboard_black_24dp, "Guess Instrument",5F))
        popularCardList.add(PopularHelper(R.drawable.ic_dashboard_black_24dp, "Sounds Animals", 4F))
        popularCardList.add(PopularHelper(R.drawable.ic_dashboard_black_24dp, "Answer the response", 3F))
        popularCardList.add(PopularHelper(R.drawable.ic_dashboard_black_24dp, "Calcul", 3F))
        popularViewAdapter = PopularAdapter(popularCardList, this)
        popularViewRecycler?.setAdapter(popularViewAdapter)
    }

    override fun onRecentViewListClick(clickedItemIndex: Int) {}

    override fun onPopularCardListClick(clickedItemIndex: Int) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}