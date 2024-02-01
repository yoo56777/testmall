package com.testmall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.testmall.Model.Commodities;
import com.testmall.Service.CommodityService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/com")
public class ComController {
    @Autowired
    CommodityService comService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Commodities> getAll(){
        return comService.queryAll();
    }

    @GetMapping("/getById")
    @ResponseBody
    public Commodities getById(Long id){
        return comService.queryById(id);
    }

    @GetMapping("/getByTag")
    @ResponseBody
    public List<Commodities> getByTag(String tag){
        return comService.queryByTag(tag);
    }

    @PostMapping("/insertItem")
    @ResponseBody
    public String insertItem(@RequestBody(required = false) Commodities commodities){
        return comService.insertItem(commodities);
    }

    @PatchMapping("/updateItem")
    @ResponseBody
    public String updateItem(@RequestBody(required = false) Commodities commodities){
        return comService.updateItem(commodities);
    }

    @DeleteMapping("/deleteItem")
    @ResponseBody
    public String deleteItem(@RequestBody(required = false) List<Long> idList){
        return comService.deleteItem(idList);
    }
}
