package com.simplemoneycounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialEditText etEmail, etPassword;

    private FirebaseAuth mAuth;

    private DatabaseReference mUserDB;

    private final String USER_KEY = "User";

    Intent returnFromRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void clickOnLoginBottom(String email, String password) {
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent goToHomeActivity = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(goToHomeActivity);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required.");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required.");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.btn_login) {

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            clickOnLoginBottom(email, password);

        } else if (i == R.id.tv_register) {
            Intent goToRegistration = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(goToRegistration);
        }
    }

    private void init() {
        etEmail = findViewById(R.id.met_emailLoginField);
        etPassword = findViewById(R.id.met_LoginLogInField);

        returnFromRegistration = getIntent();
        if (returnFromRegistration != null) {
            etEmail.setText(returnFromRegistration.getStringExtra("email"));
            etPassword.setText(returnFromRegistration.getStringExtra("password"));
        }

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mUserDB = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
}
