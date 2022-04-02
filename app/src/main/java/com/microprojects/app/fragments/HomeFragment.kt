package com.microprojects.app.fragments

import android.annotation.SuppressLint
import android.icu.lang.UCharacter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.microprojects.app.MainActivity
import com.microprojects.app.Navigator
import com.microprojects.app.R
import com.microprojects.app.adapters.HomeAdapter
import com.microprojects.app.classes.LanguageSessionManager
import com.microprojects.app.classes.SessionManager
import com.microprojects.app.databinding.FragmentHomeBinding
import com.microprojects.app.models.HomeModel


open class HomeFragment() : Fragment() {


    companion object {

        private val TAG = HomeFragment::class.java.simpleName

        private lateinit var act: MainActivity

        private lateinit var fragment: HomeFragment


        @JvmStatic
        fun newInstance(act: MainActivity): HomeFragment {

            fragment = HomeFragment()

            HomeFragment.act = act

            return fragment as HomeFragment

        }

    }

    lateinit var mSessionManager: SessionManager

    lateinit var languageSessionManager: LanguageSessionManager

    lateinit var binding: FragmentHomeBinding

    lateinit var mLayaoutManager: LinearLayoutManager

    lateinit var mAdapter: HomeAdapter

    var homeArrayList = ArrayList<HomeModel>()


    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Entered HomeFragment !!!====<>>>>>")

        if (act == null) {

            act = requireActivity() as MainActivity

        }

        try {

            mSessionManager = SessionManager(act!!)

            languageSessionManager = LanguageSessionManager(act!!)

        } catch (e: Exception) {

            Log.e(
                TAG + " onCreateLine>>LineNumber: " +
                        Thread.currentThread().stackTrace[2].lineNumber, e.message.toString()
            )

        }
    }


    @SuppressLint("LongLogTag")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {

            binding = FragmentHomeBinding.inflate(inflater, container, false)

            initViews()

        } catch (e: Exception) {

            Log.e(
                TAG + " onCreateLine>>LineNumber: " +
                        Thread.currentThread().stackTrace[2].lineNumber, e.message.toString()
            )

        }

        return binding.root
    }


    open fun initViews() {

        mLayaoutManager = LinearLayoutManager(act)
        mLayaoutManager.orientation = RecyclerView.VERTICAL
        binding.rvMyRecycler.layoutManager = mLayaoutManager
        binding.rvMyRecycler.itemAnimator = DefaultItemAnimator()

        MainActivity.setTextFonts(binding.root)

    }


    override fun onStart() {
        super.onStart()

        MainActivity.setupDefaultSettings()

        MainActivity.binding.appBarHome.title.text = act?.getString(R.string.HomeLabel)

        getHomeList();
    }


    private fun getHomeList() {

        homeArrayList.clear()

        var hm = HomeModel()
        hm.title = "Date Time"
        homeArrayList.add(hm)

        hm = HomeModel()
        hm.title = "Custom EditText View"
        homeArrayList.add(hm)

        setHomeList()
    }

    private fun setHomeList() {

        mAdapter = HomeAdapter(act, homeArrayList, object: HomeAdapter.OnItemClickListener{

            override fun onItemClick(position: Int) {

                Navigator.loadFragment(act, getFragment(position), R.id.content_home, true, "home")

            }
        })

        binding.rvMyRecycler.adapter = mAdapter

    }


    fun getFragment(position: Int): Fragment {

//        lateinit var fragment: Fragment

        when(position) {

            0 -> return DateTimeFragment.newInstance(act)

            1 -> return CustomEditTextFragment.newInstance(act)
        }

        return HomeFragment.newInstance(act)
    }

}