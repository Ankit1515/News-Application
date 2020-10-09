package com.example.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.Article


class HeadlinesAdapter(
    private val newsList: List<Article>,
    private val context: Context
)
    : RecyclerView.Adapter<HeadlinesAdapter.ViewHolder>() {

    private var onItemClickListener: ((Article) -> Unit)? = null

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var title: TextView
        internal var discription: TextView
        internal var image : ImageView
        internal var sources : TextView
        internal var publication : TextView
        internal var url : TextView

        init {
            title = view.findViewById(R.id.tvTitle)
            discription = view.findViewById(R.id.tvDescription)
            image = view.findViewById(R.id.ivArticleImage)
            sources = view.findViewById(R.id.tvSource)
            publication = view.findViewById(R.id.tvPublishedAt)
            url = view.findViewById(R.id.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holder, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = newsList[position]

        holder.title.text = note.title
        holder.discription.text = note.description
        holder.sources.text = note.source.toString()
        holder.publication.text = note.publishedAt
        holder.url.text = note.urlToImage

        Glide.with(context).load(note.urlToImage)
            .into(holder.image)

        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(note) }
            }
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}