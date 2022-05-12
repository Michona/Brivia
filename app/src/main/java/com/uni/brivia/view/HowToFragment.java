package com.uni.brivia.view;

import com.uni.brivia.R;
import com.uni.brivia.core.base.BaseFragment;
import com.uni.brivia.databinding.FragmentHowToBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HowToFragment extends BaseFragment<FragmentHowToBinding> {
    public HowToFragment() {
        super(R.layout.fragment_how_to);
    }

    @Override
    protected void onBind() {
    }
}
