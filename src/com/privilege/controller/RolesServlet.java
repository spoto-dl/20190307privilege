package com.privilege.controller;

import com.privilege.po.Privileges;
import com.privilege.po.Roles;
import com.privilege.service.PrivilegeService;
import com.privilege.service.RolesService;
import com.privilege.service.impl.PrivilegeServiceImpl;
import com.privilege.service.impl.RolesServiceImpl;
import com.sun.org.apache.bcel.internal.generic.Select;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/RolesServlet.do")
public class RolesServlet extends HttpServlet {
    private RolesService rolesService=new RolesServiceImpl();
    private PrivilegeService privilegeService=new PrivilegeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取
        String choose = req.getParameter("choose");
        switch (choose){
            case "1":
                selectRolesList(req,resp);
            break;
            case "2":
                selectPrivilege(req,resp);
                break;
            default:
                System.out.println("操作有误");
            break;
        }


    }
    //调用查询权限的方法
    private void selectPrivilege(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取角色名称
        String rolename = req.getParameter("rolename");
        int roleid = Integer.parseInt(req.getParameter("roleid"));
        //调用根据roleid查询已经具备权限的方法
        List<Privileges> privilegeList = privilegeService.getPrivilegeList(roleid);
        List<Privileges> privilegesList = privilegeService.getPrivilegesList(roleid);
        //存
        HttpSession session = req.getSession();
        session.setAttribute("rolename",rolename);
        session.setAttribute("roleid",roleid);
        session.setAttribute("privilegeList",privilegeList);
        session.setAttribute("privilegesList",privilegesList);
        //转
        req.getRequestDispatcher("/jsp/grant_privilege.jsp").forward(req,resp);

    }

    private void selectRolesList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调查询角色的方法
        List<Roles> rolesList = rolesService.selectRoles();
        //存
        HttpSession session = req.getSession();
        session.setAttribute("rolesList",rolesList);
        //转
        req.getRequestDispatcher("/jsp/roles_list.jsp").forward(req,resp);
    }


}
