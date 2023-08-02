package com.techsultan.quizapplication.fragments.questionfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.techsultan.quizapplication.MainActivity
import com.techsultan.quizapplication.R
import com.techsultan.quizapplication.databinding.FragmentQuestionOneBinding
import com.techsultan.quizapplication.viewmodel.QuizViewModel


class QuestionOne : Fragment() {

    private var _binding: FragmentQuestionOneBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).viewModel

        displayQuestion()

        binding.nextButton.setOnClickListener {

            val selectedOptionId = binding.radioGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedIndex = binding.radioGroup.indexOfChild(view.findViewById(selectedOptionId))
                viewModel.onOptionSelected(selectedIndex)
                viewModel.checkAnswer(selectedIndex)

                viewModel.goToNextQuestion()
                findNavController().navigate(R.id.action_questionOne_to_questionTwo)

            } else {

                Toast.makeText(context, "Select an answer", Toast.LENGTH_SHORT).show()
            }

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayQuestion() {
        val currentQuestion = viewModel.getCurrentQuestion()
        binding.tvQuestion.text = currentQuestion.questionText

        // Clear the existing RadioButtons and add new ones for the current question
        binding.radioGroup.removeAllViews()
        val options = currentQuestion.options
        for (i in options.indices) {
            val radioButton = RadioButton(requireContext())
            radioButton.text = options[i]
            radioButton.id = i
            binding.radioGroup.addView(radioButton)
        }

        binding.radioGroup.clearCheck()
    }
}