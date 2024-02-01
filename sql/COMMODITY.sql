USE testmall;

DROP TABLE IF EXISTS dbo.orderlists;
DROP TABLE IF EXISTS dbo.userorders;
DROP TABLE IF EXISTS dbo.carts;
DROP TABLE IF EXISTS dbo.userinfo;
DROP TABLE IF EXISTS dbo.commodities;
DROP TABLE IF EXISTS dbo.commodity_tags;
DROP TABLE IF EXISTS dbo.manager;

CREATE TABLE dbo.commodity_tags (						/*商品種類表*/
	CommoditySubTag VARCHAR(20) NOT NULL PRIMARY KEY,	/*小類*/
	CommodityMainTag VARCHAR(20) NOT NULL				/*大類*/
);

CREATE INDEX IX_main_tags ON commodity_tags(CommodityMainTag);

CREATE TABLE dbo.commodities (					/*商品表*/
	CommodityID BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,	/*商品編號*/
	CommodityName VARCHAR(30) NULL,				/*商品名稱*/
	CommodityQty INT NULL,						/*庫存數量*/
	CommodityPrice INT NULL,					/*價格*/
	CommodityTag VARCHAR(20) NULL,				/*商品分類*/
	CommodityImgPath VARCHAR(200) NULL,			/*圖片位址*/
	CommodityDetail VARCHAR(500) NULL,			/*商品詳細資訊*/
	CommoditySaleFlag BIT NULL DEFAULT 1,		/*是否銷售中*/
	CommodityDiscount BIT NULL DEFAULT 0,		/*是否有折扣*/
	CommodityDisRate NUMERIC(1) NULL DEFAULT 0,	/*幾折*/
	FOREIGN KEY (CommodityTag) REFERENCES commodity_tags(CommoditySubTag)
);

CREATE INDEX IX_commodities_tag ON commodities(CommodityTag);
CREATE INDEX IX_commodities_name ON commodities(CommodityName);
CREATE INDEX IX_commodities_sale ON commodities(CommoditySaleFlag);
CREATE INDEX IX_commodities_discount ON commodities(CommodityDiscount);

CREATE TABLE dbo.userinfo (							/*使用者表*/
	UserAccount VARCHAR(30) NOT NULL PRIMARY KEY,	/*帳號*/
	UserPassword CHAR(32) DEFAULT '' NOT NULL,		/*密碼*/
	UserSalt CHAR(60) DEFAULT '' NOT NULL,			/*鹽值*/
	UserName VARCHAR(20) NULL,						/*姓名*/
	UserPhone VARCHAR(20) NULL,						/*電話*/
	UserEmail VARCHAR(50) NULL,						/*email*/
	UserAddress VARCHAR(100) NULL,					/*通訊地址*/
	UserMsg VARCHAR(200) NULL						/*訊息*/
);

CREATE TABLE dbo.carts (							/*購物車表*/
	CartSeq INT IDENTITY(1,1) NOT NULL PRIMARY KEY,	/*購物車流水號*/
	CartAccount VARCHAR(30) NOT NULL,				/*帳號*/
	CartCommodityID BIGINT NOT NULL,				/*商品編號*/
	CartQty INT NULL,								/*購買數量*/
	FOREIGN KEY (CartAccount) REFERENCES userinfo(UserAccount),
	FOREIGN KEY (CartCommodityID) REFERENCES commodities(CommodityID)
);

CREATE INDEX IX_carts_account ON carts(CartAccount);

CREATE TABLE dbo.userorders (						/*訂單表*/
	OrderNo BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,/*訂單編號*/
	OrderAccount VARCHAR(30) NOT NULL,				/*帳號*/
	OrderDate DATETIME NULL,						/*日期*/
	OrderTotal BIGINT NULL,                         /*總金額*/
	FOREIGN KEY (OrderAccount) REFERENCES userinfo(UserAccount)
);

