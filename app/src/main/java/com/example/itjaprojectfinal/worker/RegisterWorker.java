package com.example.itjaprojectfinal.worker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.itjaprojectfinal.DatabaseManager;
import com.example.itjaprojectfinal.InputValidation;
import com.example.itjaprojectfinal.R;
import com.example.itjaprojectfinal.pojo.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;

import androidx.core.widget.NestedScrollView;


public class RegisterWorker extends AppCompatActivity implements View.OnClickListener{



    private final AppCompatActivity activity = RegisterWorker.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutSurname;
    private TextInputLayout textInputLayoutMobile;
    private TextInputLayout textInputLayoutGender;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextSurname;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextMobile;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextGender;
    private TextInputEditText textInputEditTextConfirmPassword;
    private InputValidation inputValidation;
    private DatabaseManager databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initObjects();

    }
    private void initViews() {

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutMobile = (TextInputLayout) findViewById(R.id.textInputLayoutMobile);
        textInputLayoutSurname = (TextInputLayout) findViewById(R.id.textInputLayoutSurname);
        textInputLayoutGender = (TextInputLayout) findViewById(R.id.textInputLayoutGender);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextSurname = (TextInputEditText) findViewById(R.id.textInputEditTextSurname);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextMobile = (TextInputEditText) findViewById(R.id.textInputEditTextMobile);
        textInputEditTextGender = (TextInputEditText) findViewById(R.id.textInputEditTextGender);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditConfirmPassword);

    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseManager(activity);
        user = new User();
    }



    private void postDataToSQLite() {
        try{
            if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, "ERROR")) {
                return;
            }

            if (!inputValidation.isInputEditTextFilled(textInputEditTextSurname, textInputLayoutSurname, "ERROR")) {
                return;
            }

            if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "ERROR")) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextMobile, textInputLayoutMobile, "ERROR")) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextGender, textInputLayoutGender, "ERROR")) {
                return;
            }

            if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "ERRORPassword")) {
                System.out.println("text is empty");
            }
            if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                    textInputLayoutConfirmPassword, "Passwords dont match")) {
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            user.setFirstName(textInputEditTextName.getText().toString().trim());
            user.setLastName(textInputEditTextSurname.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setMobile(textInputEditTextMobile.getText().toString().trim());
            user.setGender(textInputEditTextGender.getText().toString().trim());
            databaseHelper.addUser(user);
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, "Success!", Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, "Email is already used", Snackbar.LENGTH_LONG).show();
        }
    }
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextGender.setText(null);
        textInputEditTextMobile.setText(null);
        textInputEditTextSurname.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }





    @Override
    public void onClick(View v) {
        postDataToSQLite();
    }
}
