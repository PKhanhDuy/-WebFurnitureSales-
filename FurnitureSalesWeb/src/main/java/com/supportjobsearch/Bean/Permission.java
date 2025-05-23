package com.supportjobsearch.Bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Permission implements Serializable {
    private int id;
    private String permissionName;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

    public Permission() {}

    public Permission(int id, String permissionName, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.id = id;
        this.permissionName = permissionName;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Permission {id=" + id +
                ", permissionName=" + permissionName +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate + "}";
    }
}
