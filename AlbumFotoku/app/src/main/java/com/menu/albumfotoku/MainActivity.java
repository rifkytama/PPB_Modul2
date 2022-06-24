package com.menu.albumfotoku;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity
        implements com.menu.albumfotoku.KeranjangBelanjaListener {

    private RecyclerView rvKatalogFoto;
    private com.menu.albumfotoku.KatalogFotoListAdapter adapter;
    private Button btnKeranjangBelanja;
    public class MyApp extends Application {

        public MyApp() {
            if(BuildConfig.DEBUG)
                StrictMode.enableDefaults();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.menu.albumfotoku.KatalogFotoUtil.init();
        com.menu.albumfotoku.OrderFotoUtil.init();

        rvKatalogFoto = findViewById(R.id.rv_katalog_foto);
        adapter = new com.menu.albumfotoku.KatalogFotoListAdapter(this);

        rvKatalogFoto.setAdapter((adapter));
        rvKatalogFoto.setLayoutManager(new LinearLayoutManager(this));

        btnKeranjangBelanja = findViewById(R.id.btn_keranjang_belanja);
        orderChanged();
        btnKeranjangBelanja.setOnClickListener(view -> {
            Intent intent = new Intent(this, com.menu.albumfotoku.KeranjangBelanjaActivity.class);
            startActivity(intent);
        });

        com.menu.albumfotoku.OrderFotoUtil.addKbListener(this);
    }

    @Override
    public void orderChanged() {
        String kbCountStr = "Keranjang Belanja: " + com.menu.albumfotoku.OrderFotoUtil.getOrderCount();
        btnKeranjangBelanja.setText(kbCountStr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "App telah di-resume",Toast.LENGTH_SHORT).show();
    }
}