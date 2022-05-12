package com.example.friendlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text


class PopPeopleAdapter(val people: List<PopPeopleResult>): RecyclerView.Adapter<PopPeopleAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    //constructor
    open fun RecyclerAdapter(c: Context) {

        //set our context
        this.mContext = c;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopPeopleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_friend, parent, false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: PopPeopleAdapter.ViewHolder, position: Int) {
        return holder.bind(people[position])
    }

    override fun getItemCount(): Int {
        return people.size
    }

    //ViewHolder Class
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView;
        var itemTitle: TextView;
        var itemSummary: TextView;
        var itemNumber: TextView;

        init{
            itemImage = itemView.findViewById(R.id.profileimage);
            itemTitle = itemView.findViewById(R.id.profilename);
            itemSummary = itemView.findViewById(R.id.profilesummary);
            itemNumber = itemView.findViewById(R.id.profilenumber);

        }

        fun bind(people: PopPeopleResult){
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${people.profile_path}").into(itemImage)
            itemTitle.text = people.name;
            itemSummary.text = "Popularity: " + people.popularity.toString();
        }

    }
}