package com.microprojects.app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.microprojects.app.MainActivity
import com.microprojects.app.R
import com.microprojects.app.classes.CustomEditText
import com.microprojects.app.classes.LanguageSessionManager
import com.microprojects.app.classes.SessionManager
import com.microprojects.app.databinding.FragmentCustomEditTextBinding


open class CustomEditTextFragment() : Fragment() {


    companion object {

        private val TAG = CustomEditTextFragment::class.java.simpleName

        private lateinit var act: MainActivity

        private lateinit var fragment: CustomEditTextFragment


        @JvmStatic
        fun newInstance(act: MainActivity): CustomEditTextFragment {

            fragment = CustomEditTextFragment()

            CustomEditTextFragment.act = act

            return fragment as CustomEditTextFragment

        }

    }

    lateinit var mSessionManager: SessionManager

    lateinit var languageSessionManager: LanguageSessionManager

    lateinit var binding: FragmentCustomEditTextBinding


    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Entered CustomEditTextFragment !!!====<>>>>>")

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

            binding = FragmentCustomEditTextBinding.inflate(inflater, container, false)

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

        MainActivity.setTextFonts(binding.root)

    }


    override fun onStart() {
        super.onStart()

        MainActivity.setupDefaultSettings()

        MainActivity.binding.appBarHome.title.text = act?.getString(R.string.CustomET)

        binding.tvTemp.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

//                binding.tvTemp.text = CustomEditText.getText()
            }
        })
//        with(binding) {
//            cetFirstName.binding.etEditText.addTextChangedListener(object : TextWatcher {
//
//                override fun afterTextChanged(p0: Editable?) {
//                    cetFirstName.binding.tvLabel.text = p0
//
//                }
//
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    cetFirstName.binding.tvLabel.text = "No Label"
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//                    cetFirstName.binding.tvLabel.text = p0
//                }
//            })
//        }
    }

}