package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.service.model.Team
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Team>()
//    private lateinit var binding: ItemTeamBinding
    var onItemClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        binding = ItemTeamBinding.bind(LayoutInflater.from(parent.context).inflate())
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        ) {}
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.apply {
            name.text = item.strTeam
            alternate_name.text = item.strAlternate
            stadium_name.text =
                String.format(context.getString(R.string.stadium_name), item.strStadium)
            Glide.with(context).load(item.strTeamBadge).into(badge_img)
            setOnClickListener { onItemClicked?.invoke(item.idTeam) }
        }
    }

    fun setItems(newItems: MutableList<Team>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

}