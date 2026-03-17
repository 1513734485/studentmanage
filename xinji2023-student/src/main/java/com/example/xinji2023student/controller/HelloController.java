package com.example.xinji2023student.controller;

import com.example.xinji2023student.entity.Userer;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("*****************gpnu***********************");
        return "Hello Spring Boot!";
   }
    @GetMapping
    public String apiRoot() {
        return "API root path";
    }
   @GetMapping("/helloObject")
   public  String  helloObject(Userer user){
        user.setFirstName("ppppp");
        user.setLastName("kkkkk");
        return "Hello, " + user.getFirstName() + " " + user.getLastName()+ "!";
   }

   //路径变量
    @GetMapping({"", "/helloPathVariable/{name}"})
    public String helloPV(@PathVariable String name) {
        System.out.println("接收到一个名字"+name);
        return "Hello Spring Boot!"+name;
    }
    @GetMapping("/helloRequest")
    //@RequestParam String height
    public String helloRP(@RequestParam String name,@RequestParam String height) {
        System.out.println("接收到一个名字"+name+"身高"+height);
        return "Hello Spring Boot!"+name+"身高"+height;
        //+"身高"+height
    }
}
