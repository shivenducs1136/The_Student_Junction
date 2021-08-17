package com.example.tsj.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.model.Feeds
import com.squareup.picasso.Picasso


class FeedAdapter(private val context: Context): RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    private var collect:List<Feeds.Article> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.newsviewitem, parent, false)
        return FeedViewHolder(v)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val collect= collect[position]
        Picasso.with(context)
            .load(collect.urlToImage)
            .into(holder.feedurl)

        holder.feedtitle.text = collect.title

        holder.itemView.setOnClickListener {


            var bundle=Bundle()
            bundle.putString("imgurl",collect.urlToImage)
            bundle.putString("author",collect.author)
            bundle.putString("publish",collect.publishedAt)
            bundle.putString("title",collect.title)
            bundle.putString("content",collect.content)
            it.findNavController().navigate(R.id.action_home4_to_fragmentOnClick,bundle)


        }
    }

    override fun getItemCount(): Int {
        return collect.size
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       val feedurl = itemView.findViewById<ImageView>(R.id.news_url)
        val feedtitle = itemView.findViewById<TextView>(R.id.news_title)
    }

    fun setStateWiseTracker(list: List<Feeds.Article>){
        this.collect=list
        notifyDataSetChanged()
    }
}
