package com.styros.esales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by atul on 11/30/2017.
 */
@Controller
public class GreetingsController {

    @RequestMapping("/greetings")
    public String greeting(@RequestParam(value="name",required=false,defaultValue = "world")String name,Model model){
        model.addAttribute("name",name);
        return "greeting";
    }
}
