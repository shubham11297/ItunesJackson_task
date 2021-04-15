package com.example.itunesjackson_task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesjackson_task.R
import com.example.itunesjackson_task.adapter.SongAdapter
import com.example.itunesjackson_task.viewmodel.SongViewModel
import com.example.itunesjackson_task.viewmodel.SongViewModelFactory
import kotlinx.android.synthetic.main.fragment_song_list.*


class SongListFragment : Fragment() {

    private lateinit var adapter : SongAdapter
    private val viewModel: SongViewModel by activityViewModels {
        SongViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getSongData()
        viewModel.dataRetrieved.observe(viewLifecycleOwner, Observer {
            if (it) {
                loadRecyclerView()
            }
        })
    }

    private fun loadRecyclerView() {
        val data = viewModel.fullData
        if (data != null) {
            val image = data.results.map { it.artworkUrl30 }
            val collection = data.results.map { it.trackName }
            val artist = data.results.map { it.artistName }
            val price = data.results.map { it.trackPrice }

            adapter = SongAdapter(requireContext(), image, collection, artist, price) {pos ->

                viewModel.selectedPosition = pos
                findNavController().navigate(R.id.action_songListFragment_to_songDetailsFragment)
            }
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            song_list_view.adapter = adapter
            song_list_view.layoutManager = layoutManager
            song_list_view.setHasFixedSize(true)
        }

    }
}