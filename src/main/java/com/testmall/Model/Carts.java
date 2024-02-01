package com.testmall.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testmall.Tools.CharsetTool;

public class Carts {
    // 2024-01-22新增,用來處理網頁與OSS系統的編碼問題
    // OSS編碼為ISO-8859-1,網頁編碼UTF-8,從資料庫讀出資料要放入物件的屬性時要轉為UTF-8
    // 相反在Dao那邊要寫入資料庫時要轉為ISO-8859-1
    @JsonIgnore
    CharsetTool cstool = new CharsetTool();
    private int cartSeq;    //購物車流水號
    private String cartAccount;    //使用者帳號

    //修改-Begin
    private Long cartCommodityID;
    //private List <Commodity> commodity;
    //商品編號&商品名稱 連到commodity的CommodityID & commodityName

    //這裡不用List<Commodity>，CartItem的屬性要直接照資料表定義的去設定
    //CartSeq INT ,	                        /*購物車流水號*/
    //CartAccount VARCHAR(30) ,				/*帳號*/
    //CartCommodityID BIGINT ,				/*商品編號*/
    //CartQty INT ,							/*購買數量*/

    //一個CartItem會對應一筆資料，比如說現在有兩筆資料，從資料庫讀出來是:
    //[1, user1, 1, 2]
    //[2, user1, 2, 1]
    //到時候查詢時透過JdbcTemplate把資料塞入CartItem
    //[1, user1, 1, 2] -> [CartItem1.cartSeq, CartItem1.cartAccount, CartItem1.cartCommodityID, CartItem1.cartQty]
    //[2, user1, 2, 1] -> [CartItem2.cartSeq, CartItem2.cartAccount, CartItem2.cartCommodityID, CartItem2.cartQty]
    //如果定義的是List<Commodity>這邊就會有錯誤

    //之後若需要Commodity的資料再用CartItem的屬性去查詢，例如
    //select * from commodities where CommodityID = CartItem1.cartCommodityID;
    //這句sql語法是去查commodities的資料，where後面是查詢條件
    //然後一樣透過JdbcTemplate把資料塞入Commodity物件
    //修改-End
    private int cartQty;    //購買數量

    public Carts(int cartSeq, String cartAccount, long commodityID, int cartQty) {
        // 2024-01-22修改-B 此處應判斷傳入的參數,this.cartSeq指的是物件底下的屬性
        // this可參考https://java.4-x.tw/java-08/java-08-4
        if (cartQty <= 0){
      //if (this.cartQty <= 0){
            throw new IllegalArgumentException("這裡沒東西 cartQty must be greater than 0");
        }
        // 基本參數合法性檢查
        // 同上,要把傳入參數塞給物件屬性
        //this.cartSeq = this.cartSeq;
        //this.cartAccount = this.cartAccount;
        //this.cartCommodityID = cartCommodityID;
        //this.cartQty = this.cartQty;
        this.cartSeq = cartSeq;
        //cartAccount需做轉碼處理,所以不直接賦值
        setCartAccount(cartAccount);
        this.cartCommodityID = commodityID;
        this.cartQty = cartQty;
        // 確保已被正確初始化
        // 2024-01-22修改-E
    }

    // 2024-01-22新增 要多一個無參數建構子,給屬性預設值,不然在接收前台資料轉為物件時會壞掉
    public Carts(){
        this.cartSeq = 0;
        this.cartAccount = "";
        this.cartCommodityID = 0L;
        this.cartQty = 0;
    }

    // Getters and setters
    public int getCartSeq() {
        return cartSeq;
    }
    public void setCartSeq(int cartSeq) {
        this.cartSeq = cartSeq;
    }

    public String getCartAccount() {
        return cartAccount;
    }
    public void setCartAccount(String cartAccount) {
        // 2024-01-22新增 字串類型資料需判斷編碼
        if (cstool.isEncoding(cartAccount, "ISO-8859-1"))
            this.cartAccount= cstool.iso2utf8(cartAccount);
        else
            this.cartAccount = cartAccount;
    }

    public Long getCartCommodityID() {
        return cartCommodityID;
    }

    public void setCartCommodityID(Long cartCommodityID) {
        this.cartCommodityID = cartCommodityID;
    }

    public int getCartQty() {
        return cartQty;
    }
    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }

    // 其他可能需要的方法
}
