package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.Bean.OwnAddress;

import java.util.List;

public interface IOwnAddressDao {
    int recordSize();

    List<OwnAddress> getOwnAddress(int id);

    void update(String fullName, String phoneNum, String princible, String fullAddress, int userId, int addressId);
}
