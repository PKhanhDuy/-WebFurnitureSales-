package com.supportjobsearch.Bean;

public class AssignPers {
    private int permissionID;
    private int roleID;

    public AssignPers() {}
    public AssignPers(int permissionID, int roleID) {
        this.permissionID = permissionID;
        this.roleID = roleID;
    }

    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
