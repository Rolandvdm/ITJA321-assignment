package com.example.itjaprojectfinal.worker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.itjaprojectfinal.R;
import com.example.itjaprojectfinal.pojo.DatabaseManager;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private InputValidation inputValidation;
    private DatabaseManager databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
    }



    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseManager(activity);
        inputValidation = new InputValidation(activity);

    }


    public void onClickRegister(View v) {

        Intent intentRegister = new Intent(getApplicationContext(), RegisterWorker.class);
        startActivity(intentRegister);
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "empty")) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Not a valid email")) {
            System.out.println(textInputEditTextEmail + "" + textInputLayoutEmail);
            Toast toast=Toast.makeText(getApplicationContext(),"Email is invalid!",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "password is invalid")) {
            Toast toast=Toast.makeText(getApplicationContext(),"Password is invalid",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, Menu.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());

            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Toast message to show message that record is wrong
            Toast toast=Toast.makeText(getApplicationContext(),"Error logging you in",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }



    @Override
    public void onClick(View v) {
        verifyFromSQLite();

    }
}


