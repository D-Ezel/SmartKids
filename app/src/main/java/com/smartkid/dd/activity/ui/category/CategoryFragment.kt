package com.smartkid.dd.activity.ui.category

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
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
        categoryHelperList.add(CategoryHelper("6284efdfdaac815be2cbe1e0", R.drawable.quiz_logo_modified, "Pays"))
        categoryHelperList.add(CategoryHelper("6284eededaac815be2cbe1dd",R.drawable.music_instrument_categ, "Music Instruments"))
        categoryHelperList.add(CategoryHelper("6284efbddaac815be2cbe1de",R.drawable.animal_categ, "Animaux"))
        categoryHelperList.add(CategoryHelper("6284efcfdaac815be2cbe1df",R.drawable.abc, "Letters and Numbers"))
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

    override fun onCategoryListClick(id: String?) {
        val intent = Intent(this.context, ItemsCategory::class.java).apply {
            putExtra(IDENTIFICATION, id)
        }
        startActivity(intent)
        showNotification(id)
    }

    private fun showNotification(idCategory:String?) {
        val channelID ="1000"
        val notificationManager = this.activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val contentView = RemoteViews(this.activity?.packageName, R.layout.custom_notification_view)
        if(idCategory.equals("6284efdfdaac815be2cbe1e0")) {
            contentView.setTextViewText(R.id.title_notif, "Catégorie Pays")
            contentView.setTextViewText(R.id.desc_notif, "Il y a du Quiz concernant les Pays et des vidéos pour bien apprendre les capitales des pays")
        }
        if(idCategory.equals("6284eededaac815be2cbe1dd")) {
            contentView.setTextViewText(R.id.title_notif, "Catégorie Instrument de Musique")
            contentView.setTextViewText(R.id.desc_notif, "Apprendre les types d'instruments de musiques dans la section vidéo et un jeu dans la section educational games")
        }

        if(idCategory.equals("6284efbddaac815be2cbe1de")) {
            contentView.setTextViewText(R.id.title_notif, "Catégorie Animaux")
            contentView.setTextViewText(R.id.desc_notif, "Jouez au jeu 'identifier l'animale' par son bruit. Des diverses vidéo aussi pour bien approfondir la connaissance")
        }

        if(idCategory.equals("6284efcfdaac815be2cbe1df")) {
            contentView.setTextViewText(R.id.title_notif, "Catégorie Lettre & Nombre")
            contentView.setTextViewText(R.id.desc_notif, "traduisez les nombres en lettre ou vice versa. Vidéo interessant concernant les lettres et nombres")
        }
        val builder =
            this.activity?.let {
                NotificationCompat.Builder(it.applicationContext, channelID)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setCustomContentView(contentView)
            }
        builder?.setContent(contentView)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, "Custom Notification", NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
            if (builder != null) {
                builder.setChannelId(channelID)
            }
        }
        val notification = builder?.build()
        notificationManager.notify(1000, notification)
    }
}