package com.techsultan.quizapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.techsultan.quizapplication.MainActivity
import com.techsultan.quizapplication.R
import com.techsultan.quizapplication.databinding.FragmentQuizResultBinding
import com.techsultan.quizapplication.viewmodel.QuizViewModel


class QuizResultFragment : Fragment() {

    private var _binding: FragmentQuizResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).viewModel

        binding.nextButton.setOnClickListener {

            viewModel.resetQuiz()
            findNavController().navigate(R.id.action_quizResultFragment_to_home_dest)
        }

        val quizResult = viewModel.getTotalCorrectAnswers()
        binding.currentScoreTv.text = quizResult.toString()


    }

}