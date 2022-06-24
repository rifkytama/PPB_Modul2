package com.menu.albumfotoku;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KeranjangBelanjaActivity extends AppCompatActivity implements com.menu.albumfotoku.KeranjangBelanjaListener {

    private RecyclerView rvOrderFoto;
    private com.menu.albumfotoku.OrderFotoListAdapter adapter;
    private TextView tvTotal;
    private TextView tvKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_belanja);
        rvOrderFoto = findViewById(R.id.rv_order_foto);
        adapter = new com.menu.albumfotoku.OrderFotoListAdapter(this);

        rvOrderFoto.setAdapter((adapter));
        rvOrderFoto.setLayoutManager(new LinearLayoutManager(this));

        com.menu.albumfotoku.OrderFotoUtil.addKbListener(this);

        tvTotal = findViewById(R.id.tv_total_harga);
        tvKosong = findViewById(R.id.tv_keranjang_kosong);
        orderChanged();
    }

    @Override
    public void orderChanged() {
        if (com.menu.albumfotoku.OrderFotoUtil.getOrderCount() == 0){
            rvOrderFoto.setVisibility(View.GONE);
            tvKosong.setVisibility(View.VISIBLE);
        }
        else {
            rvOrderFoto.setVisibility(View.VISIBLE);
            tvKosong.setVisibility(View.GONE);
        }
        String totalStr = "Total Belanja: " + IdrFormatter.format(com.menu.albumfotoku.OrderFotoUtil.getTotalHarga());
        tvTotal.setText(totalStr);
    }
}