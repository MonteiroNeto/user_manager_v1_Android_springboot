package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user_manager_v1.databinding.ActivitySignUpBinding;
import com.example.user_manager_v1.helpers.StringHelper;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClick();
    }


    private void onClick() {
        onCLickBackHome();
        onCLickHaveAccount();
        onClickSignUp();
    }

    private void onClickSignUp() {
        binding.btnSignUp.setOnClickListener(it -> {
            processFormField();
        });
    }

    private void onCLickHaveAccount() {
        binding.tvHaveAccountSignUp.setOnClickListener(it -> {
            goToSignIn();
        });
    }

    private void goToSignIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();

    }

    private void onCLickBackHome() {
        binding.btnBackHomeSignUp.setOnClickListener(it -> {
            goToHome();
        });
    }


    private void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //*****************************************+FIELDS**********************************************
    private void processFormField() {

        if (!validateFirstName() || !validateLastName() || !validateEmail() || !validatePassword()) {
            return;
        }

        //Instantiate the Request Queue:
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.206:8080/api/v1/user/register";


        //String Request Object:
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("success")) {
                    binding.edtFirstNameSignUp.setText(null);
                    binding.edtLastNameSignUp.setText(null);
                    binding.edtEmailSignUp.setText(null);
                    binding.edtPasswordSignUp.setText(null);
                    binding.edtConfirmPasswordSignUp.setText(null);
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Registration ERROR", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this, "Registration ERROR\n\n" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstName",binding.edtFirstNameSignUp.getText().toString());
                params.put("lastName",binding.edtLastNameSignUp.getText().toString());
                params.put("email",binding.edtEmailSignUp.getText().toString());
                params.put("password",binding.edtPasswordSignUp.getText().toString());

                return params;
            }
        };

        //End Of Response if block.

        //Start process request
        queue.add(stringRequest);

    }


    private boolean validateFirstName() {
        String firstName = binding.edtFirstNameSignUp.getText().toString();

        if (firstName.isBlank() || firstName.isEmpty()) {
            binding.edtFirstNameSignUp.setError("First name cannot be empty");

            return false;
        } else {
            binding.edtFirstNameSignUp.setError(null);
            return true;
        }

    }

    private boolean validateLastName() {
        String lastName = binding.edtLastNameSignUp.getText().toString();

        if (lastName.isBlank() || lastName.isEmpty()) {
            binding.edtLastNameSignUp.setError("Last name cannot be empty");

            return false;
        } else {
            binding.edtLastNameSignUp.setError(null);
            return true;
        }

    }

    private boolean validateEmail() {
        String email = binding.edtEmailSignUp.getText().toString();

        if (email.isBlank() || email.isEmpty()) {
            binding.edtEmailSignUp.setError("Email cannot be empty");
            return false;
        } else if (!StringHelper.regexEmailValidationPattern(email)) {
            binding.edtEmailSignUp.setError("Please enter a valid email");
            return false;
        }
        {
            binding.edtEmailSignUp.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String password = binding.edtPasswordSignUp.getText().toString();
        String confirmPassword = binding.edtConfirmPasswordSignUp.getText().toString();

        if (password.isBlank() || password.isEmpty()) {
            binding.edtPasswordSignUp.setError("Password cannot be empty");

            return false;
        } else if (confirmPassword.isBlank() || confirmPassword.isEmpty()) {
            //
            binding.edtPasswordSignUp.setError(null);
            binding.edtConfirmPasswordSignUp.setError("Confirm Password cannot be empty");
            return false;

        } else if (!password.equals(confirmPassword)) {
            binding.edtConfirmPasswordSignUp.setError("password is different");
            return false;

        } else {
            binding.edtPasswordSignUp.setError(null);
            binding.edtConfirmPasswordSignUp.setError(null);
            return true;
        }

    }
}


























