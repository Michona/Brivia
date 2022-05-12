package com.uni.brivia.game.view;

import com.uni.brivia.core.base.BaseFragment;
import com.uni.brivia.game.R;
import com.uni.brivia.game.databinding.FragmentGameBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameFragment extends BaseFragment<FragmentGameBinding> {
    public GameFragment() {
        super(R.layout.fragment_game);
    }

    @Override
    protected void onBind() {
    }
}
