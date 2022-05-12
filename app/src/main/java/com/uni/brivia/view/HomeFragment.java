package com.uni.brivia.view;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.uni.brivia.R;
import com.uni.brivia.base.BaseFragment;
import com.uni.brivia.databinding.FragmentHomeBinding;
import com.uni.brivia.domain.CountdownHelper;
import com.uni.brivia.viewmodels.HomeViewModel;

import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel homeViewModel;

    private CountDownTimer endOfDayTimer;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    protected void onBind() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getCurrentUser().observe(getViewLifecycleOwner(), userEntity -> {
            if (userEntity != null) {
                mBind.vTitle.setText(getResources().getString(R.string.hello_arg, userEntity.getUserName()));

                if (homeViewModel.canUserPlay(userEntity)) {
                    mBind.vOvalBg.setVisibility(View.VISIBLE);
                    mBind.vGoButton.setVisibility(View.VISIBLE);
                    mBind.vGoText.setVisibility(View.VISIBLE);
                    mBind.vIndicator.setVisibility(View.GONE);
                    mBind.vGoRemainingTimer.setVisibility(View.GONE);
                    mBind.vGoRemainingDescrr.setVisibility(View.GONE);
                } else {
                    mBind.vOvalBg.setVisibility(View.INVISIBLE);
                    mBind.vGoButton.setVisibility(View.INVISIBLE);
                    mBind.vGoText.setVisibility(View.GONE);
                    mBind.vIndicator.setVisibility(View.VISIBLE);
                    mBind.vGoRemainingTimer.setVisibility(View.VISIBLE);
                    mBind.vGoRemainingDescrr.setVisibility(View.VISIBLE);
                }
            }
        });

        startCountdown();

        mBind.vOvalBg.setOnClickListener(v -> {
            if (homeViewModel.canUserPlay(homeViewModel.getCurrentUser().getValue())) {
                // todo:
            }
        });
    }

    @Override
    public void onDestroyView() {
        endOfDayTimer = null;
        super.onDestroyView();
    }

    private void startCountdown() {
        endOfDayTimer = new CountDownTimer(CountdownHelper.millisToTheEndOfDay(), 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                mBind.vGoRemainingTimer.setText(CountdownHelper.formatRemainingMillis(millisUntilFinished));
                mBind.vIndicator.setProgress(CountdownHelper.remainingPercentage(millisUntilFinished), true);
            }

            public void onFinish() {
                Timber.d("Countdown done.");
            }
        }.start();
    }
}
