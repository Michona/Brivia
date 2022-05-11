package com.uni.brivia.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.uni.brivia.domain.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final AuthRepository mAuthRepository;

    public final MutableLiveData<Boolean> shouldInvokeGoogleSignIn;

    public final MutableLiveData<Boolean> navigateHome;

    @Inject
    LoginViewModel(AuthRepository authRepository) {
        this.mAuthRepository = authRepository;

        shouldInvokeGoogleSignIn = new MutableLiveData<>(false);
        navigateHome = new MutableLiveData<>(false);

        // TODO: check if we are already authenticated
        if (FirebaseAuth.getInstance().getUid() != null) {
            navigateHome.postValue(true);
        }
    }

    public void onSignInSuccess(@NonNull AuthResult result) {
        // TODO: deal with result
        navigateHome.postValue(true);
    }

    public void onContinueWithGoogle() {
        shouldInvokeGoogleSignIn.postValue(true);
    }
}
