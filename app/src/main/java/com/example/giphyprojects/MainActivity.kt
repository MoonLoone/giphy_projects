package com.example.giphyprojects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.giphyprojects.databinding.ActivityMainBinding
import com.example.giphyprojects.logic.Navigation
import com.example.giphyprojects.presentation.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState?.isEmpty == true){
            supportFragmentManager.beginTransaction()
                .replace(R.id.gifsFragmentContainer, ListFragment())
                .addToBackStack(Navigation.STACK_NAME)
                .commit()
        }
    }
}