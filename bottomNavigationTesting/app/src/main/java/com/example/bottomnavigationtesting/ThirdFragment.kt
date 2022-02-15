package com.example.bottomnavigationtesting

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bottomnavigationtesting.databinding.SecondFragmentBinding
import com.example.bottomnavigationtesting.databinding.ThirdFragmentBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.Executors

class ThirdFragment:Fragment(R.layout.third_fragment) {

    private val client by lazy { OkHttpClient() }
    private val executor by lazy { Executors.newSingleThreadExecutor() }
    lateinit var binding: ThirdFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ThirdFragmentBinding.bind(view)

        loadImage(binding.ivPic1,"https://picsum.photos/200?rand=")
        loadImage(binding.ivPic2,"https://picsum.photos/200?rand=")
        loadImage(binding.ivPic3,"https://picsum.photos/200?rand=")
        loadImage(binding.ivPic4,"https://picsum.photos/200?rand=")

    }
    fun loadImage(view:ImageView,url:String) {
        Glide
            .with(this)
            .load(url + System.currentTimeMillis())
            .into(view)
    }

}