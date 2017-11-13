package com.mytest.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/11/12 0012.
 */
@Entity
@Table(name = "serrole")
public class Role {
    @Id
    @Column(name = "id")
    int id;

    @Column(name ="role")
    String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
