package com.privilege.service;

import com.privilege.po.Privileges;

import java.util.List;

public interface PrivilegeService {
    //查询权限
    List<Privileges> getPrivilegeList(int roleid);
    List<Privileges> getPrivilegesList(int roleid);
    List<Privileges> getALLPrivilegesList();
    //授权
    int deletePrivilegesByRoleid(String [] deleteroleids,int roleid);
    int addPrivilegesByRoleid(String [] addroleids,int roleid);
    //添加权限
    int addPrivilege(Privileges privilege);
}
