package com.example.sijahit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sijahit.model.Register_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

public class AlamatActivity extends AppCompatActivity {
    private EditText kelurahan,kecamatan,kodepos,detail_alamat;
    private Button registrasi_button;
    private final String  url_regist = "http://sijahit.webtif.com/api/register_customer";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);

        kelurahan = findViewById(R.id.kelurahan);
        kecamatan = findViewById(R.id.kecamatan);
        kodepos= findViewById(R.id.kode_pos);
        detail_alamat= findViewById(R.id.detail_alamat);
        registrasi_button = findViewById(R.id.registrasi_btn);
        progressDialog = new ProgressDialog(AlamatActivity.this);
        progressDialog.setTitle("Mohon tunggu sebentar");


        registrasi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validasi()){
                    progressDialog.show();
                    registrasi();
                }
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent registrasi = new Intent(AlamatActivity.this, RegisterActivity.class);
        startActivity(registrasi);
        CustomIntent.customType(AlamatActivity.this, "right-to-left");
    }

    private boolean validasi(){
        if(TextUtils.isEmpty(kelurahan.getText().toString())){
            kelurahan.setError("kelurahan wajib diisi");
            kelurahan.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(kecamatan.getText().toString())){
            kecamatan.setError("kecamatan wajib diisi");
            kecamatan.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(kodepos.getText().toString())){
            kodepos.setError("kode pos wajib diisi");
            kodepos.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(detail_alamat.getText().toString())){
            detail_alamat.setError("detail alamat wajib diisi");
            detail_alamat.requestFocus();
            return false;
        }

        else {
            return true;
        }
    }

    private void registrasi(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_regist,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String register_respon = jsonObject.getString("status");

                            if(register_respon.equals("0")){
                                Toast.makeText(AlamatActivity.this,"email sudah pernah dipakai!",Toast.LENGTH_SHORT).show();
                            }
                            if (register_respon.equals("1")){
                                Toast.makeText(AlamatActivity.this,"Registrasi gagal! silahkan tunggu beberapa saat",Toast.LENGTH_SHORT).show();
                            }
                            if (register_respon.equals("2")){
                                Toast.makeText(AlamatActivity.this,"Registrasi berhasil",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                gas();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("nama",Register_model.nama_customer);
                params.put("no_hp",Register_model.no_hp);
                params.put("email",Register_model.email);
                params.put("password",Register_model.password);
                params.put("kelurahan",kelurahan.getText().toString());
                params.put("kecamatan",kecamatan.getText().toString());
                params.put("kode_pos",kodepos.getText().toString());
                params.put("detail_alamat",detail_alamat.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void gas(){
        Intent login = new Intent(AlamatActivity.this, Login.class);
        startActivity(login);
        CustomIntent.customType(AlamatActivity.this, "fadein-to-fadeout");
    }
}
