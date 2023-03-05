package com.example.giphyprojects.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.giphyprojects.R
import com.example.giphyprojects.databinding.FragmentGifInfoBinding
import com.example.giphyprojects.model.RetrofitClientObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_KEY = "id_key"


class GifInfoFragment() : Fragment() {
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ID_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGifInfoBinding.inflate(layoutInflater)
        CoroutineScope(Dispatchers.Main).launch {
            val gif = RetrofitClientObject.apiService.getGifById(id?:"")
            binding.tvId.text = gif.body()?.data?.id
            binding.tvTitle.text = gif.body()?.data?.title
            binding.tvRating.text = gif.body()?.data?.rating
            Glide.with(binding.root)
                .asGif()
                .load(
                    gif.body()?.data?.mediaInformation?.original?.embedUrl?:""
                )
                .into(binding.givImage)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(id:String) =
            GifInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_KEY, id)
                }
            }
    }
}