package com.microprojects.app

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.alduhaim_kotlin.app.MicroProjectsConstant
import com.alduhaim_kotlin.app.classes.isEnglish
import com.microprojects.app.classes.LanguageSessionManager
import com.microprojects.app.classes.LocaleHelper
import com.microprojects.app.classes.SessionManager
import com.microprojects.app.databinding.ActivityMainBinding
import com.microprojects.app.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    companion object {

        lateinit var context: Context

        lateinit var mLangSessionManager: LanguageSessionManager

        lateinit var mSessionManager: SessionManager

        lateinit var binding: ActivityMainBinding

        lateinit var tf: Typeface
        lateinit var tfBold: Typeface

        lateinit var act: AppCompatActivity

        var tabNumber = 1

        fun setTextFonts(root: ViewGroup) {

//        MainActivity.setTextGravity(root);
            for (i in 0 until root.childCount) {
                val v = root.getChildAt(i)
                if (v is TextView) {
                    v.typeface = tf
                } else if (v is Button) {
                    v.typeface = tf
                } else if (v is EditText) {
                    v.typeface = tf
                } else if (v is ViewGroup) {
                    setTextFonts(v)
                }
            }
        }

        fun setupDefaultSettings() {
            with(binding.appBarHome) {
                back.setImageResource(R.drawable.back)
                appbar.setBackgroundColor(Color.WHITE)
                if (context == null)
                    Log.e(
                        "11111",
                        "context is NULL ***************"
                    )
//                title.setTextColor(ContextCompat.getColor(context, R.color.textColorBlack))
                title.setTextColor(Color.BLACK)
                listBtn.setImageResource(R.drawable.side_menu)
                listBtn.visibility = View.INVISIBLE
                bottomBar.visibility = View.VISIBLE
                appbar.visibility = View.VISIBLE
                back.visibility = View.INVISIBLE
                tvLine.visibility = View.VISIBLE
                title.visibility = View.VISIBLE
                title.typeface = tfBold
                if (act!!.supportFragmentManager.backStackEntryCount > 0) {
                    back.visibility = View.VISIBLE
                }
            }

            setTabs()
        }

        fun setTabs() {

            with(binding.appBarHome) {
                homeBtn.setImageResource(R.drawable.home_unsel)
                mealBtn.setImageResource(R.drawable.user_unsel)
                generalInfoBtn.setImageResource(R.drawable.notifications_unsel)
                myAccountBtn.setImageResource(R.drawable.call_unsell)
                when (tabNumber) {
                    1 -> {
                        homeBtn.setImageResource(R.drawable.home_sel)
                    }
                    2 -> {
                        mealBtn.setImageResource(R.drawable.user_sel)
                    }
                    3 -> {
                        generalInfoBtn.setImageResource(R.drawable.notifications_sel)
                    }
                    4 -> {
                        myAccountBtn.setImageResource(R.drawable.call_sell)
                    }
                }

            }
        }

    }

    lateinit var drawer: DrawerLayout

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act = MainActivity()
        context = MainActivity()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mLangSessionManager = LanguageSessionManager(this)
        mSessionManager = SessionManager(this)
        val newbuilder = VmPolicy.Builder()
        StrictMode.setVmPolicy(newbuilder.build())
        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        if (mLangSessionManager.lang != null && mLangSessionManager.lang != "") {
            if (mLangSessionManager.lang == "en") {
                updateViews("en")
                isEnglish = true
                tfBold = Typeface.createFromAsset(this.assets, MicroProjectsConstant.English_Bold_Font)
                tf = Typeface.createFromAsset(this.assets, MicroProjectsConstant.English_Regular_Font)
            } else if (mLangSessionManager.lang == "ar") {
                updateViews("ar")
                isEnglish = false
                tfBold =
                    Typeface.createFromAsset(this.assets, MicroProjectsConstant.Arabic_Bold_Font)
                tf = Typeface.createFromAsset(this.assets, MicroProjectsConstant.Arabic_Regular_Font)
            }
        } else {
            updateViews("en")
            isEnglish = true
        }
        with(binding.appBarHome) {
            setSupportActionBar(toolbar)
            if (binding.relativeSideMenu == null) Log.e(
                "11111",
                "relative_side_menu is NULL ***************"
            )
//            binding.relativeSideMenu?.let { setTextFonts(it) }
            setTextFonts(binding.relativeSideMenu)
            setTextFonts(binding.appBarHome.linearTopBar)
            setTextFonts(binding.appBarHome.bottomBar)
//            linearTopBar?.let { setTextFonts(it) }
//            bottomBar?.let { setTextFonts(it) }
        }
        tabNumber = 1
        setTabs()

        with(binding) {
//            drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
//            val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
//                act, drawer, appBarHome.toolbar, R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close
//            ) {
//                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//                    super.onDrawerSlide(drawerView, slideOffset)
//                    val moveFactor = navView.width * slideOffset
//                    appBarHome.contentHome?.translationX = -moveFactor
//                    appBarHome.toolbar!!.translationX = -moveFactor
//                    appBarHome.bottomBar?.translationX = -moveFactor
//                    Log.d("onDrawerSlide", "onDrawerSlide")
//                }
//
//                override fun onDrawerOpened(drawerView: View) {
//                    super.onDrawerOpened(drawerView)
//                    Log.d("onDrawerOpened", "onDrawerOpened")
//                    relativeSideMenu?.let { setTextFonts(it) }
//                    if (mLangSessionManager.lang.equals("en", ignoreCase = true)) {
//                        tvLang.text = R.string.ArabicLabel.toString()
//                        tvLang.setTypeface(
//                            Typeface.createFromAsset(
//                                (act as MainActivity).assets,
//                                AlDuhaimConstant.Arabic_Regular_Font
//                            )
//                        )
//                    } else {
//                        tvLang.text = R.string.EnglishLabel.toString()
//                        tvLang.setTypeface(
//                            Typeface.createFromAsset(
//                                (act as MainActivity).assets,
//                                AlDuhaimConstant.English_Regular_Font
//                            )
//                        )
//                    }
//                }
//            }
//            drawer.addDrawerListener(toggle)
//            toggle.syncState()
            appBarHome.toolbar!!.navigationIcon = null
        }

        if (intent.hasExtra("fragType")) {

            val fragType = intent.getIntExtra("fragType", 0)
            Log.e("22222 ", "MainAct fragtype ===>>> " + fragType)

            if (fragType == 1) {
                Navigator.loadFragment(
                    this, HomeFragment.newInstance(this),
                    R.id.content_home,
                false,
                "home"
                )
            }
//            else if (fragType == 0) {
//
//                Navigator.loadFragment(
//                    this, LanguageFragment.newInstance(this),
//                    R.id.content_home,
//                    false,
//                    "home"
//                )
//            }
        } else {
            Navigator.loadFragment(
                this, HomeFragment.newInstance(this),
                R.id.content_home,
                false,
                "home"
            )
        }

    }

    override fun onStart() {
        super.onStart()

        with(binding.appBarHome) {

            homeBtn.setOnClickListener(View.OnClickListener {
                tabNumber = 1
                setTabs()
                Navigator.loadFragment(this@MainActivity, HomeFragment.newInstance(this@MainActivity), R.id.content_home, true, "home")
            })

            mealBtn.setOnClickListener(View.OnClickListener {
                tabNumber = 2
                setTabs()
//                Navigator.loadFragment(act, HomeFragment.newInstance(MainActivity()), R.id.content_home, true, "home")
            })

            generalInfoBtn.setOnClickListener(View.OnClickListener {
                tabNumber = 3
                setTabs()
//                Navigator.loadFragment(act, HomeFragment.newInstance(MainActivity()), R.id.content_home, true, "home")
            })

            myAccountBtn.setOnClickListener(View.OnClickListener {
                tabNumber = 4
                setTabs()
//                Navigator.loadFragment(act, HomeFragment.newInstance(MainActivity()), R.id.content_home, true, "home")
            })

        }
    }

    fun updateViews(languageCode: String) {
        LocaleHelper.setLocale(this, languageCode)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

}