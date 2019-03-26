package com.privilege.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.privilege.po.Privileges;
import com.privilege.service.PrivilegeService;
import com.privilege.service.impl.PrivilegeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/PrivilegeServlet.do")
public class PrivilegeServlet extends HttpServlet {
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
                toPrivilege(req,resp);
            break;
            case "2":
                addPrivilege(req,resp);
            break;
            case "3":
                selectPrivilege(req,resp);
            break;
            default:
                System.out.println("操作有误");
            break;
        }



    }

    private void selectPrivilege(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调
        List<Privileges> allPrivilegesList = privilegeService.getALLPrivilegesList();
        //存
        HttpSession session = req.getSession();
        session.setAttribute("allPrivilegesList",allPrivilegesList);
        //转
        req.getRequestDispatcher("/jsp/privilege_list.jsp").forward(req,resp);
    }

    private void addPrivilege(HttpServletRequest req, HttpServletResponse resp) {
        //取
        String p_name = req.getParameter("p_name");
        String p_url = req.getParameter("p_url");
        String p_description = req.getParameter("p_description");
        System.out.println(p_name);
        //调
        Privileges privileges = new Privileges(0, p_name, p_url, p_description);

        int rows = privilegeService.addPrivilege(privileges);
        if (rows>0){
            //跳转到查看页面
            System.out.println(rows);
        }
    }

    private void toPrivilege(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int roleid = Integer.parseInt(req.getParameter("roleid"));
        String[] privilegedeleteids = req.getParameter("privilegedeleteid").split(",");
        String[] privilegeaddids = req.getParameter("privilegeaddid").split(",");

        //调
        int derows = privilegeService.deletePrivilegesByRoleid(privilegedeleteids, roleid);

        int addrows = privilegeService.addPrivilegesByRoleid(privilegeaddids, roleid);

        JSONObject jsonObject= new JSONObject();
        if (derows>0&&addrows>0){
            jsonObject.put("result",1);
        }else{
            jsonObject.put("result",0);
        }
        String result = JSON.toJSONString(jsonObject);
        PrintWriter writer = resp.getWriter();
        writer.write(result);
    }
}
