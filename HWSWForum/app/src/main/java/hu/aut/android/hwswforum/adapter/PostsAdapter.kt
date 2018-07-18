package hu.aut.android.hwswforum.adapter

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.row_post.view.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import com.bumptech.glide.Glide
import android.text.TextUtils
import com.google.firebase.database.FirebaseDatabase
import hu.aut.android.hwswforum.R
import hu.aut.android.hwswforum.data.Post


class PostsAdapter(
        private val context: Context,
        private val uId: String) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private var postsList = mutableListOf<Post>()
    private var postKeys = mutableListOf<String>()

    private var lastPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.row_post, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() =  postsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (uid, author, title, body, imgUrl) = postsList[holder.adapterPosition]
        holder.tvAuthor.text = author
        holder.tvTitle.text = title
        holder.tvBody.text = body

        if (imgUrl.isNotEmpty()) {
            holder.ivPhoto.visibility = View.VISIBLE
            Glide.with(context).load(imgUrl).into(holder.ivPhoto)
        } else {
            holder.ivPhoto.visibility = View.GONE
        }

        if (uid == uId) {
            holder.btnDelete.visibility = View.VISIBLE
            holder.btnDelete.setOnClickListener { removePost(holder.adapterPosition) }
        } else {
            holder.btnDelete.visibility = View.GONE
        }


        setAnimation(holder.itemView, position)
    }

    fun addPost(post: Post, key: String) {
        postsList.add(post)
        postKeys.add(key)
        notifyDataSetChanged()
    }

    private fun removePost(index: Int) {
        FirebaseDatabase.getInstance()
                .getReference("posts")
                .child(postKeys[index])
                .removeValue()

        postsList.removeAt(index)
        postKeys.removeAt(index)
        notifyItemRemoved(index)
    }


    fun removePostByKey(key: String) {
        val index = postKeys.indexOf(key)
        if (index != -1) {
            postsList.removeAt(index)
            postKeys.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context,
                    android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor: TextView = itemView.tvAuthor
        val tvTitle: TextView = itemView.tvTitle
        val tvBody: TextView = itemView.tvBody
        val btnDelete: Button = itemView.btnDelete
        val ivPhoto: ImageView = itemView.ivPhoto
    }
}