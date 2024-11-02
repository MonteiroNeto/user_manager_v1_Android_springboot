package com.example.user_manager_v1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user_manager_v1.databinding.ActivitySignInBinding;
import com.example.user_manager_v1.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.jar.JarException;

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
        onClickLogin();
    }

    private void onClickLogin() {
        binding.btnSignIn.setOnClickListener(it -> {
            authenticateUser();
        });
    }


    private void onCLickDontHaveAccount() {
        binding.tvDontHaveAccountSignIn.setOnClickListener(it -> {
            goToSignUp();
        });
    }

    private void goToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();

    }


    private void onCLickBackHome() {
        binding.btnBackHomeSignIn.setOnClickListener(it -> {
            goToHome();
        });
    }


    private void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void authenticateUser() {
        if (!validateEmail() || !validatePassword()) {
            return;
        }

        //Instantiate the Request Queue:
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.206:8080/api/v1/user/login";

        //Set Parameters
        HashMap<String, String> params = new HashMap<>();
        params.put("email", binding.edtEmailSignIn.getText().toString());
        params.put("password", binding.edtPassSignIn.getText().toString());

        //Set request Object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Get values from response object
                    String firstName = (String) response.get("firstName");
                    String lastName = (String) response.get("lastName");
                    String email = (String) response.get("email");

                    String userDataString = firstName + "\n" + lastName + "\n" + email;

                    Intent intentGoToProfile = new Intent(SignInActivity.this, ProfileActivity.class);
                    intentGoToProfile.putExtra("data", userDataString);

                    startActivity(intentGoToProfile);
                    //finish();

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                    Log.e("Error Login", "*******Error login: " + jsonException.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("Error Login", "*******Error login: " + error.getMessage());
                Toast.makeText(SignInActivity.this, "Login Failed:\n" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //Connect to springBoot
        queue.add(jsonObjectRequest);

    }

    private boolean validateEmail() {
        String email = binding.edtEmailSignIn.getText().toString();

        if (email.isBlank() || email.isEmpty()) {
            binding.edtEmailSignIn.setError("Email cannot be empty");
            return false;
        } else if (!StringHelper.regexEmailValidationPattern(email)) {
            binding.edtEmailSignIn.setError("Please enter a valid email");
            return false;
        }
        {
            binding.edtEmailSignIn.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String password = binding.edtPassSignIn.getText().toString();


        if (password.isBlank() || password.isEmpty()) {
            binding.edtPassSignIn.setError("Password cannot be empty");

            return false;
        } else {
            binding.edtPassSignIn.setError(null);
            return true;
        }

    }

}