package com.uni.brivia.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

abstract public class BaseFragment<DataBindingType extends ViewDataBinding> extends Fragment {

    /**
     * The ViewDataBinding. Its initialized in onCreateView().
     * Use it to access the generated view classes.
     */
    protected DataBindingType mBind;

    @LayoutRes
    private final int mLayoutId;

    public BaseFragment(int layoutId) {
        this.mLayoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBind = DataBindingUtil.inflate(inflater, mLayoutId, container, false);

        return mBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBind();
    }

    /**
     * Apply and pass all the argument to the DataBinding of the fragment
     */
    abstract protected void onBind();

    @Override
    public void onDestroyView() {
        mBind = null;
        super.onDestroyView();
    }
}
