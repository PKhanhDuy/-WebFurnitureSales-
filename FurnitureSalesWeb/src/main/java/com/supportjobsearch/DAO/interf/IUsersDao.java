package com.supportjobsearch.DAO.interf;


import com.supportjobsearch.Bean.User;

import java.util.List;

public interface IUsersDao {
    //Lay ra tat ca users
    List<User> getAllUsers();

    //Lay user theo id
    User getUserById(int id);

    //Them mot user
    User addUser(User user);

    //Cap nhat cac thong tin co ban trong profile cua moi user theo id;
    User updateInfoUserNameById(int id, String userName, String fullName, String gender, String email, String phone);

    //Cap nhat avata cho user theo id
    User updateAvatarById(int id, String avatar);

    //Cap nhat mat khau cho user theo id
    boolean updatePasswordById(int id, String password);

    //Xoa users theo id
    boolean deleteUserById(int id);

    //Tim user theo userName cho phan dang nhap
    User findUser(String username);

    int getTotalUsers(boolean force);
    int getTotalEmployee(boolean force);
    int getTotalAdmin(boolean force);

    List<User> getAllAdmin(boolean forceUpdate);

    List<User> getAvailableUsers(boolean forceUpdate);

    boolean updateUser(User user);
}
