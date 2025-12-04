package com.example.sapb1;

import com.sap.smb.sbo.api.*;

public class SAPConnection {
    private ICompany company;
    private ConnectionConfig.Connection config;

    /**
     * 使用配置文件创建连接
     */
    public SAPConnection(ConnectionConfig.Connection config) {
        this.config = config;
    }

    /**
     * 连接到 SAP Business One
     */
    public boolean connect() {
        try {
            System.out.println("正在连接到 SAP Business One...");
            System.out.println("服务器: " + config.getServer());
            System.out.println("数据库: " + config.getCompanyDB());
            System.out.println("用户: " + config.getUserName());
            System.out.println("数据库类型: " + BoDataServerTypes.getTypeName(config.getDbServerType()));
            System.out.println();

            // 创建 Company 对象
            company = SBOCOMUtil.newCompany();
            System.out.println("✓ Company 对象创建成功");

            // 设置连接参数
            company.setServer(config.getServer());

            // 设置 SLD 服务器（可选）
            if (config.getSldServer() != null && !config.getSldServer().isEmpty()) {
                try {
                    company.setSLDServer(config.getSldServer());
                } catch (Exception e) {
                    System.out.println("(SLDServer 设置跳过)");
                }
            }

            company.setCompanyDB(config.getCompanyDB());
            company.setUserName(config.getUserName());
            company.setPassword(config.getPassword());
            company.setDbServerType(config.getDbServerType());

            // 设置语言
            int languageCode = getLanguageCode(config.getLanguage());
            company.setLanguage(languageCode);

            company.setUseTrusted(config.isUseTrusted());

            System.out.println("开始连接...");

            // 执行连接
            int result = company.connect();

            if (result == 0) {
                System.out.println("\n========================================");
                System.out.println("✓ 连接成功！");
                System.out.println("========================================");
                System.out.println("公司名称: " + company.getCompanyName());
                System.out.println("数据库: " + company.getCompanyDB());
                System.out.println("SAP 版本: " + company.getVersion());
                System.out.println("用户名: " + company.getUserName());
                System.out.println("数据库类型: " + BoDataServerTypes.getTypeName(company.getDbServerType()));
                System.out.println("========================================");
                return true;
            } else {
                System.err.println("\n✗ 连接失败！");
                System.err.println("错误代码: " + company.getLastErrorCode());
                System.err.println("错误描述: " + company.getLastErrorDescription());
                return false;
            }

        } catch (Exception e) {
            System.err.println("\n✗ 连接异常: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据语言名称获取语言代码
     */
    private int getLanguageCode(String language) {
        if (language == null) {
            return SBOCOMConstants.BoSuppLangs_ln_English;
        }

        switch (language.toLowerCase()) {
            case "chinese":
            case "中文":
                return SBOCOMConstants.BoSuppLangs_ln_Chinese;
            case "english":
            case "英文":
                return SBOCOMConstants.BoSuppLangs_ln_English;
            case "spanish":
            case "西班牙语":
                return SBOCOMConstants.BoSuppLangs_ln_Spanish;
            default:
                return SBOCOMConstants.BoSuppLangs_ln_English;
        }
    }

    public void disconnect() {
        try {
            if (company != null && company.isConnected()) {
                company.disconnect();
                System.out.println("\n========================================");
                System.out.println("✓ 已断开 SAP Business One 连接");
                System.out.println("========================================");
            }
        } catch (Exception e) {
            System.err.println("✗ 断开连接异常: " + e.getMessage());
        }
    }

    public boolean isConnected() {
        try {
            return company != null && company.isConnected();
        } catch (Exception e) {
            return false;
        }
    }

    public ICompany getCompany() {
        return company;
    }

    public void printCompanyInfo() {
        try {
            if (company != null && company.isConnected()) {
                System.out.println("\n========== 公司详细信息 ==========");
                System.out.println("公司名称: " + company.getCompanyName());
                System.out.println("数据库名: " + company.getCompanyDB());
                System.out.println("服务器: " + company.getServer());
                System.out.println("用户名: " + company.getUserName());
                System.out.println("SAP 版本: " + company.getVersion());
                System.out.println("数据库类型: " + BoDataServerTypes.getTypeName(company.getDbServerType()));
                System.out.println("语言: " + getLanguageString(company.getLanguage()));
                System.out.println("================================\n");
            }
        } catch (Exception e) {
            System.err.println("获取公司信息失败: " + e.getMessage());
        }
    }

    private String getLanguageString(int language) {
        if (language == SBOCOMConstants.BoSuppLangs_ln_English) return "English";
        if (language == SBOCOMConstants.BoSuppLangs_ln_Chinese) return "Chinese";
        return "Other (" + language + ")";
    }
}