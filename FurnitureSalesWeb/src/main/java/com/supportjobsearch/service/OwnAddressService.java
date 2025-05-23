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

    public List<OwnAddress> getOwnAddress(int id) {
        log.info("OwnAddressService getOwnAddress...");
        return ownAddressDao.getOwnAddress(id);
    }

    public void updateOwnAddress(String fullName, String phoneNum, String princible, String fullAddress, int userId, int addressId) {
        this.ownAddressDao.update(fullName, phoneNum, princible, fullAddress, userId, addressId);
    }
}
