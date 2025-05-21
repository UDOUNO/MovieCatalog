package com.example.MD.View

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.MD.R
import com.example.MD.SingUpActivity
import com.example.MD.databinding.WelcomeFragmentBinding


class WelcomeFragment : Fragment(R.layout.welcome_fragment) {
    private var binding: WelcomeFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WelcomeFragmentBinding.bind(view)
        val token = SingUpActivity.getApp().appSharedPref.getString("token","")
        if(token != ""){
            findNavController().navigate(R.id.action_welcomeFragment_to_feedFragment)
        }
        binding!!.buttonLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_logInFragment)
        }
        binding!!.buttonSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_singUpFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
