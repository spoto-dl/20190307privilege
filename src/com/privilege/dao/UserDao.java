package com.privilege.dao;

import javax.security.auth.login.LoginContext;
import java.sql.SQLException;

public interface UserDao {
    int login(String username,String password);
}
