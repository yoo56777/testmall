package com.testmall.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmall.Tools.CharsetTool;

import java.io.Serializable;

public class Userinfo implements Serializable {
    @JsonIgnore
    CharsetTool cstool = new CharsetTool();
    @JsonProperty(value = "userAccount")
    String userAccount;
    @JsonProperty(value = "userPassword")
    String userPassword;
    @JsonProperty(value = "userSalt")
    String userSalt;
    @JsonProperty(value = "userName")
    String userName;
    @JsonProperty(value = "userPhone")
    String userPhone;
    @JsonProperty(value = "userEmail")
    String userEmail;
    @JsonProperty(value = "userAddress")
    String userAddress;
    @JsonProperty(value = "userMsg")
    String userMsg;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        if (cstool.isEncoding(userAccount, "ISO-8859-1"))
            this.userAccount = cstool.iso2utf8(userAccount);
        else
            this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        if (cstool.isEncoding(userPassword, "ISO-8859-1"))
            this.userPassword = cstool.iso2utf8(userPassword);
        else
            this.userPassword = userPassword;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        if (cstool.isEncoding(userSalt, "ISO-8859-1"))
            this.userSalt = cstool.iso2utf8(userSalt);
        else
            this.userSalt = userSalt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (cstool.isEncoding(userName, "ISO-8859-1"))
            this.userName = cstool.iso2utf8(userName);
        else
            this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        if (cstool.isEncoding(userPhone, "ISO-8859-1"))
            this.userPhone = cstool.iso2utf8(userPhone);
        else
            this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        if (cstool.isEncoding(userEmail, "ISO-8859-1"))
            this.userEmail = cstool.iso2utf8(userEmail);
        else
            this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        if (cstool.isEncoding(userAddress, "ISO-8859-1"))
            this.userAddress = cstool.iso2utf8(userAddress);
        else
            this.userAddress = userAddress;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        if (cstool.isEncoding(userMsg, "ISO-8859-1"))
            this.userMsg = cstool.iso2utf8(userMsg);
        else
            this.userMsg = userMsg;
    }

    public Userinfo() {
        this.userAccount = "";
        this.userPassword = "";
        this.userSalt = "";
        this.userName = "";
        this.userPhone = "";
        this.userEmail = "";
        this.userAddress = "";
        this.userMsg = "";
    }

    public Userinfo(String userAccount, String userPassword, String userSalt, String userName, String userPhone, String userEmail, String userAddress, String userMsg) {
        setUserAccount(userAccount);
        setUserPassword(userPassword);
        setUserSalt(userSalt);
        setUserName(userName);
        setUserPhone(userPhone);
        setUserEmail(userEmail);
        setUserAddress(userAddress);
        setUserMsg(userMsg);
    }
}
