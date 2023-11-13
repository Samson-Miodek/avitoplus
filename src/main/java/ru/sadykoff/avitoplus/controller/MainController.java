package ru.sadykoff.avitoplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;

@Controller
public class MainController {

    @Autowired
    private DispatcherServlet dispatcherServlet;

    @GetMapping
    public String index(){
        var l = dispatcherServlet.getHandlerMappings();
        System.out.println(dispatcherServlet);
        System.out.println(l);
        System.out.println(dispatcherServlet);

        return "index";
    }
    @GetMapping("/{id}")
    public String indexParam(@PathVariable Long id){
        var l = dispatcherServlet.getHandlerMappings();
        System.out.println(dispatcherServlet);
        System.out.println(l);
        System.out.println(id);
        System.out.println(dispatcherServlet);
        return "index";
    }

}
