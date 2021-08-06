package com.army.saluteindia.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.army.saluteindia.databinding.FragmentLoginBinding
import com.army.saluteindia.data.networklogin.AuthApi
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.AuthRepository
import com.army.saluteindia.home.HomeActivity
import com.army.saluteindia.ui.base.BaseFragment
import com.army.saluteindia.utils.enable
import com.army.saluteindia.utils.startNewActivity
import com.army.saluteindia.utils.visible
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.loading.visible(false)
        binding.login.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.loading.visible(false)
            when(it){
                is Resource.success -> {
                    viewModel.saveAuthToken(it.value.user.access_token!!)
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.username.addTextChangedListener {
            val email = binding.username.text.toString().trim()
            binding.login.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.login.setOnClickListener {
            val email = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            binding.loading.visible(true)
            viewModel.login(email, password)
        }

    }
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}