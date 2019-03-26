package com.privilege.service.impl;

import com.privilege.dao.UserDao;
import com.privilege.dao.impl.UserDaoImpl;
import com.privilege.service.UserService;
import com.privilege.utils.MD5Util;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public int login(String username, String password) {
        //MD5加密
        password = MD5Util.MD5Encoding(password);
        System.out.println(password);
        //调用dao层login方法
        int rows = userDao.login(username, password);
        return rows;
    }
}
