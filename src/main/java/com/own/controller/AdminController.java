package com.own.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @GetMapping(value = "/")
    public String admin() {
        return "<h1>Welome as a admin</h1>";
    }

    @GetMapping(value = "/message")
    public String sayHello() {
        return "<h1>Hello Admin you can do all the  things that you think.</h1>";
    }

}