CREATE INDEX IX_orders_account ON userorders(OrderAccount);

CREATE TABLE dbo.orderlists (						/*訂單商品資訊*/
	OrderSeq BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,/*訂單流水號*/
	OrderNo BIGINT NOT NULL,						/*訂單編號*/
	OrderCommodityID BIGINT NOT NULL,				/*商品編號*/
	OrderQty INT NULL,								/*數量*/	
	OrderPrice BIGINT NULL,							/*總金額*/
	OrderReturn BIT NULL,							/*此商品是否退貨*/
	FOREIGN KEY (OrderCommodityID) REFERENCES commodities(CommodityID),
	FOREIGN KEY (OrderNo) REFERENCES userorders(OrderNo)
);

CREATE INDEX IX_orderlist_no ON orderlists(OrderNo);

CREATE TABLE dbo.manager (							/*後台管理者表*/
	ManID BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,   /*ID*/
	ManAccount VARCHAR(30) NOT NULL UNIQUE,			/*帳號*/
	ManPassword CHAR(32) DEFAULT '' NOT NULL,		/*密碼*/
	ManSalt CHAR(60) DEFAULT '' NOT NULL,			/*鹽質*/
	ManName VARCHAR(20) NULL,						/*姓名*/
	ManPhone VARCHAR(20) NULL,						/*電話*/
	ManEmail VARCHAR(50) NULL,						/*email*/
	ManAddress VARCHAR(100) NULL,					/*通訊地址*/
	ManMsg VARCHAR(200) NULL						/*備註*/
);

CREATE INDEX IX_manager_account ON manager(ManAccount);

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag) 
VALUES ('水果', '食物');

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag) 
VALUES ('狗', '寵物');

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag) 
VALUES ('蔬菜', '食物');

-- INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
-- VALUES ('蘋果', 50, 25, '水果', 'images/apple.jpg', '蘋果 Detail...');

-- INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
-- VALUES ('西瓜', 100, 90, '水果', 'images/watermelon.png', '西瓜 Detail...');

-- INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
-- VALUES ('檸檬', 100, 20, '水果', 'images/lemon.jpg', '檸檬 Detail...');

-- INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
-- VALUES ('葡萄', 100, 100, '水果', 'images/grape.jpg', '葡萄 Detail...');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('博美1', 100, 100, '狗', 'static/images/博美1.jpg', '博美1 Detail...');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('博美2', 100, 100, '狗', 'static/images/博美2.jpg', '博美2 Detail...');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('博美3', 100, 100, '狗', 'static/images/博美3.jpeg', '博美3 Detail...');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('博美4', 100, 100, '狗', 'static/images/博美1.jpg', '博美4 Detail...');

INSERT INTO userinfo (UserAccount, UserPassword, UserName, UserPhone, UserEmail, UserAddress, UserMsg)
VALUES ('user1', 'user1', '測試員', '01234567890', 'user1@mail', '台北', 'User message...');

INSERT INTO carts (CartAccount, CartCommodityID, CartQty)
VALUES ('user1', 1, 2);

INSERT INTO userorders (OrderAccount, OrderDate, OrderTotal)
VALUES ('user1', '20240101 09:50:24', 50);

INSERT INTO orderlists (OrderNo, OrderCommodityID, OrderQty, OrderPrice, OrderReturn)
VALUES (1, 1, 2, 200, 0);

INSERT INTO orderlists (OrderNo, OrderCommodityID, OrderQty, OrderPrice, OrderReturn)
VALUES (1, 2, 1, 10, 0);

INSERT INTO manager (ManAccount, ManPassword, ManSalt, ManName, ManPhone, ManEmail, ManAddress, ManMsg)
VALUES ('admin', 'fc0f0078f09b07fe5fd45428d6aaf12f', '9bf0684484d740a0b03eb84218d84b82', '管理員', '0912345678', 'admin@gmail.com', '台北市', 'cc');