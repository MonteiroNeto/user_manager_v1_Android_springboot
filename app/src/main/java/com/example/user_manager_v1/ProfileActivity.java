package com.example.user_manager_v1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.user_manager_v1.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponent();
    }

    private void initComponent() {
        String userData = getIntent().getStringExtra("data");
        binding.tvUserDataProfileAct.setText(userData);


        onClick();
    }

    private void onClick() {
        binding.btnBackHomeProfileAct.setOnClickListener(it->{
            finish();
        });
    }
}