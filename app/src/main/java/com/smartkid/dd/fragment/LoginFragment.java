package com.smartkid.dd.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smartkid.dd.R;

public class LoginFragment extends Fragment {

    Button btnLogin;
    EditText etEmail;
    EditText etPassword;
    TextView tvTitle;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_login, container, false);
            this.loadAnimation(view);
         return  view;
    }

    private void loadAnimation(View view){
        // Animation tv_title TextView
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        Animation animTitle = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        animTitle.setStartOffset(500);
        tvTitle.startAnimation(animTitle);

        // Animation et_email
        etEmail = (EditText) view.findViewById(R.id.et_email);
        Animation animMail = AnimationUtils.loadAnimation(getContext(), R.anim.lefttoright);
        animMail.setStartOffset(100);
        etEmail.startAnimation(animMail);

        // Animation et_password
        etPassword = (EditText) view.findViewById(R.id.et_password);
        Animation animMdp = AnimationUtils.loadAnimation(getContext(), R.anim.lefttoright);
        animMdp.setStartOffset(250);
        etPassword.startAnimation(animMdp);

        // animation button
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.lefttoright);
        animation.setStartOffset(400);
        btnLogin.startAnimation(animation);
    }
}