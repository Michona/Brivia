package com.uni.brivia.view;

import com.uni.brivia.R;
import com.uni.brivia.base.BaseFragment;
import com.uni.brivia.databinding.FragmentHomeBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    protected void onBind() {
    }
}
