package com.uni.brivia.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uni.brivia.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HostActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_host);
    }
}
