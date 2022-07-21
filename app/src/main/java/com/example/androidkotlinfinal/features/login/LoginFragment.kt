package com.example.androidkotlinfinal.features.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidkotlinfinal.R
import com.example.androidkotlinfinal.databinding.FragmentLoginBinding
import com.example.androidkotlinfinal.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.btnLogin.setOnClickListener{
//            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
//            navigator.getNaveHostFragment().navigate(action)
//        }
    }
}