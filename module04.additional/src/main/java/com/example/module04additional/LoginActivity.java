package com.example.module04additional;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Перемнож матрицы любой размерности.
 * Покажи примеры работы с gravity/weight.
 * При удачном логине выведи пользователя на список сущностей из твоего приложения.
 * Сущность выбрать на свое усмотрение.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";

    private static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private EditText mEmail;
    private EditText mPassword;
    private Button mCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged: " + user.getEmail());
                    String email = user.getEmail();
                    mEmail.setText(email);
                    enterTheProgram();
                } else {
                    Log.d(TAG, "onAuthStateChanged: sign_out");
                }
            }
        };

        mEmail = (EditText) findViewById(R.id.mainedit_email);
        mPassword = (EditText) findViewById(R.id.mainedit_password);
        mCreateAccount = (Button) findViewById(R.id.mainbutton_create_account);
        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        Button logIn = (Button) findViewById(R.id.mainbutton_log_in);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        TextView mDontHaveAccount = (TextView) findViewById(R.id.maintext_dont_have_account);
        mDontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.addAuthStateListener(mAuthStateListener);
        }
    }

    private void createAccount(String email, String password) {
        if (!validForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Snackbar.make(mCreateAccount, getString(R.string.cannot_be_created), Snackbar.LENGTH_SHORT).show();
                        } else {
                            enterTheProgram();
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        if (!validForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Snackbar.make(mCreateAccount, getString(R.string.wrong_data), Snackbar.LENGTH_SHORT).show();
                        } else {
                            enterTheProgram();
                        }
                    }
                });
    }

    public static void signOut() {
        mAuth.signOut();
    }

    private boolean validForm() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError(getString(R.string.required));
            valid = false;
        } else if(!email.contains("@")) {
            mEmail.setError(getString(R.string.wrong_email));
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.required));
            valid = false;
        } else if (password.length() < 6) {
            mPassword.setError(getString(R.string.too_short));
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }

    private void enterTheProgram() {
        startActivity(new Intent(this, MatrixActivity.class));
    }

    private void click(View view) {
        switch (view.getId()) {
            case R.id.maintext_dont_have_account:
                findViewById(R.id.mainframe_login).setVisibility(View.GONE);
                mCreateAccount.setVisibility(View.VISIBLE);
                break;
            case R.id.mainbutton_create_account:
                createAccount(mEmail.getText().toString(), mPassword.getText().toString());
                mCreateAccount.setVisibility(View.GONE);
                findViewById(R.id.mainframe_login).setVisibility(View.VISIBLE);
                break;
            case R.id.mainbutton_log_in:
                signIn(mEmail.getText().toString(), mPassword.getText().toString());

            default:
                break;

        }
    }

}
