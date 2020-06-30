package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.R
import com.example.myapplication.app.BaseFragment
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.service.model.League
import com.example.myapplication.ui.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : BaseFragment() {

    private val viewModel: MainViewModel by sharedViewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val teamAdapter = TeamAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.title.text = getString(R.string.leagues)
        binding.rvTeams.apply {
            adapter = teamAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        binding.leagueSelection.apply {
            val leagues = listOf<League>(
                League("Spanish La Liga", "4335"),
                League("Italian Serie A", "4332"),
                League("Mexican Primera League", "4347")
            )
            adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, leagues)
        }

        showProgress()
        observeTeams()
        league_selection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                showProgress()
                val leagueId = (league_selection.selectedItem as League).id
                viewModel.getTeams(leagueId)
            }

        }
        teamAdapter.onItemClicked = {
            view?.findNavController()
                ?.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
        }
    }

    private fun observeTeams() {
        viewModel.teamsResult.observe(viewLifecycleOwner, Observer { response ->
            popProgress()
            response.teams?.let {
                teamAdapter.setItems(it.toMutableList())
            }
        })
    }
}