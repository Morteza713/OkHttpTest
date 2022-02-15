package com.example.bottomnavigationtesting

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.bottomnavigationtesting.databinding.FirstFragmentBinding
import com.example.bottomnavigationtesting.databinding.SecondFragmentBinding
import okhttp3.*
import java.io.IOException
import java.util.concurrent.Executors

class SecondFragment:Fragment(R.layout.second_fragment) {

    private val client by lazy { OkHttpClient() }
    private val executor by lazy { Executors.newSingleThreadExecutor() }
    lateinit var binding: SecondFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SecondFragmentBinding.bind(view)

        val request = Request.Builder()
            .url("https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI")
            .build()

        val Call: Call = client.newCall(request)
        Call.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                setResult(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                setResult(response.body()?.string())
            }
        })

    }
    fun setResult(data:String?){
        Handler(Looper.getMainLooper()).post {
            binding.textView.text = data
        }
    }

}