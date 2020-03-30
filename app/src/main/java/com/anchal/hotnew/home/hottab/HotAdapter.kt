package com.anchal.hotnew.home.hottab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anchal.hotnew.R
import com.anchal.hotnew.databinding.HotItemBinding


class HotAdapter(private val context: Context, private var list: MutableList<HotModel>, private var filterList: MutableList<HotModel>) : RecyclerView.Adapter<HotAdapter.LeadsViewHolder>() {

    lateinit var onBottomReachedListener: OnBottomReachedListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadsViewHolder {
        val binding = DataBindingUtil.inflate<HotItemBinding>(LayoutInflater.from(context),
                R.layout.hot_item, parent, false)

        return LeadsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeadsViewHolder, position: Int) {
        if (position == list.size - 15)
            onBottomReachedListener.onBottomReached(position)
        holder.setData(list.get(position).data!!)
    }


    override fun getItemCount(): Int {
        return list.size
    }


    inner class LeadsViewHolder(var itemBinding: HotItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun setData(model: DataModel) {
            itemBinding.model = model
            itemBinding.executePendingBindings()
        }

    }

    fun updateList(addlist: List<HotModel>){
        list.addAll(addlist)
        notifyDataSetChanged()
    }

    fun putOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener?) {
        this.onBottomReachedListener = onBottomReachedListener!!
    }

    interface OnBottomReachedListener {
        fun onBottomReached(position: Int)
    }

    fun filterList(filterdNames: ArrayList<HotModel>) {
        list = filterdNames
        notifyDataSetChanged()
    }


}