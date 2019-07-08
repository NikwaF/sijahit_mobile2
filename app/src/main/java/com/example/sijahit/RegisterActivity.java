package com.example.sijahit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sijahit.model.Register_model;

import maes.tech.intentanim.CustomIntent;

public class RegisterActivity extends AppCompatActivity {
    private Button next_btn;
    private EditText nama_customer,email,no_hp, pass ,repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama_customer = findViewById(R.id.nama_customer);
        email = findViewById(R.id.email);
        no_hp = findViewById(R.id.nomer_hp);
        pass = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validdasi()){
                    simpan_data();
                    gas();
                }
            }
        });
    }

    private  void gas(){
        Intent intent = new Intent(RegisterActivity.this,AlamatActivity.class);
        startActivity(intent);
        CustomIntent.customType(RegisterActivity.this, "left-to-right");
    }

    private boolean validdasi(){
        if(TextUtils.isEmpty(nama_customer.getText().toString())){
            nama_customer.setError("masukkan nama anda");
            nama_customer.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(no_hp.getText().toString())){
            no_hp.setError("no hp wajib diisi");
            no_hp.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("email wajib diisi");
            email.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(pass.getText().toString())){
            pass.setError("password wajib diisi");
            pass.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(repassword.getText().toString())){
            repassword.setError("konfirmasi password wajib diisi");
            repassword.requestFocus();
            return false;
        }

        else if(!repassword.getText().toString().equals(pass.getText().toString())){
            repassword.setError("konfirmasi password wajib sama dengan password");
            repassword.requestFocus();
            return false;
        }

        else {
            return true;
        }
    }

    private void simpan_data(){
        Register_model register_model = new Register_model();
        register_model.setNama_customer(nama_customer.getText().toString());
        register_model.setEmail(email.getText().toString());
        register_model.setPassword(pass.getText().toString());
        register_model.setNo_hp(no_hp.getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent registrasi = new Intent(RegisterActivity.this, Login.class);
        startActivity(registrasi);
        CustomIntent.customType(RegisterActivity.this, "fadein-to-fadeout");
    }
}
