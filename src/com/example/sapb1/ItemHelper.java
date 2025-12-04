package com.example.sapb1;

import com.sap.smb.sbo.api.*;

public class ItemHelper {
    private ICompany company;

    public ItemHelper(ICompany company) {
        this.company = company;
    }

    /**
     * 根据物料代码获取物料名称
     */
    public String getItemName(String itemCode) {
        IItems item = null;
        try {
            item = SBOCOMUtil.newItems(company);

            if (item.getByKey(itemCode)) {
                return item.getItemName();
            } else {
                System.err.println("✗ 未找到物料: " + itemCode);
                return null;
            }
        } catch (Exception e) {
            System.err.println("✗ 查询物料异常: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (item != null) {
                item.release();
            }
        }
    }

    /**
     * 获取物料完整信息并输出
     */
    public void getItem(String itemCode) {
        IItems item = null;
        try {
            item = SBOCOMUtil.newItems(company);

            if (item.getByKey(itemCode)) {
                printItemInfo(item);
            } else {
                System.out.println("\n========================================");
                System.err.println("✗ 未找到物料: " + itemCode);
                System.out.println("========================================\n");
            }

        } catch (Exception e) {
            System.err.println("✗ 查询物料异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (item != null) {
                item.release();
            }
        }
    }

    /**
     * 打印物料信息
     */
    private void printItemInfo(IItems item) {
        try {
            System.out.println("\n========================================");
            System.out.println("           物料信息");
            System.out.println("========================================");
            System.out.println("物料代码: " + item.getItemCode());
            System.out.println("物料名称: " + item.getItemName());
            System.out.println("----------------------------------------");
            System.out.println("物料组: " + item.getItemsGroupCode());
            System.out.println("物料类型: " + BoItemTypes.getTypeName(item.getItemType()));
            System.out.println("----------------------------------------");
            System.out.println("库存物料: " + BoYesNoEnum.toChineseString(item.getInventoryItem()));
            System.out.println("销售物料: " + BoYesNoEnum.toChineseString(item.getSalesItem()));
            System.out.println("采购物料: " + BoYesNoEnum.toChineseString(item.getPurchaseItem()));
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.err.println("✗ 打印物料信息失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 批量获取物料名称
     */
    public void getMultipleItems(String[] itemCodes) {
        System.out.println("\n========================================");
        System.out.println("           批量查询物料");
        System.out.println("========================================");
        System.out.printf("%-20s %-40s%n", "物料代码", "物料名称");
        System.out.println("----------------------------------------");

        for (String itemCode : itemCodes) {
            String itemName = getItemName(itemCode);
            if (itemName != null) {
                System.out.printf("%-20s %-40s%n", itemCode, itemName);
            } else {
                System.out.printf("%-20s %-40s%n", itemCode, "[未找到]");
            }
        }

        System.out.println("========================================\n");
    }

    /**
     * 检查物料是否存在
     */
    public boolean itemExists(String itemCode) {
        IItems item = null;
        try {
            item = SBOCOMUtil.newItems(company);
            return item.getByKey(itemCode);
        } catch (Exception e) {
            System.err.println("✗ 检查物料是否存在异常: " + e.getMessage());
            return false;
        } finally {
            if (item != null) {
                item.release();
            }
        }
    }
}