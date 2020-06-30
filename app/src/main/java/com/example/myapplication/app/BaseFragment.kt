package com.example.myapplication.app

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.myapplication.R

abstract class BaseFragment : Fragment() {

    private lateinit var progressDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progressDialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.dialog_loading_progress)
            .setCancelable(false)
            .create()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun showProgress() {
        hideKeyboard()
        if (!progressDialog.isShowing) {
            progressDialog.show()
            progressDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun popProgress() {
        if (!(activity?.isFinishing == true || activity?.isDestroyed == true)) {
            progressDialog.dismiss()
        }
    }

    private fun hideKeyboard() {
        val window = requireActivity().window.decorView
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.windowToken, 0)
    }
}