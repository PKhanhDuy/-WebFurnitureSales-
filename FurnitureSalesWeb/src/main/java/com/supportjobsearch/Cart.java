package com.supportjobsearch;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Cart {
    private Map<Integer, CartItem> data = new HashMap<Integer, CartItem>();

    public boolean add(Product p){
        if(data.containsKey(p.getId())) return update(p.getId(), data.get(p.getId()).getQuantity()+1);
        data.put(p.getId(), convert(p));
        return true;
    }
    public boolean add(CartItem p){
        if(data.containsKey(p.getId())) return update(p.getId(), data.get(p.getId()).getQuantity()+1);
        data.put(p.getId(), p);
        return true;
    }

//    8.3.8. updateQuantity() -cập nhật lại số lượng trong CartItem-
    public boolean update(int id, int quantity){
        if(!data.containsKey(id)) return false;
        CartItem p = data.get(id);
        if(quantity<1) return false;
        p.setQuantity(quantity);
        data.put(id, p);
        return true;
    }

    //xoa mot san pham khoi gio hang
    public boolean remove(int id){
        return data.remove(id) != null;
    }

//   8.3.4. getListCartItem() -lấy danh sách CartItem trong Cart-
    public List<CartItem> getListCartItem(){
        return new ArrayList<CartItem>(data.values());
    }

    //Tinh tong so luong tat ca san pham co trong gio hang
    public int getTotalQuantity(){
        AtomicInteger i = new AtomicInteger();
        data.values().forEach(cp -> i.addAndGet(cp.getQuantity()));
        return i.get();
    }

    //Ting tong tien cua gio hang
    public double getTotal(){
        AtomicReference<Double> res = new AtomicReference<>((double) 0);
        data.values().forEach(cp -> res.updateAndGet(v -> (v + cp.getQuantity() * cp.getPrice())));
        return res.get();
    }

    public List<CartItem> getSelectedProducts(List<String> selectedProductIds) {
        return null;
    }
    public CartItem convert(Product p){
        CartItem cp = new CartItem();
        cp.setId(p.getId());
        cp.setName(p.getProName());
        cp.setPrice(p.getPrice());
        cp.setImg(p.getThumb());
        cp.setQuantity(1);
        return cp;
    }
}
