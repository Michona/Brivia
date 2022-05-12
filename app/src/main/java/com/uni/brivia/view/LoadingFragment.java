package com.uni.brivia.view;

import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.uni.brivia.R;
import com.uni.brivia.core.base.BaseFragment;
import com.uni.brivia.databinding.FragmentLoginBinding;

public class LoadingFragment extends BaseFragment<FragmentLoginBinding> {
    public LoadingFragment() {
        super(R.layout.fragment_loading);
    }

    @Override
    protected void onBind() {
        if (FirebaseAuth.getInstance().getUid() != null) {
            NavHostFragment.findNavController(this).navigate(R.id.action_loadingFragment_to_homeFragment);
        } else {
            NavHostFragment.findNavController(this).navigate(R.id.action_loadingFragment_to_loginFragment);
        }
    }
}
