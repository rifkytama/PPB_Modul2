package com.menu.albumfotoku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderFotoListAdapter extends RecyclerView.Adapter<OrderFotoListAdapter.OrderFotoViewHolder> {

    private LayoutInflater mInflater;

    public OrderFotoListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OrderFotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.order_foto_item, parent, false);
        return new OrderFotoViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderFotoViewHolder holder, int position) {
        com.menu.albumfotoku.OrderFoto order = com.menu.albumfotoku.OrderFotoUtil.getOrderAt(position);
        KatalogFoto katalogFoto = order.getKatalogFoto();
        holder.ivOrderFoto.setImageResource(katalogFoto.getResId());
        holder.tvFilenameOrderFoto.setText(katalogFoto.getFilename());
        holder.tvNumOrder.setText(order.getNumOrder()+ "");
        holder.tvUkuran.setText(order.getUkuran());
        holder.tvSubtotal.setText(IdrFormatter.format(order.getHargaSubtotal()));
    }

    @Override
    public int getItemCount() {
        return com.menu.albumfotoku.OrderFotoUtil.getOrderCount();
    }

    class OrderFotoViewHolder extends RecyclerView.ViewHolder {
        final TextView tvFilenameOrderFoto, tvUkuran, tvNumOrder, tvSubtotal;
        final Button btnDel, btnIncOrder, btnDecOrder;
        final ImageView ivOrderFoto;

        OrderFotoListAdapter mAdapter;

        public OrderFotoViewHolder(@NonNull View itemView, OrderFotoListAdapter _mAdapter) {
            super(itemView);
            mAdapter = _mAdapter;
            tvFilenameOrderFoto = itemView.findViewById(R.id.tv_foto_filename);
            tvUkuran = itemView.findViewById(R.id.tv_foto_order_ukuran);
            tvNumOrder = itemView.findViewById(R.id.tv_foto_num);
            tvSubtotal = itemView.findViewById(R.id.tv_sub_price);


            btnDel = itemView.findViewById(R.id.btn_del_foto_order);
            btnDel.setOnClickListener(view -> {
                int itemPos = getLayoutPosition();
                showDialog(itemView.getContext(), tvFilenameOrderFoto.getText().toString(),itemPos);
            });

            btnIncOrder = itemView.findViewById(R.id.btn_inc_foto_num);

            btnIncOrder.setOnClickListener(view -> {
                int itemPos = getLayoutPosition();
                com.menu.albumfotoku.OrderFotoUtil.addOneToOrder(itemPos);
                notifyItemChanged(itemPos);
            });
            btnDecOrder = itemView.findViewById(R.id.btn_dec_foto_num);
            btnDecOrder.setOnClickListener(view -> {
                int itemPos = getLayoutPosition();
                if(!com.menu.albumfotoku.OrderFotoUtil.minusOneToOrder(itemPos))
                    showDialog(itemView.getContext(), tvFilenameOrderFoto.getText().toString(),itemPos);
                notifyItemChanged(itemPos);
            });


            ivOrderFoto = itemView.findViewById(R.id.iv_foto_order);
        }
    }
    private void showDialog(Context context, String filename, int itemPos){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Menghapus Order");
        alertDialog.setMessage("Apakah anda yakin mau menghapus order cetak" +
                filename+"?");
        alertDialog.setPositiveButton("Ya", (dialogInterface, i) -> {
            if (i == DialogInterface.BUTTON_POSITIVE){
                com.menu.albumfotoku.OrderFotoUtil.removeOrderAt(itemPos);
                notifyItemRemoved(itemPos);
            }
        });
        alertDialog.setNegativeButton("Tidak", null);
        alertDialog.show();
    }
}
