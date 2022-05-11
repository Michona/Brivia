package com.uni.brivia.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import timber.log.Timber;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    public final MutableLiveData<Boolean> shouldInvokeGoogleSignIn;

    public final MutableLiveData<Boolean> navigateHome;

    @Inject
    public LoginViewModel() {
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
