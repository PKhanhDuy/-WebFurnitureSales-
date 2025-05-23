package com.supportjobsearch.DAO.interf;


import com.supportjobsearch.Bean.Permission;

import java.util.List;

public interface IPermissionDao {
    //    Lay tat ca cac permission
    List<Permission> getAllPermissions();

    //    Lay mot permission theo id
    Permission getPermissionById(int id);

    //    Them mot permission
    boolean addPermission(Permission permission);

    //    Cap nhat ten cua permission theo id
    boolean updateName(int id, String newName);

    //    Xoa mot permission
    boolean deletePermission(int id);
}
