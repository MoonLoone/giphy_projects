package com.example.giphyprojects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.giphyprojects.navigation.Navigation
import com.example.giphyprojects.presentation.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState?.isEmpty == true) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.gifsFragmentContainer, ListFragment())
                .addToBackStack(Navigation.STACK_NAME)
                .commit()
        }
    }
}