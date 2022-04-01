package com.example.uidesign_project;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement implements Parcelable {
    
    private static final String TAG = "AccountManagement";

    List<Account> account_list;

    public AccountManagement() {

        this.account_list = new ArrayList<Account>();
    }

    public AccountManagement(Parcel parcel){
        account_list = new ArrayList<>();
        parcel.readList(account_list, Account.class.getClassLoader());
    }

    public static final Creator<AccountManagement> CREATOR = new Creator<AccountManagement>() {
        @Override
        public AccountManagement createFromParcel(Parcel in) {
            return new AccountManagement(in);
        }

        @Override
        public AccountManagement[] newArray(int size) {
            return new AccountManagement[size];
        }
    };

    public boolean isAccountExist(String email){
        for(Account a: account_list)
            if(a.getEmail_addr().equals(email))
                return true;

        return false;
    }

    public void add(Account newAccount){
        account_list.add(newAccount);
        Log.d(TAG, "add: " + account_list.size());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(account_list);

    }
}
