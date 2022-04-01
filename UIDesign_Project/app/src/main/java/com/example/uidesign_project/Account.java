package com.example.uidesign_project;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;

import java.util.regex.Pattern;

public class Account implements Parcelable {

    private String email_addr;
    private String password;

    public Account(String email, String pwd) {
        this.email_addr = email;
        this.password = pwd;

    }

    protected Account(Parcel in) {
        email_addr = in.readString();
        password = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public static boolean isValidEmail(String email){
        return (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isValidPassword(String password) {

        if (password.length() < 8)
            return false;

        boolean containNumber = false;
        boolean containUpperCase = false;
        boolean containLowerCase = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isDigit(c))
                containNumber = true;

            if (Character.isUpperCase(c))
                containUpperCase = true;

            if (Character.isLowerCase(c))
                containLowerCase = true;
        }

        return containLowerCase && containUpperCase && containNumber;
    }

    public static boolean checkRepeatPassword(String password, String repeat_password) {

        return password.equals(repeat_password);
    }

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email_addr);
        parcel.writeString(password);

    }
}