package com.smartkid.dd.activity.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.home.adapter.PopularAdapter
import com.smartkid.dd.activity.ui.home.adapter.RecentViewAdapter
import com.smartkid.dd.activity.ui.home.helper.PopularHelper
import com.smartkid.dd.activity.ui.notifications.adapter.NotificationsAdapter
import com.smartkid.dd.activity.ui.notifications.helper.NotificationsHelper
import com.smartkid.dd.databinding.FragmentNotificationsBinding
import java.sql.Time

class NotificationsFragment : Fragment(), NotificationsAdapter.ListItemClickListener {

    var notificationsRecycler: RecyclerView? = null
    var notificationsAdapter: RecyclerView.Adapter<*>? = null

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val notificationsViewRecyclerCaller: RecyclerView = binding.notificationsRecycler
        notificationsRecycler = notificationsViewRecyclerCaller.findViewById(R.id.notifications_recycler)
        notificationViewRecycler()

        return root
    }

    private fun notificationViewRecycler() {

        notificationsRecycler?.setHasFixedSize(true)
        notificationsRecycler?.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        val notificationCardList: ArrayList<NotificationsHelper> = ArrayList()
        notificationCardList.add(NotificationsHelper(R.drawable.ic_dashboard_black_24dp, "Guess Instrument","Il y a 1 min", Time(1224548)))
        notificationCardList.add(NotificationsHelper(R.drawable.ic_dashboard_black_24dp, "Sounds Animals", "Il y a 3 jours", Time(1224548)))
        notificationCardList.add(NotificationsHelper(R.drawable.ic_dashboard_black_24dp, "Answer the response", "Il y a 5 min", Time(1224548)))
        notificationCardList.add(NotificationsHelper(R.drawable.ic_dashboard_black_24dp, "New Game", "Il y a 5 min", Time(1224548)))
        notificationsAdapter = NotificationsAdapter(notificationCardList, this)
        notificationsRecycler?.setAdapter(notificationsAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onNotificationsListClick(clickedItemIndex: Int) {}
}