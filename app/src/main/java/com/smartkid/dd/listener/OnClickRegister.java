package com.smartkid.dd.listener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

import java.util.HashMap;
import java.util.Map;

public class OnClickRegister implements View.OnClickListener {

    private final Context context;
    private AuthActivity registerActivity;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRepassword;
    private EditText[] textViewers;

    public OnClickRegister(Context context,
                           AuthActivity registerActivity,
                           EditText etName,
                           EditText etEmail,
                           EditText etPassword,
                           EditText etRepassword
    ){
        this.context = context;
        this.registerActivity = registerActivity;
        this.etName = etName;
        this.etEmail = etEmail;
        this.etPassword = etPassword;
        this.etRepassword = etRepassword;
        this.textViewers = new EditText[]{this.etEmail, this.etPassword, this.etName, this.etRepassword};
    }

    @Override
    public void onClick(View view) {
        String pseudo = etName.getText().toString(),
                email = etEmail.getText().toString(),
                mdp = etPassword.getText().toString(),
                reMdp = etRepassword.getText().toString();

        try {
            Utilisateur user = new Utilisateur(pseudo, email, mdp, reMdp, Constant.MODE_MODIF);
            this.register(user);
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof AppException){
                AppException exc = (AppException) e;
                this.textViewers[exc.getNum()-1].setError(exc.getMessage());
            }
        }
    }

    private void register(Utilisateur user){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Util.resToString(context, R.string.backend_url) + "/user/inscription";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String idUser = jsonObject.getString("_id");

                        SharedPreferences sharedPreferences = registerActivity.getSharedPreferences("session", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("idUser", idUser);
                        editor.apply();

                        Intent intent = new Intent(context, MainActivity.class);
                        registerActivity.startActivity(intent);
                        registerActivity.finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, requestError -> {
                    requestError.printStackTrace();
                    try {
                        String err = new String(requestError.networkResponse.data,"UTF-8");
                        System.out.println("Errr :" + err);
                        JSONObject jsonObj = new JSONObject(err);
                        String message = jsonObj.getString("message");
                        this.etEmail.setError(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }) {
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("pseudo", user.getPseudo());
                        params.put("email", user.getEmail());
                        params.put("mdp", user.getMdp());
                        params.put("confirmMdp", user.getReMdp());
                        return params;
                    }
                };
        queue.add(stringRequest);
    }
}
