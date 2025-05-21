package com.example.MD.View

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.MD.Domain.Auth
import com.example.MD.Domain.GenderModel
import com.example.MD.Domain.RegisterUseCase
import com.example.MD.Domain.UserRegisterModel
import com.example.MD.R
import com.example.MD.ViewModel.SignUpFactory
import com.example.MD.ViewModel.SingUpViewModel
import com.example.MD.addons.onRightDrawableClicked
import com.example.MD.databinding.SingUpFragmentBinding
import com.example.MD.addons.makeClearableEditText
import com.example.MD.addons.makeClearableEditTextPass
import com.example.MD.data.mapper.GenderModelDTOMapper
import com.example.MD.data.mapper.LoginCredentialsModelDTOMapper
import com.example.MD.data.mapper.UserRegisterModelDTOMapper
import com.example.MD.data.repository.AuthRepositoryImpl


class SingUpFragment : Fragment(R.layout.sing_up_fragment) {
    private var binding: SingUpFragmentBinding? = null
    private val mapper = GenderModelDTOMapper()
    private var gender:GenderModel = GenderModel.male
    private val registerModelDTOMapper = UserRegisterModelDTOMapper(mapper)
    private val loginModelDTOMapper = LoginCredentialsModelDTOMapper()
    private val repository: Auth = AuthRepositoryImpl(
        registerModelDTOMapper = registerModelDTOMapper,
        loginModelDTOMapper = loginModelDTOMapper
    )
    private val signUpUseCase = RegisterUseCase(repository)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(
            this, SignUpFactory(signUpUseCase)
        )[SingUpViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)
        binding = SingUpFragmentBinding.bind(view)
        binding!!.goBack.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_welcomeFragment)
        }
        binding!!.male.setOnClickListener {
            gender = GenderModel.male
            maleChanger()
        }
        binding!!.female.setOnClickListener {
            gender = GenderModel.female
            femaleChanger()
        }
        binding!!.enterButton.setOnClickListener {
            if (validationCheck()) {
                register(viewModel)
                findNavController().navigate(R.id.action_singUpFragment_to_feedFragment)
            }
        }
        binding!!.login.makeClearableEditText(null, null)
        binding!!.mail.makeClearableEditText(null, null)
        binding!!.name.makeClearableEditText(null, null)
        binding!!.password.makeClearableEditTextPass(null, null)
        binding!!.passwordVerify.makeClearableEditTextPass(null, null)
        binding!!.enterButton.isClickable = false
        binding!!.birthday.addTextChangedListener { text ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    binding!!.birthday.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null,
                        null,
                        ContextCompat.getDrawable(requireContext(), R.drawable.title_calendar),
                        null
                    )
                }
            }
        }
        binding!!.login.onRightDrawableClicked { it.text.clear() }
        binding!!.mail.onRightDrawableClicked { it.text.clear() }
        binding!!.name.onRightDrawableClicked { it.text.clear() }
        binding!!.password.onRightDrawableClicked { visibilitySwitch(it) }
        binding!!.passwordVerify.onRightDrawableClicked { visibilitySwitch(it) }
        binding!!.birthday.onRightDrawableClicked { callDatePicker(it) }
        binding!!.login.addTextChangedListener { text ->
            if (text != null) {
                singUpAccessCheck()
            }
        }
        binding!!.mail.addTextChangedListener { text ->
            if (text != null) {
                singUpAccessCheck()
            }
        }
        binding!!.name.addTextChangedListener { text ->
            if (text != null) {
                singUpAccessCheck()
            }
        }
        binding!!.password.addTextChangedListener { text ->
            if (text != null) {
                singUpAccessCheck()
            }
        }
        binding!!.passwordVerify.addTextChangedListener { text ->
            if (text != null) {
                singUpAccessCheck()
            }
        }
        binding!!.birthday.addTextChangedListener { text ->
            if (text != null) {
                singUpAccessCheck()
            }
        }
    }

    private fun register(viewModel: SingUpViewModel) {
        viewModel.singUp(
            UserRegisterModel(
                username = binding!!.login.text.toString(),
                name = binding!!.name.text.toString(),
                password = binding!!.password.text.toString(),
                email = binding!!.mail.text.toString(),
                birthday = binding!!.birthday.toString() + "T19:54:29.735Z",
                gender = gender
            )
        )
    }

    private fun singUpAccessCheck() {
        if (binding!!.login.text.isNotEmpty() && binding!!.mail.text.isNotEmpty() && binding!!.name.text.isNotEmpty() && binding!!.password.text.isNotEmpty() && binding!!.passwordVerify.text.isNotEmpty() && binding!!.birthday.text.isNotEmpty()) {
            binding!!.enterButton.background =
                this.context?.let { ContextCompat.getDrawable(it, R.drawable.gradient) }
            binding!!.enterButton.setTextColor(resources.getColor(R.color.White))
            binding!!.enterButton.isClickable = true
        } else {
            binding!!.enterButton.background =
                this.context?.let { ContextCompat.getDrawable(it, R.drawable.shape) }
            binding!!.enterButton.setTextColor(resources.getColor(R.color.Gray_Faded))
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
        } else {
            text.inputType = 129
            text.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.title_eye),
                null
            )
        }
    }

    private fun callDatePicker(it: EditText) {
        val cal = Calendar.getInstance()
        val mYear = cal[Calendar.YEAR]
        val mMonth = cal[Calendar.MONTH]
        val mDay = cal[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view, year, monthOfYear, dayOfMonth ->
                val editTextDateParam = dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year
                it.setText(editTextDateParam)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    private fun maleChanger() {
        if (binding!!.male.background != context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.left_grad
                )
            }) {
            binding!!.male.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.left_grad) }
            binding!!.female.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.right_shape) }
        }
    }

    private fun femaleChanger() {
        if (binding!!.female.background != context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.right_grad
                )
            }) {
            binding!!.male.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.left_shape) }
            binding!!.female.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.right_grad) }
        }
    }

    private fun validationCheck(): Boolean {
        var pass: Boolean = false
        var mail: Boolean = false
        if (binding!!.password.text.toString() == binding!!.passwordVerify.text.toString()) {
            pass = true
            binding!!.passwordVerify.background =
                this.context?.let { ContextCompat.getDrawable(it, R.drawable.shape) }
        } else {
            binding!!.passwordVerify.background =
                this.context?.let { ContextCompat.getDrawable(it, R.drawable.shape_error) }
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding!!.mail.text).matches()) {
            mail = true
            binding!!.mail.background =
                this.context?.let { ContextCompat.getDrawable(it, R.drawable.shape) }
        } else {
            binding!!.mail.background =
                this.context?.let { ContextCompat.getDrawable(it, R.drawable.shape_error) }
        }
        return pass && mail
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}