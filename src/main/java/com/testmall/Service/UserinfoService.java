package com.testmall.Service;

import com.testmall.Dao.UserinfoDao;
import com.testmall.Model.Manager;
import com.testmall.Model.Userinfo;
import com.testmall.Tools.CharsetTool;
import com.testmall.Tools.MD5util;
import com.testmall.Tools.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoService {
    CharsetTool cstool = new CharsetTool();
    @Autowired
    UserinfoDao userDao;

    public List<Userinfo> queryAll(){
        return userDao.queryAll();
    }

    public Userinfo queryByAccount(String account){
        return userDao.queryByAccount(account);
    }

    public String createUser(Userinfo userinfo){
        if (userDao.queryByAccount(userinfo.getUserAccount())!=null)
            return "此帳號已存在!";
        else{
            String salt = UUIDutil.uuid();
            userinfo.setUserSalt(salt);
            String md5Password = MD5util.md5(userinfo.getUserPassword(), salt);
            userinfo.setUserPassword(md5Password);
            return userDao.createUser(userinfo);
        }
    }

    public String updateUser(Userinfo userinfo){
        Userinfo oldUser = userDao.queryByAccount(userinfo.getUserAccount());
        if (oldUser == null){
            cstool.pLogln("查無此帳號"+userinfo.getUserAccount(), "UserinfoService.updateUser");
            return "查無此帳號!";
        }
        if (!oldUser.getUserPassword().equals(userinfo.getUserPassword())){
            String salt = UUIDutil.uuid();
            userinfo.setUserSalt(salt);
            String md5Password = MD5util.md5(userinfo.getUserPassword(), salt);
            userinfo.setUserPassword(md5Password);
        }
        return userDao.updateUser(userinfo);
    }

    public String deleteUser(List<String> accounts){
        int rowsAffected = 0;
        int failCount = 0;
        for (String account:accounts){
            if (userDao.deleteUser(account) == 1){
                rowsAffected++;
            }else {
                failCount++;
            }
        }
        return "共刪除"+accounts.size()+"筆, 成功"+rowsAffected+"筆, 失敗"+failCount+"筆!";
    }

}
