package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.Bean.Promotion;

import java.time.LocalDateTime;
import java.util.List;

public interface IPromotionDao {
    // Lay ra tat ca cac promotion
    List<Promotion> getAll();
    public List<Promotion> getAll(boolean force);
    //lay promotion theo id
    Promotion getById(int id);
    //lay promotion theo code
    Promotion getByCode(String code);
    //Them mot promotion moi
    int add(Promotion promotion);
    // cap nhat gioi han su dung cua promotion
    boolean updateLimit(int newLimit);
    //Cap nhat gia tri cua promotion
    boolean updateValue(double newValue);
    //Cap nhat trang thai cua promotion
    boolean updateStatus(String newStatus);
    //Cap nhat ngay bat dau cua promotion
    boolean updateStartDate(LocalDateTime newStartDate);
    //Cap nhat ngay ket thuc cua promotion
    boolean updateEndDate(LocalDateTime newEndDate);
    //xoa promotion theo id
    boolean delete(int id);
}
