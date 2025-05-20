package com.supportjobsearch.DAO.iml;

import com.supportjobsearch.Bean.Promotion;
import com.supportjobsearch.DAO.interf.IPromotionDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PromotionDao extends ImplementBase implements IPromotionDao {
    List<Promotion> promotionList;

    public static PromotionDao instance;
    public PromotionDao() {
        super();
        promotionList = getAll();
    }

    public static PromotionDao getInstance() {
        if (instance == null) {
            instance = new PromotionDao();
        }
        return instance;
    }
    public List<Promotion> getList(){
        return promotionList;
    }

    @Override
    public List<Promotion> getAll() {
        if (promotionList == null) {
            promotionList = new ArrayList<>();
        }

        if (promotionList.isEmpty()) {
            promotionList = handle.createQuery("select * from promotions")
                            .mapToBean(Promotion.class).list();
        }
        return promotionList;
    }

    @Override
    public List<Promotion> getAll(boolean force){
        log.info("Querying all promotions with force: " + force);
        if(force){
            promotionList.clear();
        }
        return getAll();
    }

    @Override
    public Promotion getById(int id) {
        for (Promotion promotion : promotionList) {
            if (promotion.getId() == id) {
                return promotion;
            }
        }
        return null;
    }

    @Override
    public Promotion getByCode(String code) {
        for (Promotion promotion : promotionList) {
            if (promotion.getCodes().equals(code)) {
                return promotion;
            }
        }
        return null;
    }

    @Override
    public int add(Promotion promotion) {
        log.info("Adding promotion: " + promotion);
        return handle.createUpdate("INSERT INTO promotions(codes, type, limitPro, valueOfPro, statusOfPro, startDate, endDate) VALUES " +
                                   "(?,?,?,?,?,?,?)")
                .bind(0, promotion.getCodes())
                .bind(1, promotion.getType())
                .bind(2, promotion.getLimitPro())
                .bind(3, promotion.getValueOfPro())
                .bind(4, promotion.getStatusOfPro())
                .bind(5, promotion.getStartDate())
                .bind(6, promotion.getEndDate())
                .execute();
    }

    @Override
    public boolean updateLimit(int newLimit) {
        return false;
    }

    @Override
    public boolean updateValue(double newValue) {
        return false;
    }

    @Override
    public boolean updateStatus(String newStatus) {
        return false;
    }

    @Override
    public boolean updateStartDate(LocalDateTime newStartDate) {
        return false;
    }

    @Override
    public boolean updateEndDate(LocalDateTime newEndDate) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
