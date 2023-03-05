package com.example.giphyprojects.presentation

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.giphyprojects.MainActivity
import com.example.giphyprojects.R
import com.example.giphyprojects.databinding.ActivityMainBinding
import com.example.giphyprojects.databinding.FragmentListBinding
import com.example.giphyprojects.logic.Navigation
import com.example.giphyprojects.model.pojo.Gif
import com.example.giphyprojects.presentation.adapters.GifRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    val viewModel = ListFragmentViewModel()
    private val listOfGifs = mutableListOf<Gif>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGifFromApiByRequest("hello")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = GifRecyclerAdapter { id ->
            Navigation.simpleFragmentNavigation(GifInfoFragment.newInstance(id), parentFragmentManager)
        }
        val binding = FragmentListBinding.inflate(inflater, container, false)
        val rvGifs = binding.rvGifs
        val editRequestField = binding.etRequest
        editRequestField.setOnKeyListener { view, i, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.getGifFromApiByRequest(editRequestField.text.toString())
                return@setOnKeyListener true
            } else {
                return@setOnKeyListener false
            }
        }
        observeViewStateUpdates(adapter)
        rvGifs.adapter = adapter
        rvGifs.layoutManager = GridLayoutManager(requireContext(), 2)
        return binding.root
    }

    private fun observeViewStateUpdates(adapter: GifRecyclerAdapter) =
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.isLoading.collect {
                if (!it) {
                    adapter.submitList(viewModel.listOfGifs)
                }
            }
        }

}