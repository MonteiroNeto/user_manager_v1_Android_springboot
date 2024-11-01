package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.user_manager_v1.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {


    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        onClick();
    }

    private void onClick() {
        onCLickDontHaveAccount();
        onCLickBackHome();
    }


    private void onCLickDontHaveAccount() {
        binding.tvDontHaveAccountSignIn.setOnClickListener(it->{
            goToSignUp();
        });
    }

    private void goToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();

    }


    private void onCLickBackHome() {
        binding.btnBackHomeSignIn.setOnClickListener(it->{
            goToHome();
        });
    }


    private void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}