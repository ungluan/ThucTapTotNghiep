package com.example.androidkotlinfinal.utils

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidkotlinfinal.R
import com.example.androidkotlinfinal.domain.User
import com.makeramen.roundedimageview.RoundedImageView

//@BindingAdapter("listDataUser")
//fun RecyclerView.setListDataUser(users: List<User>?) {
//    users?.let {
//        (adapter as UserListAdapter).submitList(users)
//    }
//}

@BindingAdapter("imageUrl")
fun RoundedImageView.setImageUrl(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context).load(imgUri).apply(
            RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(this)
    }
}
//@BindingAdapter("enable")
//fun Button.setEnable(isEnable: Boolean?){
//    isEnable?.let {
//        this.setEnable(isEnable)
//    }
//}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context).load(imgUri).apply(
            RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(this)
    }
}
@BindingAdapter("formattedDate")
fun TextView.setFormattedText(date: String?){
    date?.let {
        text = context.getString(R.string.created_at,date.formatDate())
    }
}
@BindingAdapter("value")
fun TextView.setValue(value: Float?){
    value?.let {
        text = value.toString()
    }
}