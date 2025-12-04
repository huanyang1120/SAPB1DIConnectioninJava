package com.example.sapb1;

/**
 * SAP Business One 物料类型常量
 * 对应 BoItemTypes 枚举
 */
public class BoItemTypes {
    public static final int itItems = 0;         // 标准物料
    public static final int itLabor = 1;         // 人工
    public static final int itTravel = 2;        // 差旅
    public static final int itFixedAssets = 3;   // 固定资产

    /**
     * 获取物料类型名称
     */
    public static String getTypeName(int itemType) {
        switch (itemType) {
            case itItems: return "标准物料";
            case itLabor: return "人工";
            case itTravel: return "差旅";
            case itFixedAssets: return "固定资产";
            default: return "未知 (" + itemType + ")";
        }
    }
}