package com.mytest.DAO;

import com.mytest.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/11/13 0013.
 */
public interface UserLoginReg extends JpaRepository<User,String> {

    public User findByName(String name);

}
