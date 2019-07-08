package com.example.sijahit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sijahit.model.Login_session_model;

import org.w3c.dom.Text;

import java.util.HashMap;

import maes.tech.intentanim.CustomIntent;

public class AccountActivity extends AppCompatActivity {
    private TextView nama_akun, email,nohp, edit_profil_btn;
    private Button logout_btn;
    Login_session_model login_session_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);



        nama_akun = findViewById(R.id.nama_akun);
        email = findViewById(R.id.emailakun);
        nohp = findViewById(R.id.nohp_akun);
        edit_profil_btn = findViewById(R.id.edit_profil_btn);

        login_session_model = new Login_session_model(AccountActivity.this);

        HashMap<String,String> customer = login_session_model.getCustomerDetail();
        nama_akun.setText(customer.get(login_session_model.NAMA_CUSTOMER));
        email.setText(customer.get(login_session_model.EMAIL_CUSTOMER));
        nohp.setText(customer.get(login_session_model.NO_HP));


        logout_btn = findViewById(R.id.logout_btn);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_session_model.logout();
                finish();
            }
        });

        edit_profil_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, EditProfilAkunActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(AccountActivity.this,HomeActivity.class);
        startActivity(intent);
        CustomIntent.customType(AccountActivity.this, "left-to-right");
        super.onBackPressed();
    }
}
