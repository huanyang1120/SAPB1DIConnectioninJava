package com.example.sapb1;

public class Main {
    public static void main(String[] args) {
        printHeader();

        // 加载配置文件
        ConnectionConfig config = ConfigLoader.loadConfig();

        if (config == null) {
            System.err.println("\n无法加载配置文件，程序退出。");
            System.err.println("请检查 config.json 文件是否存在且格式正确。");
            System.exit(1);
        }

        // 使用配置创建连接
        SAPConnection sapConnection = new SAPConnection(config.getConnection());

        try {
            // 1. 连接
            if (sapConnection.connect()) {

                // 2. 显示公司信息
                sapConnection.printCompanyInfo();

                // 3. 连接状态检查
                System.out.println("连接状态检查: " + (sapConnection.isConnected() ? "已连接 ✓" : "未连接 ✗"));

                // 4. 物料查询示例
                System.out.println("\n========================================");
                System.out.println("开始物料查询示例...");
                System.out.println("========================================\n");

                ItemHelper itemHelper = new ItemHelper(sapConnection.getCompany());

                // 从配置文件读取测试物料代码
                String testItemCode = config.getTest() != null && config.getTest().getItemCode() != null
                        ? config.getTest().getItemCode()
                        : "A001";

                System.out.println("测试物料代码（来自配置文件）: " + testItemCode);
                System.out.println();

                // 示例 1
                System.out.println("【示例 1】查询单个物料名称");
                String itemName = itemHelper.getItemName(testItemCode);
                if (itemName != null) {
                    System.out.println("物料代码: " + testItemCode);
                    System.out.println("物料名称: " + itemName);
                } else {
                    System.out.println("✗ 物料 " + testItemCode + " 不存在");
                }

                // 示例 2
                System.out.println("\n【示例 2】查询物料详细信息");
                itemHelper.getItem(testItemCode);

                // 示例 3
                System.out.println("【示例 3】批量查询物料");
                String[] itemCodes = {testItemCode, "A002", "A003"};
                itemHelper.getMultipleItems(itemCodes);

                // 示例 4
                System.out.println("【示例 4】检查物料是否存在");
                boolean exists = itemHelper.itemExists(testItemCode);
                System.out.println("物料 " + testItemCode + " " + (exists ? "存在 ✓" : "不存在 ✗"));
                System.out.println();

            } else {
                System.err.println("\n连接失败，程序退出。");
                System.exit(1);
            }

        } catch (Exception e) {
            System.err.println("程序执行异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sapConnection.disconnect();
            printFooter();
        }
    }

    private static void printHeader() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║       SAP Business One DI API 连接示例程序           ║");
        System.out.println("║              物料查询功能演示                        ║");
        System.out.println("║                  Version 1.3                         ║");
        System.out.println("║                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void printFooter() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║                   程序执行完毕                       ║");
        System.out.println("║                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }
}