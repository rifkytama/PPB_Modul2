package com.menu.albumfotoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KatalogFotoListAdapter extends RecyclerView.Adapter<KatalogFotoListAdapter.KatalogFotoViewHolder> {

    private LayoutInflater mInflater;

    public KatalogFotoListAdapter(Context context){
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public KatalogFotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.katalog_foto_item, parent, false);
        return new KatalogFotoViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull KatalogFotoViewHolder holder, int position) {
        com.menu.albumfotoku.KatalogFoto katalogFoto = com.menu.albumfotoku.KatalogFotoUtil.getKatalogFotoAt(position);
        holder.ivKatalogFoto.setImageResource(katalogFoto.getResId());
        holder.tvFilenameKatalogFoto.setText(katalogFoto.getFilename());
    }

    @Override
    public int getItemCount() {
        return com.menu.albumfotoku.KatalogFotoUtil.getKatalogFotoList().size();
    }

    class KatalogFotoViewHolder extends RecyclerView.ViewHolder {
        final Button btnCetak;
        final Button[] btnUkuranArray;
        final ImageView ivKatalogFoto;
        final TextView tvFilenameKatalogFoto;
        private  KatalogFotoListAdapter mAdapter;

        private int[] resBtnUkuranArray = {
                R.id.btn_ukuran3r,
                R.id.btn_ukuran4r,
                R.id.btn_ukuran8r,
                R.id.btn_ukuran10r,
        };

        public KatalogFotoViewHolder(@NonNull View itemView, KatalogFotoListAdapter _mAdapter) {
            super(itemView);
            mAdapter = _mAdapter;

            btnCetak = itemView.findViewById(R.id.btn_cetak);
            ivKatalogFoto = itemView.findViewById(R.id.iv_katalog_foto);
            tvFilenameKatalogFoto = itemView.findViewById(R.id.tv_katalog_filename);

            btnUkuranArray = new Button[resBtnUkuranArray.length];

            for(int i=0;i<resBtnUkuranArray.length;i++){
                btnUkuranArray[i] = itemView.findViewById(resBtnUkuranArray[i]);

                btnUkuranArray[i].setOnClickListener(view -> {
                    Button btn = (Button) view;
                    int itemPos = getLayoutPosition();
                    com.menu.albumfotoku.OrderFotoUtil.addOrder(com.menu.albumfotoku.KatalogFotoUtil.getKatalogFotoAt(itemPos),
                            btn.getText().toString());
                });
            }
        }
    }
}
