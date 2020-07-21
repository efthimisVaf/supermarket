package com.utrechtfour.supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getStyledPage(Model model) {
        model.addAttribute("name", "Supermarket API");
        return "supermarket.md";
    }


}
