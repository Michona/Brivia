package com.uni.brivia.view;

import androidx.lifecycle.ViewModelProvider;

import com.uni.brivia.R;
import com.uni.brivia.base.BaseFragment;
import com.uni.brivia.databinding.FragmentLoginBinding;
import com.uni.brivia.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    private LoginViewModel loginViewModel;

    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    protected void onBind() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mBind.continueWithGoogle.setOnClickListener(v -> {
            loginViewModel.onContinueWithGoogle();
        });

        loginViewModel.mObservableProduct.observe(getViewLifecycleOwner(), product -> {
            Timber.d(product.toString());
        });
    }
}
