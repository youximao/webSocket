package com.mytest.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/11/12 0012.
 */
@Entity
@Table(name = "seruserrole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    int id;

    @Column(name = "userId")
    int userId;

    @Column(name = "roleId")
    int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
