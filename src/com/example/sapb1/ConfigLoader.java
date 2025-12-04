package com.example.sapb1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 配置文件加载工具
 */
public class ConfigLoader {
    private static final String DEFAULT_CONFIG_FILE = "config.json";
    private static ConnectionConfig config;

    /**
     * 加载配置文件
     */
    public static ConnectionConfig loadConfig() {
        return loadConfig(DEFAULT_CONFIG_FILE);
    }

    /**
     * 加载指定的配置文件
     */
    public static ConnectionConfig loadConfig(String configFile) {
        if (config != null) {
            return config;
        }

        try {
            System.out.println("正在加载配置文件: " + configFile);

            // 检查文件是否存在
            if (!Files.exists(Paths.get(configFile))) {
                System.err.println("配置文件不存在: " + configFile);
                System.err.println("请在项目根目录创建 config.json 文件");
                createSampleConfig();
                return null;
            }

            // 使用 Gson 解析 JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader(configFile);
            config = gson.fromJson(reader, ConnectionConfig.class);
            reader.close();

            // 验证配置
            if (validateConfig(config)) {
                System.out.println("✓ 配置文件加载成功");
                return config;
            } else {
                System.err.println("✗ 配置文件验证失败");
                return null;
            }

        } catch (IOException e) {
            System.err.println("✗ 读取配置文件失败: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("✗ 解析配置文件失败: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证配置
     */
    private static boolean validateConfig(ConnectionConfig config) {
        if (config == null) {
            System.err.println("配置对象为空");
            return false;
        }

        ConnectionConfig.Connection conn = config.getConnection();
        if (conn == null) {
            System.err.println("连接配置为空");
            return false;
        }

        // 检查必填字段
        if (isEmpty(conn.getServer())) {
            System.err.println("服务器地址不能为空");
            return false;
        }

        if (isEmpty(conn.getCompanyDB())) {
            System.err.println("数据库名称不能为空");
            return false;
        }

        if (isEmpty(conn.getUserName())) {
            System.err.println("用户名不能为空");
            return false;
        }

        if (isEmpty(conn.getPassword())) {
            System.err.println("密码不能为空");
            return false;
        }

        if (conn.getDbServerType() <= 0) {
            System.err.println("数据库类型无效");
            return false;
        }

        return true;
    }

    /**
     * 创建示例配置文件
     */
    private static void createSampleConfig() {
        String sampleConfig = "{\n" +
                "  \"connection\": {\n" +
                "    \"server\": \"your_server_name\",\n" +
                "    \"sldServer\": \"your_server_name:40000\",\n" +
                "    \"companyDB\": \"SBODemoUS\",\n" +
                "    \"userName\": \"manager\",\n" +
                "    \"password\": \"your_password\",\n" +
                "    \"dbServerType\": 17,\n" +
                "    \"language\": \"English\",\n" +
                "    \"useTrusted\": false\n" +
                "  },\n" +
                "  \"test\": {\n" +
                "    \"itemCode\": \"A00001\"\n" +
                "  }\n" +
                "}";

        System.out.println("\n示例配置文件内容：");
        System.out.println("==================");
        System.out.println(sampleConfig);
        System.out.println("==================\n");
    }

    /**
     * 重新加载配置
     */
    public static ConnectionConfig reloadConfig() {
        config = null;
        return loadConfig();
    }

    /**
     * 获取当前配置
     */
    public static ConnectionConfig getConfig() {
        if (config == null) {
            return loadConfig();
        }
        return config;
    }

    /**
     * 检查字符串是否为空
     */
    private static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}