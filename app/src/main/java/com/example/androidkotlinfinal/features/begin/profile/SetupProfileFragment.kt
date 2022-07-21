package com.example.androidkotlinfinal.features.begin.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.androidkotlinfinal.databinding.SetupFragmentProfileBinding
import com.example.androidkotlinfinal.navigation.AppNavigator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SetupProfileFragment @Inject constructor() : Fragment() {
    lateinit var binding: SetupFragmentProfileBinding
//    lateinit var textInputLayoutHeight: TextInputLayout
    lateinit var heightInputEditText: TextInputEditText
//    lateinit var weightInputLayout: TextInputLayout
    lateinit var weightInputEditText: TextInputEditText
    private val viewModel: SetupProfileViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SetupFragmentProfileBinding.inflate(inflater, container, false)

        heightInputEditText = binding.textInputHeight
        weightInputEditText = binding.textInputWeight
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        heightInputEditText.doOnTextChanged { text, _, _, _ ->
            Timber.d("Height:"+ text.toString())
            Timber.d("Weight:"+ weightInputEditText.text.toString())
            viewModel.checkEnable(text.toString(), weightInputEditText.text.toString())
        }
        weightInputEditText.doOnTextChanged { text, _, _, _ ->
            Timber.d("Height:"+ heightInputEditText.text.toString())
            Timber.d("Weight:"+ text.toString())
            viewModel.checkEnable( weightInputEditText.text.toString(),text.toString())
        }

        viewModel.isEnable.observe(viewLifecycleOwner) {
            binding.btnSubmit.isEnabled = it
        }

        binding.btnSubmit.setOnClickListener {
            val action = SetupProfileFragmentDirections.actionSetupProfileFragmentToHomeFragment()
            navigator.getNaveHostFragment().navigate(action)
        }
    }
}