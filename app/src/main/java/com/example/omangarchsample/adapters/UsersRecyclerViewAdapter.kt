package com.example.omangarchsample.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.omangarchsample.BR
import com.example.omangarchsample.R
import com.example.omangarchsample.databinding.LayoutItemUserBinding
import com.example.omangarchsample.model.database.User

class UsersRecyclerViewAdapter(private var userList: List<User>, private val context: Context) :
    RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>(), ClickListener {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_item_user, parent, false
        )
        return ViewHolder(binding)
    }
    fun setUserList( userList: List<User>){
        this.userList =userList
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
        holder.itemRowBinding.itemClickListener = this;
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(var itemRowBinding: LayoutItemUserBinding) : RecyclerView.ViewHolder(
        itemRowBinding.root
    ) {
        fun bind(obj: User?) {
            itemRowBinding.setVariable(BR.user, obj)
            itemRowBinding.executePendingBindings()
        }
    }



    override fun cardClicked(user: User?) {
        Toast.makeText(
            context, "You clicked " + user?.firstName,
            Toast.LENGTH_LONG
        ).show()
    }
}
 internal interface ClickListener {
    fun cardClicked(user: User?)
}


@BindingAdapter("placeholder","url")
fun setImage(image: ImageView,placeHolder: Drawable,url: String?) {

    if (!url.isNullOrEmpty()){

        image.load(url) {
            crossfade(true)
            placeholder(R.drawable.place_holder)
            transformations(CircleCropTransformation())
        }
    }
    else{
        image.setImageDrawable(placeHolder)
    }


}