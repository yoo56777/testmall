package com.testmall.Model;

public class CommodityTag {
    private String commoditySubTag;
    private String commodityMainTag;

    public String getCommoditySubTag(){
        return commoditySubTag;
    }

    public void setCommoditySubTag(String tag){
        this.commoditySubTag = tag;
    }

    public String getCommodityMainTag(){
        return commodityMainTag;
    }

    public void setCommodityMainTag(String tag){
        this.commodityMainTag = tag;
    }

    public CommodityTag(String subTag, String mainTag){
        commoditySubTag = subTag;
        commodityMainTag = mainTag;
    }
}
