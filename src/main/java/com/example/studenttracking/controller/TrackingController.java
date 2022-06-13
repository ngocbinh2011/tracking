package com.example.studenttracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tracking")
public class TrackingController {

    @GetMapping("")
    public String trackingGet(){
        return "tracking";
    }
}
