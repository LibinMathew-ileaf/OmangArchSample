package com.example.omangarchsample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.omangarchsample.adapters.UsersRecyclerViewAdapter
import com.example.omangarchsample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        homeViewModel.fetchUser()
        val myRecyclerViewAdapter = UsersRecyclerViewAdapter(homeViewModel.userList.value!!, requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.ryUsers.also {
            it.layoutManager=layoutManager
            it.adapter=(myRecyclerViewAdapter)
        }
        homeViewModel.userList.observe(viewLifecycleOwner) {
            myRecyclerViewAdapter.notifyDataSetChanged()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}