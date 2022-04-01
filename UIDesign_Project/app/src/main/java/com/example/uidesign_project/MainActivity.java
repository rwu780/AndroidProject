package com.example.uidesign_project;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static String ACCOUNT_MANAGEMENT_KEY = "MAIN_ACCOUNT_MANAGEMENT";

    private Button createAccountButton;
    private AccountManagement accountManagement;
//
//    ActivityResultLauncher<Intent> getContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode() == Activity.RESULT_OK){
//                        Intent data = result.getData();
//                        accountManagement = data.getParcelableExtra(ACCOUNT_MANAGEMENT_KEY);
//                    }
//                }
//            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountManagement = AccountManagement.getInstance();


        createAccountButton = findViewById(R.id.btn_mainActivity_create_account);
        createAccountButton.setOnClickListener(view -> {
            Intent createAccountIntent = new Intent(MainActivity.this, CreateAccountActivity.class);
//            createAccountIntent.putExtra(ACCOUNT_MANAGEMENT_KEY, accountManagement);
//            getContent.launch(createAccountIntent);
            startActivity(createAccountIntent);

        });
    }
}