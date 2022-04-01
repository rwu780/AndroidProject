package com.example.uidesign_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InvalidFragment extends Fragment {


    private String message;

    public InvalidFragment(String message) {
        this.message = message;
    }

    public static InvalidFragment newInstance(String message) {
        InvalidFragment fragment = new InvalidFragment(message);

        return fragment;
    }

    public String getFragmentMessage(){
        return this.message;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_invalid, container, false);

        TextView tv_error_msg = rootView.findViewById(R.id.tv_error_msg);
        tv_error_msg.setText(this.message);

        return rootView;
    }
}