package com.example.sapb1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExploreJar {
    public static void main(String[] args) {
        String jarPath1 = "C:\\Program Files\\SAP\\SAP Business One DI API\\JCO\\LIB\\sboapi.jar";
        String jarPath2 = "C:\\Program Files\\SAP\\SAP Business One DI API\\JCO\\LIB\\sbowrapper.jar";

        System.out.println("========================================");
        System.out.println("探测 sboapi.jar");
        System.out.println("========================================");
        exploreJar(jarPath1);

        System.out.println("\n========================================");
        System.out.println("探测 sbowrapper.jar");
        System.out.println("========================================");
        exploreJar(jarPath2);
    }

    private static void exploreJar(String jarPath) {
        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jarFile.entries();

            int classCount = 0;
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();

                // 只显示 .class 文件
                if (name.endsWith(".class")) {
                    // 转换为类名格式
                    String className = name.replace('/', '.')
                            .replace(".class", "");

                    // 过滤出重要的类
                    if (className.contains("Company") ||
                            className.contains("SBO") ||
                            className.contains("Items") ||
                            className.contains("ICompany")) {
                        System.out.println("  " + className);
                    }
                    classCount++;
                }
            }

            System.out.println("\n总共找到 " + classCount + " 个类");

        } catch (IOException e) {
            System.err.println("无法读取 JAR 文件: " + e.getMessage());
        }
    }
}

/*
"C:\Program Files\SapMachine\JDK\17\bin\java.exe" "-Djava.library.path=C:\Program Files\SAP\SAP Business One DI API\JCO\LIB" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2025.2.5\lib\idea_rt.jar=64574" -Dfile.encoding=UTF-8 -classpath "C:\Users\Administrator\IdeaProjects\SAPB1DIConnectinJava\out\production\SAPB1DIConnectinJava;C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\sboapi.jar;C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\sbowrapper.jar" com.example.sapb1.ExploreJar
========================================
探测 sboapi.jar
========================================
  com.sap.smb.sbo.api.AlternativeItems
  com.sap.smb.sbo.api.AlternativeItemsService
  com.sap.smb.sbo.api.BlanketAgreements_ItemsLine
  com.sap.smb.sbo.api.BlanketAgreements_ItemsLines
  com.sap.smb.sbo.api.CampaignItems
  com.sap.smb.sbo.api.CashFlowLineItemsParams
  com.sap.smb.sbo.api.CashFlowLineItemsService
  com.sap.smb.sbo.api.Company
  com.sap.smb.sbo.api.CompanyInfo
  com.sap.smb.sbo.api.CompanyService
  com.sap.smb.sbo.api.DocumentPackageItems
  com.sap.smb.sbo.api.FixedAssetItemsService
  com.sap.smb.sbo.api.IAlternativeItems
  com.sap.smb.sbo.api.IAlternativeItemsService
  com.sap.smb.sbo.api.IBlanketAgreements_ItemsLine
  com.sap.smb.sbo.api.IBlanketAgreements_ItemsLines
  com.sap.smb.sbo.api.ICampaignItems
  com.sap.smb.sbo.api.ICashFlowLineItemsParams
  com.sap.smb.sbo.api.ICashFlowLineItemsService
  com.sap.smb.sbo.api.ICompany
  com.sap.smb.sbo.api.ICompanyInfo
  com.sap.smb.sbo.api.ICompanyService
  com.sap.smb.sbo.api.IDocumentPackageItems
  com.sap.smb.sbo.api.IFixedAssetItemsService
  com.sap.smb.sbo.api.IItems
  com.sap.smb.sbo.api.IItemsAttributeGroups
  com.sap.smb.sbo.api.IItemsDepreciationParameters
  com.sap.smb.sbo.api.IItemsDistributionRules
  com.sap.smb.sbo.api.IItemsPeriodControls
  com.sap.smb.sbo.api.IItemsProjects
  com.sap.smb.sbo.api.IItems_PreferredVendors
  com.sap.smb.sbo.api.IItems_Prices
  com.sap.smb.sbo.api.IReportLayoutItems
  com.sap.smb.sbo.api.ISBOMailerService
  com.sap.smb.sbo.api.ISBObob
  com.sap.smb.sbo.api.IUserMenuItems
  com.sap.smb.sbo.api.IWebClientFormSettingItems
  com.sap.smb.sbo.api.Items
  com.sap.smb.sbo.api.ItemsAttributeGroups
  com.sap.smb.sbo.api.ItemsDepreciationParameters
  com.sap.smb.sbo.api.ItemsDistributionRules
  com.sap.smb.sbo.api.ItemsPeriodControls
  com.sap.smb.sbo.api.ItemsProjects
  com.sap.smb.sbo.api.Items_PreferredVendors
  com.sap.smb.sbo.api.Items_Prices
  com.sap.smb.sbo.api.ReportLayoutItems
  com.sap.smb.sbo.api.SBOCOMConstants
  com.sap.smb.sbo.api.SBOCOMException
  com.sap.smb.sbo.api.SBOCOMUtil
  com.sap.smb.sbo.api.SBOErrorMessage
  com.sap.smb.sbo.api.SBOMailerService
  com.sap.smb.sbo.api.SBObob
  com.sap.smb.sbo.api.UserMenuItems
  com.sap.smb.sbo.api.WebClientFormSettingItems

总共找到 3117 个类

========================================
探测 sbowrapper.jar
========================================

总共找到 16 个类

Process finished with exit code 0
 */