package com.uni.brivia.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    public final MutableLiveData<Boolean> mObservableProduct;

    @Inject
    public LoginViewModel() {
        mObservableProduct = new MutableLiveData<>(false);
    }

    public void onContinueWithGoogle() {
        mObservableProduct.postValue(true);
    }
}
