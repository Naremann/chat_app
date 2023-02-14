package com.example.chatapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("layout_error")
fun setError(inputLayout: TextInputLayout,error:String?){
    inputLayout.error = error
}
@BindingAdapter("imageSrc")
fun setImage(imageView: ImageView,imageId:Int){
    imageView.setImageResource(imageId)
}