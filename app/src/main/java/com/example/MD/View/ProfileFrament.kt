package com.example.MD.View

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.MD.Domain.Auth
import com.example.MD.Domain.EditProfileUseCase
import com.example.MD.Domain.GenderModel
import com.example.MD.Domain.GetProfileUseCase
import com.example.MD.Domain.LogoutUseCase
import com.example.MD.Domain.ProfileModel
import com.example.MD.Domain.User
import com.example.MD.R
import com.example.MD.ViewModel.ProfileScreenViewModel
import com.example.MD.ViewModel.ProfileScreenViewModelFactory
import com.example.MD.data.mapper.GenderModelDTOMapper
import com.example.MD.data.mapper.LoginCredentialsModelDTOMapper
import com.example.MD.data.mapper.ProfileModelDTOMapper
import com.example.MD.data.mapper.UserRegisterModelDTOMapper
import com.example.MD.data.repository.AuthRepositoryImpl
import com.example.MD.data.repository.UserRepositoryImpl
import com.example.MD.databinding.ProfileScreenBinding
import kotlinx.coroutines.launch
import java.time.LocalTime

class ProfileFrament : Fragment(R.layout.profile_screen) {
    private var binding: ProfileScreenBinding? = null
    private val mapper = GenderModelDTOMapper()
    private val loginModelDTOMapper = LoginCredentialsModelDTOMapper()
    private val registerModelDTOMapper = UserRegisterModelDTOMapper(mapper)
    private val auth: Auth = AuthRepositoryImpl(registerModelDTOMapper, loginModelDTOMapper)
    private val profileMapper = ProfileModelDTOMapper(mapper)
    private val repository: User = UserRepositoryImpl(profileMapper)
    private val getProfileUseCase = GetProfileUseCase(repository)
    private val editProfileUseCase = EditProfileUseCase(repository)
    private val logoutUseCase = LogoutUseCase(auth)
    private lateinit var avatarUrl:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(
            this,
            ProfileScreenViewModelFactory(getProfileUseCase, editProfileUseCase, logoutUseCase)
        )[ProfileScreenViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileScreenBinding.bind(view)
        binding!!.feedBtn.setOnClickListener {
            findNavController().navigate(ProfileFramentDirections.actionProfileFramentToFeedFragment())
        }
        binding!!.movieBtn.setOnClickListener {
            findNavController().navigate(ProfileFramentDirections.actionProfileFramentToMovieFragment())
        }
        binding!!.logoutBtn.setOnClickListener {
            lifecycleScope.launch { viewModel.logout() }
        }
        binding!!.avatar.setOnClickListener {
            viewModel.profile.value?.let { it1 -> uploadImage(viewModel, it1) }
        }
        observeData(viewModel)
        lifecycleScope.launch { viewModel.getProfile() }
    }

    private fun uploadImage(viewModel: ProfileScreenViewModel, profile: ProfileModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Avatar Link")
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton(
            "OK"
        ) { _, _ ->
            avatarUrl = input.text.toString(); viewModel.uploadImage(
            ProfileModel(
                profile.id,
                profile.nickname,
                profile.email,
                avatarUrl,
                profile.name,
                profile.birthDate,
                profile.gender
            )
        )
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun showProfile(viewModel: ProfileScreenViewModel) {
        binding!!.greetingName.text = viewModel.profile.value!!.name
        binding!!.loginText.setText(viewModel.profile.value!!.nickname)
        binding!!.emailText.setText(viewModel.profile.value!!.email)
        binding!!.nameText.setText(viewModel.profile.value!!.name)
        binding!!.birthDateText.setText(
            viewModel.profile.value!!.birthDate.substring(
                0,
                viewModel.profile.value!!.birthDate.length - 13
            )
        )
        if (viewModel.profile.value!!.gender == GenderModel.male) {
            binding!!.male.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.left_grad)
            binding!!.female.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.right_shape)
        } else {
            binding!!.female.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.right_grad)
            binding!!.male.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.left_shape)
        }
        val now = LocalTime.now()
        if (now.hour >= LocalTime.parse("06:00:00").hour && now.hour <= LocalTime.parse("11:59:00").hour) {
            binding!!.helloText.text = "Доброе утро,"
        } else if (now.hour >= LocalTime.parse("00:00:00").hour && now.hour <= LocalTime.parse("05:59:00").hour) {
            binding!!.helloText.text = "Доброй ночи,"
        } else if (now.hour >= LocalTime.parse("12:00:00").hour && now.hour <= LocalTime.parse("17:59:00").hour) {
            binding!!.helloText.text = "Добрый день,"
        } else {
            binding!!.helloText.text = "Добрый вечер,"
        }
        Glide.with(binding!!.avatar.context).load(Uri.parse(viewModel.profile.value!!.avatarLink)).into(binding!!.avatar)
    }

    private fun observeData(viewModel: ProfileScreenViewModel) {
        lifecycleScope.launch {
            viewModel.profile.collect {
                if (it != null) {
                    showProfile(viewModel)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.updateConfirm.collect{
                viewModel.getProfile()
            }
        }
        lifecycleScope.launch {
            viewModel.logoutTrue.collect{
                if(viewModel.logoutTrue.value){
                    findNavController().navigate(ProfileFramentDirections.actionProfileFramentToWelcomeFragment())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}