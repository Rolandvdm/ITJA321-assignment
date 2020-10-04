package com.example.itjaprojectfinal.worker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itjaprojectfinal.R;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = Menu.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
        Using email as its a unique identifier
         */

        String email = getIntent().getStringExtra("EMAIL");
        Toast toast=Toast.makeText(getApplicationContext(),"Logged in with email: " + email,Toast.LENGTH_SHORT);
        toast.show();



    }

    public void onClick(View v) {

        String email = getIntent().getStringExtra("EMAIL");
        Intent accountsIntent = new Intent(activity, ViewAccountActivity.class);
        accountsIntent.putExtra("EMAIL", email );
        startActivity(accountsIntent);

    }

    public void onClickLogout(View v){

        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);

    }


    public void onClickTransfer(View v) {

        String email = getIntent().getStringExtra("EMAIL");
        Intent accountsIntent = new Intent(activity, TransferWorker.class);
        accountsIntent.putExtra("EMAIL", email );
        startActivity(accountsIntent);


    }




}
