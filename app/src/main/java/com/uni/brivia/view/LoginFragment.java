package com.uni.brivia.view;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.uni.brivia.R;
import com.uni.brivia.base.BaseFragment;
import com.uni.brivia.databinding.FragmentLoginBinding;
import com.uni.brivia.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    private LoginViewModel loginViewModel;

    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    protected void onBind() {
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        mBind.continueWithGoogle.setOnClickListener(view -> loginViewModel.onContinueWithGoogle());

        loginViewModel.navigateHome.observe(getViewLifecycleOwner(), shouldNavigate -> {
            if (shouldNavigate) {
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });
    }
}
