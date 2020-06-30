package com.example.myapplication.ui.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.app.BaseFragment
import com.example.myapplication.databinding.DetailFragmentBinding
import com.example.myapplication.ui.MainViewModel
import kotlinx.android.synthetic.main.detail_fragment.view.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val viewModel: MainViewModel by sharedViewModel()
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val id = args.id

        showProgress()
        viewModel.getTeamFromId(id)
        observeTeam()
    }

    private fun observeTeam() {
        viewModel.teamData.observe(viewLifecycleOwner, Observer { data ->
            popProgress()
            val team = data.team
            data.events?.let {
                val eventString = it.joinToString(separator = "\n") { e -> getString(R.string.event_list, e.strEvent, e.dateEvent) }
                binding.events.text = eventString
            }
            binding.teamName.text = team.strTeam
            binding.teamDescription.apply {
                text = team.strDescriptionES
                setOnTouchListener { v, event ->
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    when (event.action and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_SCROLL -> v.parent.requestDisallowInterceptTouchEvent(
                            false
                        )
                    }
                    false
                }
                movementMethod = ScrollingMovementMethod()
            }
            binding.foundation.text = getString(R.string.foundation_year, team.intFormedYear)
            Glide.with(requireContext()).load(team.strTeamBadge).into(binding.badge)
            Glide.with(requireContext()).load(team.strTeamJersey).into(binding.jersey)
            binding.website.text = team.strWebsite
            binding.facebook.text = team.strFacebook
            binding.twitter.text = team.strTwitter
            binding.instagram.text = team.strInstagram
        })
    }

}