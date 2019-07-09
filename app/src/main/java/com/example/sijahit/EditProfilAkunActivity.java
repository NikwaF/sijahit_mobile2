package com.example.sijahit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.example.sijahit.model.Login_session_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

public class EditProfilAkunActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    private EditText nama,email,nohp;
    private String url_load_data = "http://sijahit.webtif.com/api/customer";
    Login_session_model login_session_model;
    private Button update_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil_akun);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon tunggu sebentar");

        login_session_model = new Login_session_model(this);
        nama = findViewById(R.id.nama_customer);
        email = findViewById(R.id.email);
        nohp = findViewById(R.id.nomer_hp);
        progressDialog.show();

        load_data();

        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validasi()){
                    progressDialog.show();
                    edit_data();
                }
            }
        });




    }

    private Boolean validasi(){
        if(TextUtils.isEmpty(nama.getText().toString())){
            nama.setError("masukkan nama anda");
            nama.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("masukkan nama anda");
            nohp.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(nohp.getText().toString())){
            nohp.setError("masukkan nama anda");
            nohp.requestFocus();
            return false;
        }
        else{
            return true;
        }
    }

    private void load_data(){
        Login_session_model login_session_model = new Login_session_model(EditProfilAkunActivity.this);
        HashMap<String,String> aduh = login_session_model.getCustomerDetail();
        StringRequest load = new StringRequest(Request.Method.GET, url_load_data+"?id_customer="+aduh.get(login_session_model.ID_CUSTOMER),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            System.out.println(response);
                            String kode_respon = jsonObject.getString("kode");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            String namanya = "";
                            String emailnya = "";
                            String nonya = "";


                            for(int i=0 ; i < jsonArray.length() ; i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                namanya = data.getString("nama_customer");
                                emailnya = data.getString("email");
                                nonya = data.getString("no_hp");
                            }


                            if(kode_respon.equals("1")){
                                nama.setText(namanya);
                                email.setText(emailnya);
                                nohp.setText(nonya);

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
                });

        RequestQueue aha = Volley.newRequestQueue(EditProfilAkunActivity.this);
        aha.add(load);
    }

    private void edit_data(){
        StringRequest edit = new StringRequest(Request.Method.PUT, url_load_data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String respon = jsonObject.getString("kode");

                            if(respon.equals("1")){
                                HashMap<String,String> id = login_session_model.getCustomerDetail();

                                login_session_model.save_session_login(id.get(Login_session_model.ID_CUSTOMER),nama.getText().toString(),email.getText().toString(),nohp.getText().toString());

                                progressDialog.dismiss();

                                new AlertDialog.Builder(EditProfilAkunActivity.this)
                                        .setTitle("Sukses!")
                                        .setMessage("Data berhasil diupdate")
                                        .setCancelable(false)
                                        .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(EditProfilAkunActivity.this,AccountActivity.class);
                                                startActivity(intent);
                                                CustomIntent.customType(EditProfilAkunActivity.this, "left-to-right");
                                            }
                                        }).show();
                            }
                            if(respon.equals("2")){
                                progressDialog.dismiss();


                                new AlertDialog.Builder(EditProfilAkunActivity.this)
                                        .setTitle("Gagal!")
                                        .setMessage("Data tidak berhasil diupdate")
                                        .setCancelable(false)
                                        .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        }).show();

                            }
                            if(respon.equals("3")){
                                progressDialog.dismiss();
                                email.setError("Email sudah tersedia!");
                                email.requestFocus();
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
                }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> edit = new HashMap<>();
                HashMap<String,String> id = login_session_model.getCustomerDetail();

                edit.put("id_customer", id.get(Login_session_model.ID_CUSTOMER));
                edit.put("email", email.getText().toString());
                edit.put("nama_customer",nama.getText().toString());
                edit.put("no_hp",nohp.getText().toString());

                return edit;
            }
        };

        RequestQueue editlah = Volley.newRequestQueue(this);
        editlah.add(edit);
    }
}
