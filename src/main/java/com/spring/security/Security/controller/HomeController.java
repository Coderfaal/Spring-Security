package com.spring.security.Security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/root")
public class HomeController {
    @GetMapping("/useradmin")
    public String userAdminPoint(HttpServletRequest request){return "~~WELCOME PEOPLE~~"+request.getSession().getId();}

    @GetMapping("/all")
    public String commonEndPoint(){
        return "~WELCOME~";
    }
    @GetMapping("/inbox")
    public String userEndPoint(HttpServletRequest request){
        return "~WELCOME USER~"+request.getSession().getId();
    }

    @GetMapping("/adminOffice")
    public String adminEndPoint(HttpServletRequest request){
        return "~WELCOME ADMIN~"+request.getSession().getId();
    }













}
