package com.uni.brivia.view;

import androidx.lifecycle.ViewModelProvider;

import com.uni.brivia.R;
import com.uni.brivia.base.BaseFragment;
import com.uni.brivia.databinding.FragmentHomeBinding;
import com.uni.brivia.viewmodels.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel homeViewModel;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    protected void onBind() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getCurrentUser().observe(getViewLifecycleOwner(), userEntity -> {
            if (userEntity != null) {
                mBind.vTitle.setText(getResources().getString(R.string.hello_arg, userEntity.getUserName()));
            }
        });

        mBind.vOvalBg.setOnClickListener(v -> {});
    }
}
