package com.example.uidesign_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAccountButton = findViewById(R.id.btn_create_account);
        createAccountButton.setOnClickListener(view -> {
           Intent createAccountIntent = new Intent(MainActivity.this, CreateAccountActivity.class);

           startActivity(createAccountIntent);

        });
    }
}