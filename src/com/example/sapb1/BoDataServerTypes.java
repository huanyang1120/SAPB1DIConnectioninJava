package com.example.sapb1;

/**
 * SAP Business One 数据库服务器类型常量
 * 对应 BoDataServerTypes 枚举
 */
public class BoDataServerTypes {
    public static final int dst_MSSQL = 1;
    public static final int dst_DB_2 = 2;
    public static final int dst_SYBASE = 3;
    public static final int dst_MSSQL2005 = 4;
    public static final int dst_MAXDB = 5;
    public static final int dst_MSSQL2008 = 6;
    public static final int dst_MSSQL2012 = 7;
    public static final int dst_MSSQL2014 = 8;
    public static final int dst_HANADB = 9;
    public static final int dst_MSSQL2016 = 10;
    public static final int dst_MSSQL2017 = 11;
    public static final int dst_MSSQL2019 = 15;
    public static final int dst_MSSQL2022 = 17;

    /**
     * 获取数据库类型名称
     */
    public static String getTypeName(int dbType) {
        switch (dbType) {
            case dst_MSSQL: return "MSSQL";
            case dst_DB_2: return "DB2";
            case dst_SYBASE: return "SYBASE";
            case dst_MSSQL2005: return "MSSQL 2005";
            case dst_MAXDB: return "MAXDB";
            case dst_MSSQL2008: return "MSSQL 2008";
            case dst_MSSQL2012: return "MSSQL 2012";
            case dst_MSSQL2014: return "MSSQL 2014";
            case dst_HANADB: return "HANA DB";
            case dst_MSSQL2016: return "MSSQL 2016";
            case dst_MSSQL2017: return "MSSQL 2017";
            case dst_MSSQL2019: return "MSSQL 2019";
            case dst_MSSQL2022: return "MSSQL 2022";
            default: return "Unknown (" + dbType + ")";
        }
    }
}