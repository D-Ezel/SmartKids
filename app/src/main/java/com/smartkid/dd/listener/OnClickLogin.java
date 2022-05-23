package com.smartkid.dd.listener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartkid.dd.R;
import com.smartkid.dd.activity.AuthActivity;
import com.smartkid.dd.activity.MainActivity;
import com.smartkid.dd.classes.Utilisateur;
import com.smartkid.dd.classes.base.Constant;
import com.smartkid.dd.classes.base.Util;
import com.smartkid.dd.exception.AppException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.HashMap;
import java.util.Map;

public class OnClickLogin implements View.OnClickListener {
    private final Context context;
    private EditText etEmail;
    private EditText etPassword;
    private AuthActivity loginActivity;

    public OnClickLogin(Context context, AuthActivity loginActivity, EditText etEmail, EditText etPassword){
        this.context = context;
        this.loginActivity = loginActivity;
        this.etEmail = etEmail;
        this.etPassword = etPassword;
    }

    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString(),
                mdp = etPassword.getText().toString();
        try {
            Utilisateur user = new Utilisateur(email, mdp , Constant.MODE_MODIF);
            this.login(user.getEmail(), user.getMdp());
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof AppException){
                AppException exc = (AppException) e;
                if(exc.getNum() == 1){
                    this.etEmail.setError(exc.getMessage());
                } else if(exc.getNum() == 2){
                    this.etPassword.setError(exc.getMessage());
                }
            }
        }
    }

    private void login(String email, String mdp){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Util.resToString(context, R.string.backend_url) + "/user/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String idUser = jsonObject.getString("_id");

                        SharedPreferences sharedPreferences = loginActivity.getSharedPreferences("session", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("idUser", idUser);
                        editor.apply();

                        Intent intent = new Intent(context, MainActivity.class);
                        loginActivity.startActivity(intent);
                        loginActivity.finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, requestError -> {
                    requestError.printStackTrace();
                    try {
                        String err = new String(requestError.networkResponse.data,"UTF-8");
                        JSONObject jsonObj = new JSONObject(err);
                        String message = jsonObj.getString("message");
                        this.etEmail.setError(message);
                        this.etPassword.setError(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }) {
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("mdp", mdp);
                        return params;
                    }
                };
        queue.add(stringRequest);
    }
}