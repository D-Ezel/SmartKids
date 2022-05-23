package com.smartkid.dd.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smartkid.dd.R;
import com.smartkid.dd.activity.AuthActivity;
import com.smartkid.dd.listener.OnClickRegister;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    Button btnRegister;
    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etRepassword;
    AuthActivity registerActivity;



    public RegisterFragment() {
        // Required empty public constructor
    }

    public RegisterFragment(AuthActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
            this.setObjectFragment(view);
            this.btnRegister.setOnClickListener(new OnClickRegister(this.getContext(), this.registerActivity, this.etName, this.etEmail, this.etPassword, this.etRepassword));
        return view;
    }

    private void setObjectFragment(View view){
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        etName = (EditText) view.findViewById(R.id.et_name);
        etEmail = (EditText) view.findViewById(R.id.et_email);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        etRepassword = (EditText) view.findViewById(R.id.et_repassword);
    }
}