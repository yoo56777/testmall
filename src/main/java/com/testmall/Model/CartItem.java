package com.testmall.Model;

import java.util.List;

public class CartItem {
    private Long cartSeq;    //購物車流水號
    private String cartAccount;    //使用者帳號

    //修改-Begin
    private Long cartCommodityID;
    //private Long cartCommodityID;
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

    public CartItem(Long cartSeq, String cartAccount, Long cartCommodityID, int cartQty) {
        if (cartQty <= 0){
            throw new IllegalArgumentException("這裡沒東西 cartQty must be greater than 0");
        }
        // 基本參數合法性檢查
        this.cartSeq = cartSeq;
        this.cartAccount = cartAccount;
        this.cartCommodityID = cartCommodityID;
        this.cartQty = cartQty;
        // 確保已被正確初始化
    }

    // Getters and setters
    public Long getCartSeq() {
        return cartSeq;
    }

    public void setCartSeq(Long cartSeq) {
        this.cartSeq = cartSeq;
    }

    public String getCartAccount() {
        return cartAccount;
    }

    public void setCartAccount(String cartAccount) {
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
