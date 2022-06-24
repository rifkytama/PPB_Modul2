package com.menu.albumfotoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderFotoUtil {

    private static List<KeranjangBelanjaListener> kbListeners;

    private static List<OrderFoto> orderFotoList;
    private static HashMap<String, Double> hargaMap;
    private static double totalHarga;

    public static void initHargaMap(){
        hargaMap = new HashMap<>();
        hargaMap.put("3R",800.0);
        hargaMap.put("4R",1000.0);
        hargaMap.put("8R",2000.0);
        hargaMap.put("10R",2500.0);
    }

    public static void init(){
        orderFotoList = new ArrayList<>();
        initHargaMap();
        kbListeners = new ArrayList<>();
    }

    public static void addKbListener(KeranjangBelanjaListener listener){
        kbListeners.add(listener);
    }

    public static int getOrderCount(){
        return orderFotoList.size();
    }
    public static OrderFoto getOrderAt(int i){
        return orderFotoList.get(i);
    }

    private static void updateKeranjangBelanja(){
        totalHarga = 0.0;
        for (OrderFoto order:orderFotoList){
            totalHarga += order.getHargaSubtotal();
        }
        for (KeranjangBelanjaListener listener:kbListeners){
            listener.orderChanged();
        }
    }
    public static double getTotalHarga(){
        return totalHarga;
    }

    private static double getHarga(String ukuran){
        Double hargaSatuan = hargaMap.get(ukuran);
        if (hargaSatuan == null)
            hargaSatuan = 0.0;
        return hargaSatuan;
    }

    public static void addOrder(KatalogFoto foto, String ukuran){
        orderFotoList.add(new OrderFoto(foto, 1, ukuran, getHarga(ukuran)));
        updateKeranjangBelanja();
    }

    public static void removeOrderAt(int i){
        orderFotoList.remove(i);
        updateKeranjangBelanja();
    }

    public static void addOneToOrder(int i){
        OrderFoto order = orderFotoList.get(i);
        order.setNumOrder(order.getNumOrder()+1);
        order.setHargaSubtotal(order.getHargaSubtotal() + getHarga(order.getUkuran()));
        updateKeranjangBelanja();
    }
    public static boolean minusOneToOrder(int i){
        OrderFoto order = orderFotoList.get(i);
        if (order.getNumOrder()==1)
            return false;

        order.setNumOrder(order.getNumOrder()-1);
        order.setHargaSubtotal(order.getHargaSubtotal() - getHarga(order.getUkuran()));
        updateKeranjangBelanja();
        return true;
    }
}
