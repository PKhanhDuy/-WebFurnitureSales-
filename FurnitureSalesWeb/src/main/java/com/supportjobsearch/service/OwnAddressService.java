package com.supportjobsearch.service;

import com.supportjobsearch.Bean.OwnAddress;
import com.supportjobsearch.DAO.iml.OwnAddressDao;

import java.util.List;

public class OwnAddressService extends ServiceBase {
    private static OwnAddressService instance;
    OwnAddressDao ownAddressDao = new OwnAddressDao();

    public static OwnAddressService getInstance() {
        if (instance == null) {
            instance = new OwnAddressService();
        }
        return instance;
    }

    @Override
    public void init() {
        log.info("OwnAddressService init...");
        if (ownAddressDao == null) {
            ownAddressDao = new OwnAddressDao();
        }
    }

    // 9.1.7 Lấy thông tin nhận hàng của user
    public List<OwnAddress> getOwnAddress(int id) {
        log.info("OwnAddressService getOwnAddress...");
        return ownAddressDao.getOwnAddress(id);
    }

}
