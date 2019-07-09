package com.example.sijahit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profile_ukuran_detail extends AppCompatActivity {
    TextView tv_panjangdada,tv_lingkardada,tv_lebardada, tv_lingkarlengan,tv_panjanglengan,
                tv_lingkarpinggul,tv_panjangbahu,tv_panjangpunggung, tv_lingkarpinggang,
                tv_panjangcelana,tv_lingkarcelana,tv_lingkarpaha,tv_jeniskelamin,textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ukuran_detail);

        tv_panjangdada = findViewById(R.id.panjang_dada);
        tv_lingkardada = findViewById(R.id.lingkar_dada);
        tv_lebardada= findViewById(R.id.lebar_dada);
        tv_lingkarlengan= findViewById(R.id.lingkar_lengan);
        tv_panjanglengan= findViewById(R.id.panjang_lengan);
        tv_lingkarpinggul= findViewById(R.id.lingkar_pinggul);
        tv_panjangbahu= findViewById(R.id.panjang_bahu);
        tv_panjangpunggung= findViewById(R.id.panjang_punggung);
        tv_lingkarpinggang= findViewById(R.id.lingkar_pinggang);
        tv_panjangcelana= findViewById(R.id.panjang_celana);
        tv_lingkarcelana= findViewById(R.id.lingkar_celana);
        tv_lingkarpaha= findViewById(R.id.lingkar_paha);
        tv_jeniskelamin= findViewById(R.id.jenis_kelamin);
        textView = findViewById(R.id.textView);


        String id_profile_ukuran = getIntent().getExtras().getString("id_profile_ukuran");
        String nama_profile = getIntent().getExtras().getString("nama_profile");
        String panjang_dada = getIntent().getExtras().getString("panjang_dada");
        String lingkar_dada = getIntent().getExtras().getString("lingkar_dada");
        String lebar_dada = getIntent().getExtras().getString("lebar_dada");
        String panjang_lengan = getIntent().getExtras().getString("panjang_lengan");
        String lingkar_lengan = getIntent().getExtras().getString("lingkar_lengan");
        String lingkar_pinggul = getIntent().getExtras().getString("lingkar_pinggul");
        String panjang_bahu = getIntent().getExtras().getString("panjang_bahu");
        String lingkar_pinggang = getIntent().getExtras().getString("lingkar_pinggang");
        String panjang_celana = getIntent().getExtras().getString("panjang_celana");
        String lingkar_celana = getIntent().getExtras().getString("lingkar_celana");
        String lingkar_paha = getIntent().getExtras().getString("lingkar_paha");
        String jenis_kelamin = getIntent().getExtras().getString("jenis_kelamin");

        textView.setText(nama_profile);
        tv_panjangdada.setText(panjang_dada+ " cm");
        tv_lingkardada.setText(lingkar_dada+ " cm");
        tv_lebardada.setText(lebar_dada+ " cm");
        tv_panjanglengan.setText(panjang_lengan+ " cm");
        tv_lingkarlengan.setText(lingkar_lengan+ " cm");
        tv_panjangbahu.setText(panjang_bahu+ " cm");
        tv_lingkarpinggang.setText(lingkar_pinggang + " cm");
        tv_panjangcelana.setText(panjang_celana+ " cm");
        tv_lingkarcelana.setText(lingkar_celana+ " cm");
        tv_lingkarpaha.setText(lingkar_paha+ " cm");
        tv_jeniskelamin.setText(jenis_kelamin);


    }
}
