package com.uni.brivia.view;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.brivia.R;
import com.uni.brivia.core.base.BaseFragment;
import com.uni.brivia.databinding.FragmentHighScoreBinding;
import com.uni.brivia.viewmodels.ScoresViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HighScoreFragment extends BaseFragment<FragmentHighScoreBinding> {

    private ScoreAdapter mScoresAdapter;

    private ScoresViewModel scoresViewModel;

    public HighScoreFragment() {
        super(R.layout.fragment_high_score);
    }

    @Override
    protected void onBind() {
        scoresViewModel = new ViewModelProvider(this).get(ScoresViewModel.class);
        mScoresAdapter = new ScoreAdapter();
        setupRecycler();

        mBind.vFAB.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });

        scoresViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> mScoresAdapter.setProductList(users));
    }

    private void setupRecycler() {
        mBind.vScoresRv.setAdapter(mScoresAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration divider = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        mBind.vScoresRv.setLayoutManager(manager);
        mBind.vScoresRv.addItemDecoration(divider);
    }
}
