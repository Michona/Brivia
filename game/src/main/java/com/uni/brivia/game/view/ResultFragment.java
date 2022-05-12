package com.uni.brivia.game.view;

import android.annotation.SuppressLint;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.uni.brivia.core.base.BaseFragment;
import com.uni.brivia.game.R;
import com.uni.brivia.game.databinding.FragmentResultBinding;
import com.uni.brivia.game.viewmodel.GameViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ResultFragment extends BaseFragment<FragmentResultBinding> {

    /* Shared ViewModel with GameFragment. */
    private GameViewModel viewModel;

    public ResultFragment() {
        super(R.layout.fragment_result);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onBind() {
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        mBind.vContinueButton.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());

        viewModel.result.observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                if (result.getIsCorrect()) {
                    mBind.vPointsAnswer.setText("+" + result.getPoints());
                    mBind.vPointsAnswerExplained.setText(com.uni.brivia.core.R.string.correct_solution);
                    mBind.vLoadingImage.setImageDrawable(requireContext().getResources().getDrawable(R.drawable.ic_success));
                } else {
                    mBind.vPointsAnswer.setText(String.valueOf(result.getPoints()));
                    mBind.vPointsAnswerExplained.setText(com.uni.brivia.core.R.string.incorrect_solution);
                    mBind.vLoadingImage.setImageDrawable(requireContext().getResources().getDrawable(R.drawable.ic_fail));
                }

                mBind.vTryingPoints.setText("+" + result.getBonusPoints());
                mBind.vTryingPointsExplained.setText(com.uni.brivia.core.R.string.just_trying);
            }
        });
    }

    @Override
    public void onDestroyView() {
        viewModel.clear();
        super.onDestroyView();
    }
}
