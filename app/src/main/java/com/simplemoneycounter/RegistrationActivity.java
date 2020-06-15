package com.simplemoneycounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.simplemoneycounter.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialEditText metEmail, metPassword, metConfirmPassword;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDB;

    private final String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = metEmail.getText().toString();
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if (TextUtils.isEmpty(email)) {
            metEmail.setError("Required.");
            valid = false;
        } else if (!mat.matches()) {
            metEmail.setError("Invalid email.");
            valid = false;
        } else {
            metEmail.setError(null);
        }

        String password = metPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            metPassword.setError("Required.");
            valid = false;
        } else if (password.length() < 7) {
            metPassword.setError("Password is too short.");
            valid = false;
        } else {
            metPassword.setError(null);
        }

        String confirmPassword = metConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(confirmPassword)) {
            metConfirmPassword.setError("Required.");
            valid = false;
        } else if (confirmPassword.length() < 7) {
            metConfirmPassword.setError("Password is too short.");
            valid = false;
        } else if (!password.equals(confirmPassword)) {
            metConfirmPassword.setError("Passwords aren't the same.");
            valid = false;
        } else {
            metConfirmPassword.setError(null);
        }


        return valid;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            createAccount();
        }
    }

    private void createAccount() {
    }

    private void init() {

        metEmail = findViewById(R.id.met_emailField);
        metPassword = findViewById(R.id.met_password);
        metConfirmPassword = findViewById(R.id.met_confirmPassword);

        findViewById(R.id.btn_submit).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mUserDB= FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
}
