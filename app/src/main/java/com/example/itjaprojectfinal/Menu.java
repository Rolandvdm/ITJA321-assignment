package com.example.itjaprojectfinal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itjaprojectfinal.pojo.User;
import com.example.itjaprojectfinal.DatabaseManager;
import com.example.itjaprojectfinal.worker.RegisterWorker;
import com.example.itjaprojectfinal.worker.TransferWorker;
import com.example.itjaprojectfinal.worker.ViewAccountActivity;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = Menu.this;
    private DatabaseManager databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initObjects();
        Intent intent = getIntent();

        /*
        Using email as its a unique identifier
         */
        String email = getIntent().getStringExtra("EMAIL");
        Toast toast=Toast.makeText(getApplicationContext(),"Logged in with email: " + email,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();



    }



    public void onClick(View v) {

        String email = getIntent().getStringExtra("EMAIL");
        Intent accountsIntent = new Intent(activity, ViewAccountActivity.class);
        accountsIntent.putExtra("EMAIL", email );


        startActivity(accountsIntent);


    }

    public void onClickTransfer(View v) {

        String email = getIntent().getStringExtra("EMAIL");
        Intent accountsIntent = new Intent(activity, TransferWorker.class);
        accountsIntent.putExtra("EMAIL", email );


        startActivity(accountsIntent);


    }


    private void initObjects() {

        databaseHelper = new DatabaseManager(activity);
    }

}
