package com.sparta.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Main";
    }
    @GetMapping("/detail")
    public String detail() {
        return "Detail";
    }
    @GetMapping("/edit")
    public String edit() {
        return "Edit";
    }
    @GetMapping("/write")
    public String write() {
        return "Write";
    }
}