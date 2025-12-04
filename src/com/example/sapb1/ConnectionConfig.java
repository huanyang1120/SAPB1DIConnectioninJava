package com.example.sapb1;

/**
 * SAP B1 连接配置类
 */
public class ConnectionConfig {
    private Connection connection;
    private Test test;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    /**
     * 连接配置
     */
    public static class Connection {
        private String server;
        private String sldServer;
        private String companyDB;
        private String userName;
        private String password;
        private int dbServerType;
        private String language;
        private boolean useTrusted;

        // Getters and Setters
        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getSldServer() {
            return sldServer;
        }

        public void setSldServer(String sldServer) {
            this.sldServer = sldServer;
        }

        public String getCompanyDB() {
            return companyDB;
        }

        public void setCompanyDB(String companyDB) {
            this.companyDB = companyDB;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getDbServerType() {
            return dbServerType;
        }

        public void setDbServerType(int dbServerType) {
            this.dbServerType = dbServerType;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public boolean isUseTrusted() {
            return useTrusted;
        }

        public void setUseTrusted(boolean useTrusted) {
            this.useTrusted = useTrusted;
        }
    }

    /**
     * 测试配置
     */
    public static class Test {
        private String itemCode;

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }
    }
}