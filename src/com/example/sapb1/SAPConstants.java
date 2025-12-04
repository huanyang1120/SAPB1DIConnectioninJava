package com.example.sapb1;

/**
 * SAP Business One 常量工具类
 * 集中管理常用的枚举常量
 */
public class SAPConstants {

    /**
     * 数据库服务器类型
     */
    public static class DataServerTypes {
        public static final int MSSQL = 1;
        public static final int DB2 = 2;
        public static final int SYBASE = 3;
        public static final int MSSQL2005 = 4;
        public static final int MAXDB = 5;
        public static final int MSSQL2008 = 6;
        public static final int MSSQL2012 = 7;
        public static final int MSSQL2014 = 8;
        public static final int HANADB = 9;
        public static final int MSSQL2016 = 10;
        public static final int MSSQL2017 = 11;
        public static final int MSSQL2019 = 15;
        public static final int MSSQL2022 = 17;
    }

    /**
     * 物料类型
     */
    public static class ItemTypes {
        public static final int ITEMS = 0;          // 标准物料
        public static final int LABOR = 1;          // 人工
        public static final int TRAVEL = 2;         // 差旅
        public static final int FIXED_ASSETS = 3;   // 固定资产
    }

    /**
     * Yes/No 枚举
     */
    public static class YesNo {
        public static final int NO = 0;
        public static final int YES = 1;

        public static String toString(int value) {
            return (value == YES) ? "是" : "否";
        }
    }

    /**
     * 业务对象类型
     */
    public static class ObjectTypes {
        public static final int oItems = 4;
        public static final int oBusinessPartners = 2;
        public static final int oOrders = 17;
        public static final int oInvoices = 13;
        public static final int oPurchaseOrders = 22;
        // 根据需要添加更多...
    }

    /**
     * 卡片类型
     */
    public static class CardTypes {
        public static final int cCustomer = 0;   // 客户
        public static final int cSupplier = 1;   // 供应商
        public static final int cLead = 2;       // 潜在客户
    }

    /**
     * 文档状态
     */
    public static class DocumentStatus {
        public static final int bost_Open = 0;   // 打开
        public static final int bost_Close = 1;  // 关闭
        public static final int bost_Paid = 2;   // 已付款
        public static final int bost_Delivered = 3; // 已交付
    }
}