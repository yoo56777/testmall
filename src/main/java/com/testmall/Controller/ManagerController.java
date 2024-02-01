package com.testmall.Controller;

import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
import com.testmall.Service.CommodityService;
import com.testmall.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService manService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Manager> getAll(){
        return manService.queryAll();
    }

    @GetMapping("/getById")
    @ResponseBody
    public Manager getById(Long id){
        return manService.queryById(id);
    }

    @GetMapping("/getByAccount")
    @ResponseBody
    public Manager getByAccount(String account){
        return manService.queryByAccount(account);
    }

    @PostMapping("/createManager")
    @ResponseBody
    public String createManager(@RequestBody(required = false) Manager manager){
        return manService.createManager(manager);
    }

    @PostMapping("/updateManager")
    @ResponseBody
    public String updateManager(@RequestBody(required = false) Manager manager){
        return manService.updateManager(manager);
    }

    @PostMapping("/deleteManager")
    @ResponseBody
    public String deleteManager(@RequestBody(required = false) List<Long> idList){
        return manService.deleteManager(idList);
    }

    @PostMapping("/Login")
    @ResponseBody
    public String Login(HttpSession session, HttpServletResponse response, @RequestBody Manager manager){
        String str = "";
        switch(manService.Login(manager.getManAccount(), manager.getManPassword())){
            case 0:
                str = "登入成功!";
                session.setAttribute("manAccount", manager.getManAccount());
                break;
            case 1:
                str = "此帳號不存在!";
                response.setStatus(401);
                break;
            case 2:
                str = "密碼錯誤!";
                response.setStatus(401);
                break;
        }
        return str;
    }

    @GetMapping("/SignOut")
    @ResponseBody
    public String SignOut(HttpSession session){
        //銷毀session中的資料
        session.removeAttribute("manAccount");
        return "登出成功";
    }
}
