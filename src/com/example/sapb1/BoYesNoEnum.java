package com.example.sapb1;

/**
 * SAP Business One Yes/No 枚举常量
 * 对应 BoYesNoEnum 枚举
 */
public class BoYesNoEnum {
    public static final int tNO = 0;   // 否
    public static final int tYES = 1;  // 是

    /**
     * 转换为中文字符串
     */
    public static String toChineseString(int value) {
        return (value == tYES) ? "是" : "否";
    }

    /**
     * 转换为英文字符串
     */
    public static String toEnglishString(int value) {
        return (value == tYES) ? "Yes" : "No";
    }
}