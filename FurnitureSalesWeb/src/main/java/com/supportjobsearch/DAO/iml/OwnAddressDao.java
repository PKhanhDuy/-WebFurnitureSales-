package com.supportjobsearch.DAO.iml;

import com.supportjobsearch.Bean.Address;
import com.supportjobsearch.Bean.OwnAddress;
import com.supportjobsearch.Bean.User;
import com.supportjobsearch.enums.Gender;
import com.supportjobsearch.enums.StatusUser;
import com.supportjobsearch.DAO.interf.IOwnAddressDao;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

public class OwnAddressDao extends ImplementBase implements IOwnAddressDao {
    public OwnAddressDao() {

    }

    @Override
    public int recordSize() {
        return 0;
    }

    @Override
    public List<OwnAddress> getOwnAddress(int id) {
        return db.getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT DISTINCT " +
                                        "own_address.userID AS own_userID, " +
                                        "own_address.addressID AS own_addressID, " +
                                        "users.id AS user_id, " +
                                        "users.username AS user_username, " +
                                        "users.fullName AS user_fullName, " +
                                        "users.gender AS user_gender, " +
                                        "users.pass AS user_pass, " +
                                        "users.email AS user_email, " +
                                        "users.phoneNum AS user_phoneNum, " +
                                        "users.statusUser AS user_statusUser, " +
                                        "users.createUser AS user_createUser, " +
                                        "users.avatar AS user_avatar, " +
                                        "users.roleID AS user_roleID, " +
                                        "address.id AS address_id, " +
                                        "address.princible AS address_princible, " +
                                        "address.street AS address_street, " +
                                        "address.fullAddress AS address_fullAddress " +
                                        "FROM own_address " +
                                        "JOIN users ON own_address.userID = users.id " +
                                        "JOIN address ON own_address.addressID = address.id " +
                                        "WHERE users.id = :userId")
                        .bind("userId", id) // Bind the userId to the query
                        .reduceRows(new LinkedHashMap<String, OwnAddress>(), (map, rowView) -> {
                            // Create a composite key based on userID and addressID
                            String ownAddressKey = rowView.getColumn("own_userID", Integer.class) + "-" +
                                    rowView.getColumn("own_addressID", Integer.class);

                            OwnAddress ownAddress = map.computeIfAbsent(ownAddressKey, key -> {
                                OwnAddress oa = new OwnAddress();
                                oa.setUserID(rowView.getColumn("own_userID", Integer.class));
                                oa.setAddressID(rowView.getColumn("own_addressID", Integer.class));

                                // Mapping User
                                User user = new User();
                                user.setId(rowView.getColumn("user_id", Integer.class));
                                user.setUsername(rowView.getColumn("user_username", String.class));
                                user.setFullName(rowView.getColumn("user_fullName", String.class));
                                user.setGender(rowView.getColumn("user_gender", Gender.class));
                                user.setPass(rowView.getColumn("user_pass", String.class));
                                user.setEmail(rowView.getColumn("user_email", String.class));
                                user.setPhoneNum(rowView.getColumn("user_phoneNum", String.class));
                                user.setStatusUser(rowView.getColumn("user_statusUser", StatusUser.class));
                                user.setCreateUser(rowView.getColumn("user_createUser", LocalDateTime.class));
                                user.setAvatar(rowView.getColumn("user_avatar", String.class));
                                user.setRoleID(rowView.getColumn("user_roleID", Integer.class));

                                oa.setUser(user);

                                // Mapping Address
                                Address address = new Address();
                                address.setId(rowView.getColumn("address_id", Integer.class));
                                address.setPrincible(rowView.getColumn("address_princible", String.class));
                                address.setStreet(rowView.getColumn("address_street", String.class));
                                address.setFullAddress(rowView.getColumn("address_fullAddress", String.class));

                                oa.setAddress(address);

                                return oa;
                            });

                            return map;
                        })
                        .values()
                        .stream()
                        .toList()
        );
    }

    @Override
    public void update(String fullName, String phoneNum, String princible, String fullAddress, int userId, int addressId) {
        db.getJdbi().useTransaction(handle -> {
            // Cập nhật thông tin người dùng
            handle.createUpdate("UPDATE users SET fullName = :fullName, phoneNum = :phoneNum WHERE id = :userId")
                    .bind("fullName", fullName)
                    .bind("phoneNum", phoneNum)
                    .bind("userId", userId)
                    .execute();

            // Cập nhật thông tin địa chỉ
            handle.createUpdate("UPDATE address SET princible = :princible, fullAddress = :fullAddress WHERE id = :addressId")
                    .bind("princible", princible)
                    .bind("fullAddress", fullAddress)
                    .bind("addressId", addressId)
                    .execute();
        });
    }



    public static void main(String[] args) {
        IOwnAddressDao dao = new OwnAddressDao();
        System.out.println(dao.getOwnAddress(1));
    }

}
