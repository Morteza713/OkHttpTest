package com.example.bottomnavigationtesting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bottomnavigationtesting.databinding.FirstFragmentBinding
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future


class FirstFragment:Fragment(R.layout.first_fragment){

    private val client by lazy { OkHttpClient() }
    private val executor by lazy { Executors.newSingleThreadExecutor() }
    lateinit var binding: FirstFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FirstFragmentBinding.bind(view)

        val request = Request.Builder()
            .url("https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI")
            .build()
        try{
            val future: Future<String?> = executor.submit(object: Callable<String?> {
                override fun call(): String? {
                    val newCall: Call = client.newCall(request)
                    val response : Response = newCall.execute()
                    return response.body()?.string()
                }
            } )
            val data: String? = future.get()
            binding.textView.text = data
        }catch (e : IOException){
            binding.textView.text = e.javaClass.simpleName + " " + e.message
        }

    }


}