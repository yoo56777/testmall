package com.testmall.Controller;

import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
import com.testmall.Model.Userinfo;
import com.testmall.Service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserinfoService userService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Userinfo> getAll(){
        return userService.queryAll();
    }

    @GetMapping("/getByAccount")
    @ResponseBody
    public Userinfo getByAccount(@RequestParam String account){
        return userService.queryByAccount(account);
    }

    @PostMapping("/createUser")
    @ResponseBody
    public String createUser(@RequestBody(required = false) Userinfo userinfo){
        return userService.createUser(userinfo);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody(required = false) Userinfo userinfo){
        return userService.updateUser(userinfo);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody(required = false) List<String> accounts){
        return userService.deleteUser(accounts);
    }
}
