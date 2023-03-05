package com.example.giphyprojects.logic

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.giphyprojects.R

object Navigation {

    const val STACK_NAME = "main_stack"

    fun simpleFragmentNavigation(fragment: Fragment, fragmentManager: FragmentManager){
        fragmentManager.beginTransaction()
            .replace(R.id.gifsFragmentContainer, fragment)
            .addToBackStack(STACK_NAME)
            .commit()
    }

}