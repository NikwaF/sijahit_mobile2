package com.example.sijahit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sijahit.model.Login_session_model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

public class Login extends AppCompatActivity {

    private TextView btn_registrasi;
    private EditText email,password;
    private Button login_btn;
    private ProgressDialog progressDialog;
    private final String  url_login = "http://192.168.1.2/sijahit/api/login_customer";
    private Login_session_model login_session_model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_session_model = new Login_session_model(getApplicationContext());

        if(login_session_model.isLogin()){
            gas();
        }

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        btn_registrasi = findViewById(R.id.btn_registrasi);
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Mohon tunggu sebentar");

        btn_registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrasi = new Intent(Login.this, RegisterActivity.class);
                startActivity(registrasi);
                CustomIntent.customType(Login.this, "fadein-to-fadeout");
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validasi()){
                    progressDialog.show();
                    login();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private Boolean validasi(){
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("email wajib diisi");
            email.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("password wajib diisi");
            password.requestFocus();
            return false;
        }

        else {
            return true;
        }
    }

    private void login(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String kode_login = jsonObject.getString("kode");

                            if(kode_login.equals("1")){
                                String id = jsonObject.getJSONObject("data").getString("id");
                                String nama = jsonObject.getJSONObject("data").getString("nama");
                                String email = jsonObject.getJSONObject("data").getString("email");
                                String no = jsonObject.getJSONObject("data").getString("no_hp");

                                login_session_model.save_session_login(id,nama,email,no);
                                progressDialog.dismiss();
                                gas();
                            }
                            if(kode_login.equals("2")){

                                password.setError("password anda salah");
                                progressDialog.dismiss();
                            }
                            if(kode_login.equals("3")){
                                email.setError("Email yang anda input belum terdaftar");
                                progressDialog.dismiss();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> params = new HashMap<>();
                params.put("email", email.getText().toString());
                params.put("password",password.getText().toString());
                return  params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void gas(){
        Intent intent = new Intent(Login.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
