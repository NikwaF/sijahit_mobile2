package com.example.sijahit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sijahit.model.Login_session_model;

import maes.tech.intentanim.CustomIntent;

public class HomeActivity extends AppCompatActivity {

    ImageView account_btn,profile_ukuran_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        account_btn = findViewById(R.id.account_btn);
        profile_ukuran_btn = findViewById(R.id.profile_ukuran_btn);

        profile_ukuran_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gas_profile_ukuran();
            }
        });
        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gas_account();

            }
        });


    }

    private void gas_profile_ukuran(){
        Intent intent = new Intent(HomeActivity.this, ProfileUkuranActivity.class);
        startActivity(intent);
        CustomIntent.customType(HomeActivity.this, "right-to-left");
    }

    private  void gas_account(){
        Intent intent = new Intent(HomeActivity.this,AccountActivity.class);
        startActivity(intent);
        CustomIntent.customType(HomeActivity.this, "right-to-left");
    }
}
