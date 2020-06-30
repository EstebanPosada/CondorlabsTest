package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val teamAdapter = TeamAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.title.text = "Teams"
        binding.rvTeams.apply {
            adapter = teamAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel.getTeams()
        observeTeams()
        teamAdapter.onItemClicked = {
            view?.findNavController()
                ?.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
        }
    }

    private fun observeTeams() {
        viewModel.teamsResult.observe(viewLifecycleOwner, Observer { response ->
            response.teams?.let {
                teamAdapter.setItems(it.toMutableList())
            }
        })
    }

}