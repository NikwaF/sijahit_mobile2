package com.example.sijahit.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sijahit.Login;

import java.util.HashMap;

public class Login_session_model {
    Context context;

    public static String ID_CUSTOMER = "id_customer", NAMA_CUSTOMER = "nama_customer", EMAIL_CUSTOMER = "email_customer", NO_HP="no_hp";
    private static String LOGIN = "IS_LOGIN";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;


    public Login_session_model(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("login_data",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void save_session_login(String id_customer,String nama_customer,String email_customer, String no_hp){
        editor.putBoolean(LOGIN, true);
        editor.putString(ID_CUSTOMER,id_customer);
        editor.putString(NAMA_CUSTOMER,nama_customer);
        editor.putString(EMAIL_CUSTOMER,email_customer);
        editor.putString(NO_HP,no_hp);
        editor.apply();
    }

    public Boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public HashMap<String,String> getCustomerDetail(){
        HashMap<String,String> customer = new HashMap<>();
        customer.put(ID_CUSTOMER, sharedPreferences.getString(ID_CUSTOMER,null));
        customer.put(NAMA_CUSTOMER, sharedPreferences.getString(NAMA_CUSTOMER,null));
        customer.put(EMAIL_CUSTOMER, sharedPreferences.getString(EMAIL_CUSTOMER,null));
        customer.put(NO_HP, sharedPreferences.getString(NO_HP,null));
        return customer;
    }

    public void logout(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }
}
