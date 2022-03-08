package com.microprojects.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.microprojects.app.MainActivity
import com.microprojects.app.adapters.HomeAdapter.MyViewHolder
import com.microprojects.app.databinding.RowHomeBinding
import com.microprojects.app.models.HomeModel

class HomeAdapter(act: MainActivity, list: ArrayList<HomeModel>, listener: HomeAdapter.OnItemClickListener) :
    RecyclerView.Adapter<MyViewHolder>() {

    var list = list
    var act = act
    var listener = listener

    open class MyViewHolder(v: RowHomeBinding) : RecyclerView.ViewHolder(v.root) {

        open var binding: RowHomeBinding = v

        fun bind(listener: HomeAdapter.OnItemClickListener, position: Int) {

            MainActivity.setTextFonts(binding.root)

            binding.linearParent.setOnClickListener(View.OnClickListener {
                listener.onItemClick(position)
            })
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.MyViewHolder {

        val itemView =
            RowHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(itemView)
    }


    inner class ViewHolder(override var binding: RowHomeBinding) :
        HomeAdapter.MyViewHolder(binding)


    override fun onBindViewHolder(holder: HomeAdapter.MyViewHolder, position: Int) {

        holder.bind(listener, position)

        if (list[position] != null) {

            with(holder.binding) {

                tvTitle.text = list[position].title

            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    interface OnItemClickListener {

        fun onItemClick(position: Int)
    }
}