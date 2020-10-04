package com.example.itjaprojectfinal.worker;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itjaprojectfinal.R;
import com.example.itjaprojectfinal.pojo.DatabaseManager;
import com.example.itjaprojectfinal.pojo.User;



public class TransferWorker extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = TransferWorker.this;
    private DatabaseManager databaseHelper;
    private User user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        initObjects();

        String email = getIntent().getStringExtra("EMAIL");

        user = databaseHelper.getUser(email);

        int balance = user.getMainAccount();
        int savings = user.getSavingsAccount();

        TextView TextAccBalance = (TextView) findViewById(R.id.TextAccBalance2);
        TextView TextAccSavings = (TextView) findViewById(R.id.TextAccSavings2);

        TextAccBalance.setText("Current Account Balance: R" + balance);
        TextAccSavings.setText("Savings Account Balance: R" + savings);

    }

    public void TransferMoney(){
        EditText transferAmountText;
        Spinner accSpinner;
        String transferAmount ;
        String spinnerValue;
        int mainAccount;
        int savingsAccount;

        String email = getIntent().getStringExtra("EMAIL");

        user = databaseHelper.getUser(email);

        transferAmountText = (EditText) findViewById(R.id.transferAmountText);
        accSpinner = (Spinner) findViewById(R.id.accSpinner);

        transferAmount = transferAmountText.getText().toString();
        spinnerValue = accSpinner.getSelectedItem().toString();

        mainAccount = user.getMainAccount();
        savingsAccount = user.getSavingsAccount();



        if (spinnerValue.equals("Current to Savings")){
            mainAccount = mainAccount - Integer.parseInt(transferAmount);
            savingsAccount = savingsAccount + Integer.parseInt(transferAmount);

            user.setMainAccount(mainAccount);
            user.setSavingsAccount(savingsAccount);

            databaseHelper.updateUser(user);

        }

        if (spinnerValue.equals("Savings to Current")){
            mainAccount = mainAccount + Integer.parseInt(transferAmount);
            savingsAccount = savingsAccount - Integer.parseInt(transferAmount);

            user.setMainAccount(mainAccount);
            user.setSavingsAccount(savingsAccount);

            databaseHelper.updateUser(user);

        }

        Toast.makeText(this, "CHANGED", Toast.LENGTH_SHORT).show();


    }

    private void initObjects() {
        databaseHelper = new DatabaseManager(activity);
        user = new User();

    }

    @Override
    public void onClick(View v) {
        TransferMoney();

    }

}
