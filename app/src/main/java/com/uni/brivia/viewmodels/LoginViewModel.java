package com.uni.brivia.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.uni.brivia.core.api.IAuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final IAuthRepository mAuthRepository;

    public final MutableLiveData<Boolean> shouldInvokeGoogleSignIn;

    public final MutableLiveData<Boolean> navigateHome;

    @Inject
    LoginViewModel(IAuthRepository authRepository) {
        this.mAuthRepository = authRepository;

        shouldInvokeGoogleSignIn = new MutableLiveData<>(false);
        navigateHome = new MutableLiveData<>(false);
    }

    public void onSignInSuccess(@NonNull AuthResult result) {
        mAuthRepository.createNewUser(result);
        navigateHome.postValue(true);
    }

    public void onContinueWithGoogle() {
        shouldInvokeGoogleSignIn.postValue(true);
    }
}
