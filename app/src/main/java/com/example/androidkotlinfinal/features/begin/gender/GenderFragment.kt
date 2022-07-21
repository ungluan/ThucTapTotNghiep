package com.example.androidkotlinfinal.features.begin.gender

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidkotlinfinal.R
import com.example.androidkotlinfinal.databinding.FragmentGenderBinding
import com.example.androidkotlinfinal.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GenderFragment : Fragment() {
    @Inject lateinit var navigator: AppNavigator
    private lateinit var binding: FragmentGenderBinding
    private val viewModel: GenderViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenderBinding.inflate(inflater, container, false)
        binding.cardViewMale.setOnClickListener{
            viewModel.changeGender()
        }
        binding.cardViewFemale.setOnClickListener {
            viewModel.changeGender()
        }
        binding.btnNext.setOnClickListener {
            // Todo: 01 save gender
            // Todo: 02 navigate to profile
            val action = GenderFragmentDirections.actionGenderFragmentToSetupProfileFragment()
            navigator.getNaveHostFragment().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.maleSelected.observe(viewLifecycleOwner) { maleSelected ->
            if(maleSelected){
                binding.cardViewMale.strokeColor = requireContext().getColor(R.color.yellow)
                var maleImageDrawable: Drawable = binding.imgMale.background
                maleImageDrawable = DrawableCompat.wrap(maleImageDrawable)
                DrawableCompat.setTint(maleImageDrawable, requireContext().getColor(R.color.yellow))

                binding.cardViewFemale.strokeColor = requireContext().getColor(R.color.inactive)
                var femaleImageDrawable: Drawable = binding.imgFemale.background
                femaleImageDrawable = DrawableCompat.wrap(femaleImageDrawable)
                DrawableCompat.setTint(femaleImageDrawable, requireContext().getColor(R.color.inactive))
            }
            else {
                binding.cardViewMale.strokeColor = requireContext().getColor(R.color.inactive)
                var maleImageDrawable: Drawable = binding.imgMale.background
                maleImageDrawable = DrawableCompat.wrap(maleImageDrawable)
                DrawableCompat.setTint(maleImageDrawable, requireContext().getColor(R.color.inactive))

                binding.cardViewFemale.strokeColor = requireContext().getColor(R.color.yellow)
                var femaleImageDrawable: Drawable = binding.imgFemale.background
                femaleImageDrawable = DrawableCompat.wrap(femaleImageDrawable)
                DrawableCompat.setTint(femaleImageDrawable, requireContext().getColor(R.color.yellow))
            }
        }

    }
}