package com.uni.brivia.game.view;

import android.os.CountDownTimer;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.uni.brivia.core.api.IGameRepository;
import com.uni.brivia.core.base.BaseFragment;
import com.uni.brivia.game.R;
import com.uni.brivia.game.databinding.FragmentGameBinding;
import com.uni.brivia.game.viewmodel.GameViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameFragment extends BaseFragment<FragmentGameBinding> {


    /* Shared ViewModel with ResultFragment. */
    private GameViewModel viewModel;

    private CountDownTimer endOfGameTimer;

    public GameFragment() {
        super(R.layout.fragment_game);
    }

    @Override
    protected void onBind() {
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        setupTimer();

        viewModel.result.observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                NavHostFragment.findNavController(this).navigate(R.id.action_gameFragment_to_resultFragment);
            }
        });

        setupAnswers();
    }

    @Override
    public void onDestroyView() {
        endOfGameTimer.cancel();
        endOfGameTimer = null;
        super.onDestroyView();
    }

    private void setupAnswers() {
        mBind.vFirstAnswer.setOnClickListener(view -> viewModel.onAnswerClicked(0));
        mBind.vSecondAnswer.setOnClickListener(view -> viewModel.onAnswerClicked(1));
        mBind.vThirdAnswer.setOnClickListener(view -> viewModel.onAnswerClicked(2));
    }

    private void setupTimer() {
        endOfGameTimer = new CountDownTimer(IGameRepository.TIME_TO_PLAY, 1000) {
            public void onTick(long millisUntilFinished) {
                mBind.vProgress.setProgress(100 - (int) ((double) millisUntilFinished / IGameRepository.TIME_TO_PLAY * 100), true);
            }

            public void onFinish() {
                viewModel.timeUp();
            }
        }.start();
    }
}
