package com.supportjobsearch.DAO.iml;


import com.supportjobsearch.Bean.User;
import com.supportjobsearch.DAO.interf.IUsersDao;
import com.supportjobsearch.InsertData;
import com.supportjobsearch.enums.StatusUser;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends ImplementBase implements IUsersDao {
    List<User> allUser = new ArrayList<>();

    public UserDao() {
        super();
    }

    @Override
    public List<User> getAllUsers() {

        if (allUser.isEmpty()) {
            allUser = handle.createQuery("SELECT * FROM users")
                    .mapToBean(User.class)
                    .list();
        }
        return allUser;
    }

    public List<User> getAllUsers(boolean forced) {
        log.info("Query all user with force: " + forced);
        if (forced) {
            allUser = handle.createQuery("SELECT * FROM users")
                    .mapToBean(User.class)
                    .list();
        } else {
            allUser = getAllUsers();
        }
        return allUser;

    }

    public User findIDByEmail(String email) {
        var u = handle.createQuery("select * from users where users.email = email").mapToBean(User.class).first();
        return u;
    }

    @Override
    public User getUserById(int id) {
        var a = handle.createQuery("select * from users where id = " + id)
                .mapToBean(User.class)
                .first();

        return a;
    }

    @Override
    public User addUser(User user) {
        log.info("Add user: " + user);
        return db.jdbi.withHandle(handle -> {
            int id = handle.createUpdate("INSERT INTO  users(username, fullName, gender, pass, email, phoneNum, statusUser, createUser, avatar, roleID) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)")
                    .bind(0, user.getUsername())
                    .bind(1, user.getFullName())
                    .bind(2, user.getGender())
                    .bind(3, InsertData.hashPassword(user.getPass()))
                    .bind(4, user.getEmail())
                    .bind(5, user.getPhoneNum())
                    .bind(6, user.getStatusUser())
                    .bind(7, user.getCreateUser())
                    .bind(8, user.getAvatar())
                    .bind(9, user.getRoleID())
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class).one();
            user.setId(id);
            return user;
        });
    }

    @Override
    public User updateInfoUserNameById(int id, String userName, String fullName, String gender, String
            email, String phone) {
        return db.jdbi.withHandle(handle -> handle.createUpdate("UPDATE users SET userName = :userName, fullName = :fullName, gender = :gender, email = :email, phone = :phone WHERE id = :id")
                .bind("username", userName)
                .bind("fullName", fullName)
                .bind("gender", gender)
                .bind("email", email)
                .bind("phone", phone)
                .executeAndReturnGeneratedKeys("id")
                .mapToBean(User.class)
                .findOne()
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " not found")));
    }

    @Override
    public User updateAvatarById(int id, String avatar) {
        return null;
    }

    @Override
    public boolean updatePasswordById(int id, String password) {
        return db.getJdbi().withHandle(
                        handle -> handle.createUpdate("update users set password :password where id :id"))
                .bind(0, id)
                .bind(1, InsertData.hashPassword(password)).execute() > 0;
    }

    @Override
    public boolean deleteUserById(int id) {
        return db.jdbi.withHandle(
                handle -> handle.createUpdate("delete * from users where id=:id")).bind(0, id).execute() > 0;
    }

    @Override
    public User findUser(String username) {
        return db.jdbi.withHandle(handle ->
                handle.createQuery("select * from users where username = :username")
                        .bind("username", username)
                        .mapToBean(User.class).findOne().orElse(null));
    }

    public int getTotalUsers(boolean force) {
        log.info("Query total user with force: " + force);
        getAllUsers(force);
        return allUser.size();
    }

    @Override
    public int getTotalEmployee(boolean force) {
        log.info("Query total employee with force: " + force);
        var users = getAllUsers(force);

        int count = 0;
//        for (User user : users)
//            if (user.getRoleID() == RolePermission.EMPLOYEE.getValue())
//                count++;

        return count;
    }

    @Override
    public int getTotalAdmin(boolean force) {
        log.info("Query total admin with force: " + force);
        var users = getAllUsers(force);

        int count = 0;
//        for (User user : users)
//            if (user.getRoleID() == RolePermission.ADMINISTRATOR.getValue())
//                count++;

        return count;
    }

    @Override
    public List<User> getAllAdmin(boolean forceUpdate) {
        log.info("Query all admin with force: " + forceUpdate);
        var users = getAllUsers(forceUpdate);

        List<User> userList = new ArrayList<>();

//        for (User user : users)
//            if (user.getRoleID() == RolePermission.ADMINISTRATOR.getValue())
//                userList.add(user);

        return userList;
    }

    @Override
    public List<User> getAvailableUsers(boolean forceUpdate) {
        log.info("Querying available users with force: " + forceUpdate);

        List<User> availableUsers = new ArrayList<>();

        allUser = getAllUsers(forceUpdate);

        allUser.forEach(u -> {
            if (u.getStatusUser().equals(StatusUser.ENABLE)) {
                availableUsers.add(u);
            }
        });

        return availableUsers;
    }

    @Override
    public boolean updateUser(User user) {
        log.info("Updating user: " + user);
        int c = 0;
        if (user.getPass().equals("")) {
            c = handle.createUpdate("UPDATE users SET " +
                            "username = ?, fullName = ?, gender = ?, email = ?, phoneNum = ?, statusUser = ?, avatar = ?, roleID = ?, public_key = ? WHERE id = ?")
                    .bind(0, user.getUsername())
                    .bind(1, user.getFullName())
                    .bind(2, user.getGender())
                    .bind(3, user.getEmail())
                    .bind(4, user.getPhoneNum())
                    .bind(5, user.getStatusUser())
                    .bind(6, user.getAvatar())
                    .bind(7, user.getRoleID())
                    .bind(8, user.getPublic_key())
                    .bind(9, user.getId())
                    .execute();
        } else {
            c = handle.createUpdate("UPDATE users SET " +
                            "username = ?, fullName = ?, gender = ?, pass = ?, email = ?, phoneNum = ?, statusUser = ?, avatar = ?, roleID = ? WHERE id = ?")
                    .bind(0, user.getUsername())
                    .bind(1, user.getFullName())
                    .bind(2, user.getGender())
                    .bind(3, InsertData.hashPassword(user.getPass()))
                    .bind(4, user.getEmail())
                    .bind(5, user.getPhoneNum())
                    .bind(6, user.getStatusUser())
                    .bind(7, user.getAvatar())
                    .bind(8, user.getRoleID())
                    .bind(9, user.getId())
                    .execute();
        }

        return c > 0;
    }

    public static void main(String[] args) {
        UserDao dao = new UserDao();
        dao.log.info("test");
    }
}
