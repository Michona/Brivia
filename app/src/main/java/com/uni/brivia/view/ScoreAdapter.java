package com.uni.brivia.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.uni.brivia.R;
import com.uni.brivia.databinding.ItemScoreBinding;
import com.uni.brivia.db.entity.UserEntity;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    List<? extends UserEntity> mUsersList;

    private final String mUserId;

    public ScoreAdapter() {
        setHasStableIds(true);
        mUserId = FirebaseAuth.getInstance().getUid();
    }

    public void setProductList(final List<? extends UserEntity> usersList) {
        if (mUsersList == null) {
            mUsersList = usersList;
            notifyItemRangeInserted(0, usersList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mUsersList.size();
                }

                @Override
                public int getNewListSize() {
                    return usersList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mUsersList.get(oldItemPosition).getId().equals(usersList.get(newItemPosition).getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    UserEntity newProduct = usersList.get(newItemPosition);
                    UserEntity oldProduct = mUsersList.get(oldItemPosition);
                    return newProduct.getId().equals(oldProduct.getId());
                }
            });
            mUsersList = usersList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    @NonNull
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScoreBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_score,
                        parent, false);
        return new ScoreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        UserEntity entity = mUsersList.get(position);

        if (entity.getId().equals(mUserId)) {
            holder.binding.vName.setText(R.string.you);
            holder.binding.vName.setTextColor(holder.binding.getRoot().getContext().getResources().getColor(R.color.red));
        } else {
            holder.binding.vName.setText(entity.getUserName());
            holder.binding.vName.setTextColor(holder.binding.getRoot().getContext().getResources().getColor(R.color.black));
        }
        holder.binding.vScore.setText(String.valueOf(entity.getScore()));

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mUsersList == null ? 0 : mUsersList.size();
    }

    @Override
    public long getItemId(int position) {
        return mUsersList.get(position).getId().hashCode();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        final ItemScoreBinding binding;

        public ScoreViewHolder(ItemScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
