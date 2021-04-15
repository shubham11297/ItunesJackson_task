package com.example.itunesjackson_task.fragments

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.itunesjackson_task.R
import com.example.itunesjackson_task.viewmodel.SongViewModel
import com.example.itunesjackson_task.viewmodel.SongViewModelFactory
import kotlinx.android.synthetic.main.fragment_song_details.*
import java.lang.Exception

class SongDetailsFragment : Fragment() {

    private val viewModel: SongViewModel by activityViewModels {
        SongViewModelFactory()
    }

    lateinit var artwork: AppCompatImageView
    lateinit var songName: AppCompatTextView
    lateinit var singerName: AppCompatTextView

    private var mediaPlayer = MediaPlayer()
    private var songPosition : Int = 0
    var mediaPrepared : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        artwork = view.findViewById(R.id.main_artwork)
        songName = view.findViewById(R.id.song_name)
        singerName = view.findViewById(R.id.singer_name)

        val data = viewModel.returnSelectedData()

        val url = data.previewUrl
        prepareSong(url)

        songName.text = data.trackName
        singerName.text = data.artistName

        Glide.with(requireContext())
            .load(data.artworkUrl100)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(artwork)

//        mediaPlayer.setAudioAttributes(AudioManager.STREAM_MUSIC)

        play_btn.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                if (!mediaPrepared) {
                    prepareSong(url)
                }
                if (songPosition == 0) {
                    mediaPlayer.start()
                    Toast.makeText(requireContext(), "Playing!", Toast.LENGTH_SHORT).show()
                } else {
                    mediaPlayer.seekTo(songPosition)
                    mediaPlayer.start()
                }
            }
        }

        pause_btn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                songPosition = mediaPlayer.currentPosition
            } else {
                Toast.makeText(requireContext(), "Song not playing!", Toast.LENGTH_SHORT).show()
            }
        }

        stop_btn.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPrepared = false
            songPosition = 0
            Toast.makeText(requireContext(), "Stopped!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun prepareSong(url: String){
        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPrepared = true
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error: $e", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
        mediaPlayer.reset()
    }
}