package com.microprojects.app.classes

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.alduhaim_kotlin.app.classes.showAnimatedET
import com.alduhaim_kotlin.app.classes.showLabelOnEditText
import com.microprojects.app.R
import com.microprojects.app.databinding.CustomEditTextBinding

class CustomEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyle, defStyleRes) {

    var binding = CustomEditTextBinding.inflate(LayoutInflater.from(context), this)

    init {

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it,
                R.styleable.custom_component_attributes, 0, 0)
            val title = resources.getText(typedArray
                .getResourceId(R.styleable
                    .custom_component_attributes_custom_component_title,
                    R.string.app_name))

            if (showAnimatedET) {

                binding.fixedEt.visibility = GONE
                binding.animationEt.visibility = VISIBLE
                binding.animTextInputLayout1.hint = title
            } else {

                binding.animationEt.visibility = GONE
                binding.fixedEt.visibility = VISIBLE
                binding.fixedTvLabel.visibility = if (showLabelOnEditText) VISIBLE else GONE
                binding.fixedTvLabel.text = title
            }

            typedArray.recycle()
        }
    }

    companion object {

//        lateinit var binding : CustomEditTextBinding

//        fun getText(): String {
//            return if (showLabelOnEditText) binding.etEditText.text.toString() else "abc"
//        }
    }

}