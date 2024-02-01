package com.testmall.Service;

import com.testmall.Dao.ManagerDao;
import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
import com.testmall.Tools.MD5util;
import com.testmall.Tools.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ManagerService {
    @Autowired
    ManagerDao manDao;

    public List<Manager> queryAll(){
        return manDao.queryAll();
    }

    public Manager queryById(Long id){
        return manDao.queryById(id);
    }

    public Manager queryByAccount(String account){
        return manDao.queryByAccount(account);
    }

    public String createManager(Manager manager){
        if (queryByAccount(manager.getManAccount())!=null)
            return "此帳號已存在!";
        else{
            String salt = UUIDutil.uuid();
            manager.setManSalt(salt);
            String md5Password = MD5util.md5(manager.getManPassword(), salt);
            manager.setManPassword(md5Password);
            return manDao.createManager(manager);
        }
    }

    public String updateManager(Manager manager){
        if (!manager.getManPassword().equals(manDao.queryById(manager.getManID()).getManPassword())){
            String salt = UUIDutil.uuid();
            manager.setManSalt(salt);
            String md5Password = MD5util.md5(manager.getManPassword(), salt);
            manager.setManPassword(md5Password);
        }
        return manDao.updateManager(manager);
    }

    public String deleteManager(List<Long> idList){
        return manDao.deleteManager(idList);
    }

    public int Login(String account, String password){
        Manager manager = queryByAccount(account);
        if (manager==null){
            //帳號不存在
            return 1;
        }else {
            String salt = manager.getManSalt().trim();
            String md5Password = MD5util.md5(password, salt);

            if (manager.getManPassword().equals(md5Password))
                //登入成功
                return 0;
            else
                //密碼錯誤
                return 2;
        }
    }
}
