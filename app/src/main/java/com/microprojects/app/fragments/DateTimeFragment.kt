package com.microprojects.app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.microprojects.app.MainActivity
import com.microprojects.app.MainActivity.Companion.setTextFonts
import com.microprojects.app.MainActivity.Companion.setupDefaultSettings
import com.microprojects.app.R
import com.microprojects.app.classes.FixControl
import com.microprojects.app.classes.GlobalFunctions
import com.microprojects.app.classes.LanguageSessionManager
import com.microprojects.app.classes.SessionManager
import com.microprojects.app.databinding.FragmentDateTimeBinding
import java.util.*


open class DateTimeFragment() : Fragment() {


    companion object {

        private val TAG = DateTimeFragment::class.java.simpleName

        private lateinit var act: MainActivity

        private lateinit var fragment: DateTimeFragment


        @JvmStatic
        fun newInstance(act: MainActivity): DateTimeFragment {

            fragment = DateTimeFragment()

            DateTimeFragment.act = act

            return fragment as DateTimeFragment

        }

    }

    lateinit var mSessionManager: SessionManager

    lateinit var languageSessionManager: LanguageSessionManager

    lateinit var binding: FragmentDateTimeBinding


    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Entered DateTimeFragment !!!====<>>>>>")

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

            binding = FragmentDateTimeBinding.inflate(inflater, container, false)

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

        setTextFonts(binding.root)

        setupDefaultSettings()

    }


    override fun onStart() {
        super.onStart()

        with(binding) {

            MainActivity.binding.appBarHome.title.text = act?.getString(R.string.DateTimeLabel)

            tvToday.setOnClickListener(View.OnClickListener { setResult(0, 0, 0) })
            tvTomorrow.setOnClickListener(View.OnClickListener { setResult(0, 0, 1) })
            tvAfterTomorrow.setOnClickListener(View.OnClickListener { setResult(0, 0, 2) })
            tvGo.setOnClickListener(View.OnClickListener {
                FixControl.hideKeybord(MainActivity.binding.appBarHome.title, act)
                setResult(if (etYear.text.isEmpty()) 0 else etYear.text.toString().toInt(),
                    if (etMonth.text.isEmpty()) 0 else etMonth.text.toString().toInt(),
                    if (etDay.text.isEmpty()) 0 else etDay.text.toString().toInt()) })
        }
    }

    private fun setResult(year: Int, month: Int, day: Int) {

        var c = Calendar.getInstance()

        binding.tvResult.text = act.getString(R.string.ResultDate)
            .replace("aaa", GlobalFunctions.convertDate(c, year, month, day))

    }

}