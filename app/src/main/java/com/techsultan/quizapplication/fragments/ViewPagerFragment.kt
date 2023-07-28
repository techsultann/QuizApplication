package com.techsultan.quizapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.techsultan.quizapplication.R
import com.techsultan.quizapplication.adapter.ViewPagerAdapter
import com.techsultan.quizapplication.databinding.FragmentViewPagerBinding
import com.techsultan.quizapplication.fragments.questionfragment.QuestionOne
import com.techsultan.quizapplication.fragments.questionfragment.QuestionThree
import com.techsultan.quizapplication.fragments.questionfragment.QuestionTwo


class ViewPagerFragment : Fragment() {

    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        viewPager = binding.viewPager

        val fragments = listOf(
            QuestionOne(),
            QuestionTwo(),
            QuestionThree()
        )

        val adapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}