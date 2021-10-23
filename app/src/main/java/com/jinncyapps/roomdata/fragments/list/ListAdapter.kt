package com.jinncyapps.roomdata.fragments.list

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jinncyapps.roomdata.R
import com.jinncyapps.roomdata.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_items_recyclerview, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tv_id).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_firstName).text = currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.tv_lastName).text = currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.tv_age).text = currentItem.age.toString()

        holder.itemView.findViewById<View>(R.id.root_layout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}


