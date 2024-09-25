package com.tads.me.controller;

@RestController
@RequestMapping("/test")
public class testController {


    @GetMapping
    public String test(){
        return "test";
    }
    
}
