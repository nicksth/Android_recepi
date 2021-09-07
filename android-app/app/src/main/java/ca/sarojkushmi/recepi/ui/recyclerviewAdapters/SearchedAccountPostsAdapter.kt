package ca.sarojkushmi.recepi.ui.recyclerviewAdapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import ca.sarojkushmi.recepi.R
import ca.sarojkushmi.recepi.data.models.roomModels.UserModel
import ca.sarojkushmi.recepi.data.models.roomModels.UserPost
import ca.sarojkushmi.recepi.ui.activities.SearchedAccountPostViewActivity

class SearchedAccountPostsAdapter internal constructor(val context: Context, private val searchedAccount: UserModel) : RecyclerView.Adapter<SearchedAccountPostsAdapter.SearchedAccountPostsViewHolder>() {

    private var postList = mutableListOf<UserPost>()

    class SearchedAccountPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImageView: ImageView = itemView.findViewById(R.id.postImage)
        val linearLayoutPost : LinearLayout = itemView.findViewById(R.id.postLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedAccountPostsViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.user_post_grid_cell, parent, false)
        return SearchedAccountPostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchedAccountPostsViewHolder, position: Int) {
        val uriPath : String = postList[position].uriImgPathString
        holder.postImageView.setImageURI(Uri.parse(uriPath))
        holder.linearLayoutPost.setOnClickListener {
            val intent = Intent(context, SearchedAccountPostViewActivity::class.java)
            intent.putExtra("post", postList[position])
            intent.putExtra("account", searchedAccount)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    internal fun setPostList(searchedAccountPostList: List<UserPost>) {
        postList = searchedAccountPostList.toMutableList()
        notifyDataSetChanged()
    }

    fun getPostAt(position : Int) : UserPost {
        return postList[position]
    }
}