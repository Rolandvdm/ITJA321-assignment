package com.example.itjaprojectfinal.worker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.example.itjaprojectfinal.R;
import com.example.itjaprojectfinal.pojo.DatabaseManager;
import com.example.itjaprojectfinal.pojo.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;



public class RegisterWorker extends AppCompatActivity implements View.OnClickListener{



    private final AppCompatActivity activity = RegisterWorker.this;
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
                Toast toast=Toast.makeText(getApplicationContext(),"Email is invalid",Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextMobile, textInputLayoutMobile, "ERROR")) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextGender, textInputLayoutGender, "ERROR")) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "ERRORPassword")) {
                Toast toast=Toast.makeText(getApplicationContext(),"Password is invalid",Toast.LENGTH_SHORT);
                toast.show();
                System.out.println("text is empty");
            }
            if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                    textInputLayoutConfirmPassword, "Passwords don't match")) {
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
            user.setMainAccount(10000);
            user.setSavingsAccount(5000);
            databaseHelper.addUser(user);

            Toast toast=Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(activity, MainActivity.class);
            startActivity(intent);
            emptyInputEditText();
        } else {

            Toast toast=Toast.makeText(getApplicationContext(),"Email is already used",Toast.LENGTH_SHORT);
            toast.show();
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

    public void onClickLoginPage(View v){

        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);

    }


    @Override
    public void onClick(View v) {

        postDataToSQLite();
    }
}
