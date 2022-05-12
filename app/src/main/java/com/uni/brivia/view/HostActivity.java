package com.uni.brivia.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.uni.brivia.R;
import com.uni.brivia.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class HostActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mSignInClient;

    private static final int REQ_ONE_TAP = 14;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_host);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mAuth = FirebaseAuth.getInstance();

        /* Creates sign in options. */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(com.uni.brivia.core.R.string.google_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        mSignInClient = GoogleSignIn.getClient(this, gso);

        loginViewModel.shouldInvokeGoogleSignIn.observe(this, openGoogleSignIn -> {
            if (openGoogleSignIn)
                startActivityForResult(mSignInClient.getSignInIntent(), REQ_ONE_TAP);
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ONE_TAP) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Timber.e(e);
            }
        }
    }

    private void firebaseAuthWithGoogle(@NonNull GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(this, authResult -> {
                    loginViewModel.onSignInSuccess(authResult);
                })
                .addOnFailureListener(this, e -> Toast.makeText(HostActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show());
    }
}
