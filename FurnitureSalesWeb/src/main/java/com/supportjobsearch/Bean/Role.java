package com.supportjobsearch.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {
    private int id;
    private String nameRole;
    private List<String> permission;

    public Role() {}
    public Role(int id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
        this.permission = new ArrayList<String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public List<String> getPermission() {
        return permission;
    }

    public boolean addPermission(String per) {
        if(this.permission == null)
            this.permission = new ArrayList<>();

        if(per.equals("")) return false;

        return this.permission.add(per);
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", nameRole=" + nameRole + '}';
    }
}
