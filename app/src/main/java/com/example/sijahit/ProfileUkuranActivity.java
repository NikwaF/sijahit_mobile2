package com.example.sijahit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sijahit.adapter.Profile_ukuran_adapter;
import com.example.sijahit.model.Login_session_model;
import com.example.sijahit.model.Profile_ukuran_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ProfileUkuranActivity extends AppCompatActivity {

    private String url_get_profile_ukuran = "http://sijahit.webtif.com/api/Get_profile_ukuran";
    private List<Profile_ukuran_model> profile_ukuran_models ;
    private RecyclerView recyclerView;
    Login_session_model login_session_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ukuran);

        profile_ukuran_models = new ArrayList<>();
        login_session_model = new Login_session_model(this);


        recyclerView = findViewById(R.id.recycleview);

        get_data();
    }

    private  void get_data(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_get_profile_ukuran,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String kode = jsonObject.getString("kode");
                            System.out.println(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(kode.equals("1")){

                                for(int $i = 0 ; jsonArray.length() > $i ; $i++){
                                    JSONObject data = jsonArray.getJSONObject($i);
                                    String id_profile_ukuran = data.getString("id_profile_ukuran");
                                    String nama_profile = data.getString("nama_profile");
                                    String panjang_dada= data.getString("panjang_dada");
                                    String lingkar_dada= data.getString("lingkar_dada");
                                    String lebar_dada= data.getString("lebar_dada");
                                    String panjang_lengan= data.getString("panjang_lengan");
                                    String lingkar_lengan= data.getString("lingkar_lengan");
                                    String lingkar_pinggul = data.getString("lingkar_pinggul");
                                    String panjang_bahu= data.getString("panjang_bahu");
                                    String panjang_punggung = data.getString("panjang_punggung");
                                    String lingkar_pinggang = data.getString("lingkar_pinggang");
                                    String panjang_celana = data.getString("panjang_celana");
                                    String lingkar_celana = data.getString("lingkar_celana");
                                    String lingkar_paha = data.getString("lingkar_paha");
                                    String jenis_kelamin = data.getString("jenis_kelamin");
                                    Profile_ukuran_model profile_ukuran_model =
                                    new Profile_ukuran_model(
                                            id_profile_ukuran,nama_profile,panjang_dada,lingkar_dada,lebar_dada,panjang_lengan,
                                            lingkar_lengan,lingkar_pinggul,panjang_bahu,panjang_punggung,lingkar_pinggang,panjang_celana,
                                            lingkar_celana,lingkar_paha,jenis_kelamin
                                    );

                                    profile_ukuran_models.add(profile_ukuran_model);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        setuprecycleview(profile_ukuran_models);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> profile = new HashMap<>();
                HashMap<String,String> id = login_session_model.getCustomerDetail();

                profile.put("id_customer", id.get(Login_session_model.ID_CUSTOMER));
                return profile;
            }
        };

        RequestQueue req = Volley.newRequestQueue(this);
        req.add(stringRequest);
    }

    private void setuprecycleview(List<Profile_ukuran_model> profile_ukuran_models) {
        Profile_ukuran_adapter profile_ukuran_adapter = new Profile_ukuran_adapter(this,profile_ukuran_models);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(profile_ukuran_adapter);

    }
}
