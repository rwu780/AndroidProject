package com.example.uidesign_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.accounts.AccountManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";

    private EditText et_email;
    private EditText et_create_password;
    private EditText et_repeat_password;
    private Button account_submit_button;
    private AccountManagement accountManager;

    private final String PASSWORD_DO_NOT_MATCH = "Your passwords don't match";
    private final String PASSWORD_INVALID = "One of the passwords you entered wasn't valid";
    private final String EMAIL_INVALID = "An account already exists for this email address";
    private final String EMAIL_INVALID_FORMAT = "Your emails doesn't in the correct format";

    private boolean isEmailValid = false;
    private boolean isPasswordValid = false;

    private final int validColor = Color.GREEN;
    private final int inValidColor = Color.RED;
    private final int backgroundColor = Color.WHITE;
    private final int defaultWidth = 5;

    private final int INVALID_EMAIL_CONTAINER = R.id.container_invalid_email;
    private final int INVALID_PWD_CONTAINER = R.id.container_invalid_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initView();

        Intent intent = getIntent();
        Parcelable obj = intent.getParcelableExtra(MainActivity.ACCOUNT_MANAGEMENT_KEY);

        if( obj instanceof AccountManagement){
            accountManager = (AccountManagement) obj;
        }
        else{
            accountManager = new AccountManagement();
        }

        registerButtonListener();
        registerEmailListener();
        registerPasswordListener();

    }

    private void registerPasswordListener() {

        et_repeat_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String rp_pwd = charSequence.toString();
                String pwd = et_create_password.getText().toString().trim();
                checkPassword(pwd, rp_pwd);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        et_create_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pwd = charSequence.toString().trim();
                String rp_pwd = et_repeat_password.getText().toString().trim();
                checkPassword(pwd, rp_pwd);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    private void registerEmailListener() {
        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = charSequence.toString();
                if(accountManager.isAccountExist(email)){
                    isEmailValid = false;
                    showInvalidEmail(EMAIL_INVALID);
                }
                else if (!Account.isValidEmail(email)){
                    isEmailValid = false;
                    showInvalidEmail(EMAIL_INVALID_FORMAT);
                }
                else{
                    isEmailValid = true;
                    showValidEmail();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    private void registerButtonListener() {
        account_submit_button.setOnClickListener(
                view -> {
                    if(isEmailValid && isPasswordValid){
                        String user_email = et_email.getText().toString().trim();
                        String password = et_create_password.getText().toString().trim();

                        accountManager.add(new Account(user_email, password));

                        Intent data = new Intent();
                        data.putExtra(MainActivity.ACCOUNT_MANAGEMENT_KEY, accountManager);
                        setResult(RESULT_OK, data);

                        finish();
                        toast_message("Account created successfully");
                    }
                    else
                        toast_message("You need to fix the errors");
                });
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        et_email = findViewById(R.id.et_email_addr);
        et_create_password = findViewById(R.id.et_create_password);
        et_repeat_password = findViewById(R.id.et_repeat_password);
        account_submit_button = findViewById(R.id.btn_create_account_submit);
    }

    private void showValidEmail(){
        Drawable icon = getDrawable(R.drawable.tick);

        et_email.setBackground(getShare(validColor, Color.WHITE, defaultWidth));
        hideErrorContainer(INVALID_EMAIL_CONTAINER);
        et_email.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
    }

    private void showInvalidEmail(String msg){

        et_email.setBackground(getShare(inValidColor, backgroundColor, defaultWidth));
        showErrorContainer(INVALID_EMAIL_CONTAINER, msg);
        et_email.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);

    }

    private void checkPassword(String pwd, String rp_pwd){
        Log.d(TAG, "checkPassword: " + pwd + "; " + rp_pwd);
        if(!Account.isValidPassword(pwd) || !Account.isValidPassword(rp_pwd)){
            showInvalidPassword(PASSWORD_INVALID);
            isPasswordValid = false;
        }
        else if (!Account.checkRepeatPassword(pwd, rp_pwd)){
            showInvalidPassword(PASSWORD_DO_NOT_MATCH);
            isPasswordValid = false;
        }
        else{
            isPasswordValid = true;
            showValidPassword();
        }
    }

    private void toast_message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private GradientDrawable getShare(int BorderColor, int fillColor, int width){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setStroke(width, BorderColor);
        return gd;
    }

    private void showInvalidPassword(String msg){

        et_create_password.setBackground(getShare(inValidColor, backgroundColor, defaultWidth));
        et_repeat_password.setBackground(getShare(inValidColor, backgroundColor, defaultWidth));

        et_create_password.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
        et_repeat_password.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
        showErrorContainer(INVALID_PWD_CONTAINER, msg);

    }

    private void showValidPassword(){
        Drawable icon = getDrawable(R.drawable.tick);

        et_create_password.setBackground(getShare(validColor, backgroundColor, defaultWidth));
        et_repeat_password.setBackground(getShare(validColor, backgroundColor, defaultWidth));
        et_create_password.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
        et_repeat_password.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
        hideErrorContainer(INVALID_PWD_CONTAINER);

    }

    private void showErrorContainer(int id, String msg){
        FrameLayout frame = findViewById(id);

        View error_view = getLayoutInflater().inflate(R.layout.fragment_invalid, null);
        error_view.setBackground(getShare(inValidColor, Color.BLACK, 2));
        TextView tv = error_view.findViewById(R.id.tv_error_msg);
        tv.setText(msg);

        frame.addView(error_view);

    }

    private void hideErrorContainer(int id){
        FrameLayout frame = findViewById(id);
        frame.removeAllViews();
    }




//    private void showInvalidFragment(int id, String msg){
//        Log.d(TAG, "showInvalidFragment: ");
//
//        InvalidFragment fragment;
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragment = (InvalidFragment) fragmentManager.findFragmentById(id);
//        if(fragment != null){
//            if(fragment.getFragmentMessage().equals(msg))
//                return;
//
//            InvalidFragment fragment1 = InvalidFragment.newInstance(msg);
//            fragmentTransaction.replace(id, fragment1).commitNow();
//        }
//
//        fragment = InvalidFragment.newInstance(msg);
//        fragmentTransaction.add(id, fragment).addToBackStack(null).commit();
//    }
//
//    private void hideInvalidFragment(int id){
//
//        Log.d(TAG, "hideInvalidFragment: ");
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        InvalidFragment invalidFragment = (InvalidFragment) fragmentManager.findFragmentById(id);
//
//        if(invalidFragment != null){
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.remove(invalidFragment).commitNow();
//            Log.d(TAG, "hideInvalidFragment: Fragment removed");
//        }
//
//        if(fragmentManager.findFragmentById(id) != null)
//            Log.d(TAG, "hideInvalidFragment: It is not removing the fragments");
//    }
}