package com.example.MD.View

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.MD.Domain.Auth
import com.example.MD.Domain.LoginCredentialsModel
import com.example.MD.Domain.LoginUseCase
import com.example.MD.R
import com.example.MD.ViewModel.LoginViewModel
import com.example.MD.ViewModel.LoginViewModelFactory
import com.example.MD.addons.makeClearableEditTextPass
import com.example.MD.addons.onRightDrawableClicked
import com.example.MD.data.mapper.GenderModelDTOMapper
import com.example.MD.data.mapper.LoginCredentialsModelDTOMapper
import com.example.MD.data.mapper.UserRegisterModelDTOMapper
import com.example.MD.data.repository.AuthRepositoryImpl
import com.example.MD.databinding.LogInFragmentBinding
import kotlinx.coroutines.launch

class LogInFragment : Fragment(R.layout.log_in_fragment) {
    private var binding: LogInFragmentBinding? = null
    private val mapper = GenderModelDTOMapper()
    private val registerModelDTOMapper = UserRegisterModelDTOMapper(mapper)
    private val loginModelDTOMapper = LoginCredentialsModelDTOMapper()
    private val repository: Auth = AuthRepositoryImpl(registerModelDTOMapper = registerModelDTOMapper,loginModelDTOMapper = loginModelDTOMapper)
    private val loginUseCase = LoginUseCase(repository)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(
            this, LoginViewModelFactory(loginUseCase)
        )[LoginViewModel::class.java]
        observeData(viewModel)
        super.onViewCreated(view, savedInstanceState)
        binding = LogInFragmentBinding.bind(view)
        binding!!.password.makeClearableEditTextPass(null,null)
        binding!!.password.onRightDrawableClicked { visibilitySwitch(it) }
        binding!!.login.addTextChangedListener { text->if(text!=null){ logInCheck() }}
        binding!!.password.addTextChangedListener { text->if(text!=null){ logInCheck() }}
        binding!!.goBack.setOnClickListener {
           findNavController().navigate(R.id.action_logInFragment_to_welcomeFragment)
        }
        binding!!.enterButton.setOnClickListener {
            viewModel.login(LoginCredentialsModel(binding!!.login.text.toString(),binding!!.password.text.toString()))
        }
        binding!!.enterButton.isClickable = false
    }

    private fun observeData(viewModel:LoginViewModel){
        lifecycleScope.launch {
            viewModel.loginExist.collect{
                if(it){
                    findNavController().navigate(R.id.action_logInFragment_to_feedFragment)
                }
            }
        }
    }

    private fun logInCheck(){
        if(binding!!.login.text.isNotEmpty() && binding!!.password.text.isNotEmpty()){
            binding!!.enterButton.background = this.context?.let { ContextCompat.getDrawable(it, R.drawable.gradient) }
            binding!!.enterButton.setTextColor( resources.getColor(R.color.White))
            binding!!.enterButton.isClickable = true
        }
        else{
            binding!!.enterButton.background = this.context?.let { ContextCompat.getDrawable(it, R.drawable.shape) }
            binding!!.enterButton.setTextColor( resources.getColor(R.color.Gray_Faded))
            binding!!.enterButton.isClickable = false
        }
    }

    private fun visibilitySwitch(text: EditText) {
        if (text.inputType == 129) {
            text.inputType = 145
            text.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.title_eye_off),
                null
            )
        }
        else{
            text.inputType = 129
            text.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.title_eye),
                null
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}