package com.example.itjaprojectfinal.worker;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itjaprojectfinal.R;
import com.example.itjaprojectfinal.pojo.DatabaseManager;
import com.example.itjaprojectfinal.pojo.User;

public class ViewAccountActivity extends AppCompatActivity {


    private DatabaseManager databaseHelper;
    private User user;
    private final AppCompatActivity activity = ViewAccountActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewaccountbalance);
        initObjects();


        String email = getIntent().getStringExtra("EMAIL");

        user = databaseHelper.getUser(email);
        String name = user.getFirstName();
        String surname = user.getLastName();
        int balance = user.getMainAccount();
        int savings = user.getSavingsAccount();

        TextView TextAccName = (TextView) findViewById(R.id.TextAccName);
        TextView TextAccSurname = (TextView) findViewById(R.id.TextAccSurname);
        TextView TextAccBalance = (TextView) findViewById(R.id.TextAccBalance);
        TextView TextAccSavings = (TextView) findViewById(R.id.TextAccSavings);

        TextAccName.setText("Account Holder Name: " + name);
        TextAccSurname.setText("Account Holder Surname: " + surname);
        TextAccBalance.setText("Current Account Balance: R" + balance);
        TextAccSavings.setText("Savings Account Balance: R" + savings);


    }

    private void initObjects() {

        databaseHelper = new DatabaseManager(activity);
        user = new User();
    }
}
