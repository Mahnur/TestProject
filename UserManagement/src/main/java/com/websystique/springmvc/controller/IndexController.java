package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value =  { "/", "/login" }, method = RequestMethod.GET)
    public String getIndexPage() {
        return "Login";
    }
    
    @RequestMapping(value =  "/test" , method = RequestMethod.GET)
    public String getTest() {
        return "UserManagement";
    }
    
    @RequestMapping(value =  "/admin" , method = RequestMethod.GET)
    public String getAdminPage() {
        return "Admin";
    }
    
    @RequestMapping(value =  "/createUser" , method = RequestMethod.GET)
    public String getCreateUserPage() {
        return "CreateUser";
    }
    
    @RequestMapping(value =  "/userManagement" , method = RequestMethod.GET)
    public String getUserManagementPage() {
        return "UserManagement";
    }
    
    @RequestMapping(value =  "/editUser" , method = RequestMethod.GET)
    public String getEditUserPage() {
        return "EditUser";
    }
    
    @RequestMapping(value =  "/userPage" , method = RequestMethod.GET)
    public String getUserPage() {
        return "User";
    }
    
    @RequestMapping(value =  "/repoManagement" , method = RequestMethod.GET)
    public String getRepoManagementPage() {
        return "RepoManagement";
    }
    
    @RequestMapping(value =  "/createRepo" , method = RequestMethod.GET)
    public String getCreateRepoPage() {
        return "CreateRepo";
    }

}