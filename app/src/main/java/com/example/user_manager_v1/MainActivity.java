package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.user_manager_v1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClick();
    }

    private void onClick() {
        onClickSignIn();
        onClickSignUp();
    }

    private void onClickSignUp() {
        binding.btnSignUp.setOnClickListener(it -> {
            goToSignUp();
        });
    }

    private void onClickSignIn() {
        binding.btnSignIn.setOnClickListener(it -> {
            goToSignIn();
        });
    }


    private void goToSignUp() {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();

    }

    private void goToSignIn() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();

    }
}