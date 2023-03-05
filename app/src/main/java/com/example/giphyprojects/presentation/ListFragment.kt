package com.example.giphyprojects.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyprojects.R
import com.example.giphyprojects.databinding.FragmentListBinding
import com.example.giphyprojects.navigation.Navigation
import com.example.giphyprojects.presentation.adapters.GifRecyclerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    val viewModel = ListFragmentViewModel()
    var request = "hello"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = GifRecyclerAdapter { id ->
            Navigation.simpleFragmentNavigation(
                GifInfoFragment.newInstance(id),
                parentFragmentManager
            )
        }
        val spanCount =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                resources.getInteger(R.integer.portrait_span)
            else resources.getInteger(R.integer.horizontal_span)
        val binding = FragmentListBinding.inflate(inflater, container, false)
        val rvGifs = binding.rvGifs
        val editRequestField = binding.etRequest
        editRequestField.setOnKeyListener { view, i, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                request = editRequestField.text.toString()
                collectUiState(adapter, request)
                return@setOnKeyListener true
            } else {
                return@setOnKeyListener false
            }
        }
        collectUiState(adapter, request)
        rvGifs.adapter = adapter
        rvGifs.layoutManager = GridLayoutManager(requireContext(), spanCount)
        return binding.root
    }

    private fun collectUiState(adapter: GifRecyclerAdapter, request: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getGifFromApiByRequest(request).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
