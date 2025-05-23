package com.supportjobsearch.DAO.iml;


import com.supportjobsearch.Bean.Permission;
import com.supportjobsearch.DAO.interf.IPermissionDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionDao extends ImplementBase implements IPermissionDao {
    List<Permission> permissions = new ArrayList<>();

    private static PermissionDao instance;

    public PermissionDao() {
        super();
        getAllPermissions();
    }

    public static PermissionDao getInstance() {
        if (instance == null) {
            instance = new PermissionDao();
        }
        return instance;
    }

    @Override
    public List<Permission> getAllPermissions() {
        if (permissions.isEmpty()) {
            permissions = handle.createQuery("SELECT * FROM permission")
                    .mapToBean(Permission.class)
                    .list();
        }

        return permissions;
    }

    public List<Permission> getAllPermissions(boolean force) {
        log.info("Querying permissions with force: " + force);

        if (force) {
            permissions.clear();
        }
        permissions = getAllPermissions();
        return permissions;
    }

    public Map<Integer, String> getAllPermissionsMap() {
        log.info("Querying permissions map");

        if (permissions.isEmpty()) {
            permissions = handle.createQuery("SELECT * FROM permission")
                    .mapToBean(Permission.class)
                    .list();
        }

        Map<Integer, String> map = new HashMap<>();
        for (Permission permission : permissions) {
            map.put(permission.getId(), permission.getPermissionName());
        }

        return map;
    }

    @Override
    public Permission getPermissionById(int id) {
        return null;
    }

    @Override
    public boolean addPermission(Permission permission) {
        return false;
    }

    @Override
    public boolean updateName(int id, String newName) {
        return false;
    }

    @Override
    public boolean deletePermission(int id) {
        return false;
    }

    public static void main(String[] args) {
        var a = new PermissionDao();
        var b = a.getAllPermissions(true);
        System.out.println(b);
    }
}
