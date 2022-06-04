package com.example.androidkotlinfinal.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidkotlinfinal.databinding.FragmentHomeBinding
import com.example.androidkotlinfinal.domain.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/***
- Tránh dùng constraint layout vs item của recycler view
- Bởi vì: ConstraintLayout sẽ phải tính toán lại rất nhiều dựa trên các Constraint -> Ngốn CPU
 ***/
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userListAdapter: UserListAdapter
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = UserListAdapter(OnClickListener { user ->
            navigateToUserDetailFragment(user)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresherUser()
        }

        viewModel.isCompletedRefresh.observe(viewLifecycleOwner) { completed ->
            if (completed) binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun navigateToUserDetailFragment(user: User) {
        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(user)
        findNavController().navigate(action)
    }
}