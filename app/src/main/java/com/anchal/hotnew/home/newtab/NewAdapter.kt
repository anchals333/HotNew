package com.anchal.hotnew.home.newtab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anchal.hotnew.R
import com.anchal.hotnew.databinding.NewItemBinding


class NewAdapter(private val context: Context, private var list: MutableList<NewModel>, private var filterList: MutableList<NewModel>) : RecyclerView.Adapter<NewAdapter.LeadsViewHolder>() {

    lateinit var onBottomReachedListener: OnBottomReachedListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadsViewHolder {
        val binding = DataBindingUtil.inflate<NewItemBinding>(LayoutInflater.from(context),
                R.layout.new_item, parent, false)

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


    inner class LeadsViewHolder(var itemBinding: NewItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun setData(model: NewDataModel) {
            itemBinding.model = model
            itemBinding.executePendingBindings()
        }

    }

    fun updateList(addlist: List<NewModel>){
        list.addAll(addlist)
        notifyDataSetChanged()
    }

    fun putOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener?) {
        this.onBottomReachedListener = onBottomReachedListener!!
    }

    interface OnBottomReachedListener {
        fun onBottomReached(position: Int)
    }

    fun filterList(filterdNames: ArrayList<NewModel>) {
        list = filterdNames
        notifyDataSetChanged()
    }


}