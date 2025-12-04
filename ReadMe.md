# SAP Business One DI API 连接示例项目

## 项目简介

这是一个使用 Java 连接 SAP Business One DI API 的示例项目，演示了如何通过 DI API 连接到 SAP B1 并执行基本的物料查询操作。

## 技术栈

- **Java**: JDK 17
- **构建工具**: Maven
- **SAP B1 DI API**: 10.0
- **JSON 解析**: Gson 2.10.1

## 项目结构

```
SAPB1DIConnectinJava/
├── pom.xml                           # Maven 配置文件
├── config.json                       # 连接配置文件
├── README.md                         # 项目说明文档
├── lib/                              # SAP B1 DI API 库文件
│   ├── sboapi.jar
│   ├── sbowrapper.jar
│   └── sbojni.dll
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── sapb1/
│                       ├── SAPConnection.java          # 连接管理
│                       ├── ItemHelper.java             # 物料操作
│                       ├── ConnectionConfig.java       # 配置模型
│                       ├── ConfigLoader.java           # 配置加载器
│                       ├── BoDataServerTypes.java      # 数据库类型常量
│                       ├── BoItemTypes.java            # 物料类型常量
│                       ├── BoYesNoEnum.java            # Yes/No 枚举
│                       └── Main.java                   # 主程序入口
└── target/                           # Maven 编译输出目录
```

## 功能特性

- ✅ 从配置文件读取连接参数
- ✅ 连接到 SAP Business One
- ✅ 查询物料信息
- ✅ 批量查询物料
- ✅ 检查物料是否存在
- ✅ 完善的错误处理
- ✅ 自动资源释放

## 环境要求

### 软件要求

- **JDK**: 17 或更高版本
- **Maven**: 3.6+
- **SAP Business One**: 10.0 或更高版本
- **SAP B1 DI API**: 已安装

### SAP B1 DI API 安装位置

默认安装路径：
```
C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\
```

该目录包含：
- `sboapi.jar`
- `sbowrapper.jar`
- `sbojni.dll`

## 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd SAPB1DIConnectinJava
```

### 2. 配置 SAP B1 连接

在项目根目录创建或编辑 `config.json` 文件：

```json
{
  "connection": {
    "server": "服务器地址或主机名",
    "sldServer": "SLD服务器地址:端口（可选）",
    "companyDB": "公司数据库名称",
    "userName": "SAP B1 用户名",
    "password": "SAP B1 密码",
    "dbServerType": 17,
    "language": "English",
    "useTrusted": false
  },
  "test": {
    "itemCode": "测试用的物料代码"
  }
}
```

### 配置参数说明

#### connection 配置项

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `server` | String | ✅ | SAP B1 服务器地址或主机名 |
| `sldServer` | String | ❌ | SLD 服务器地址和端口，格式：`host:port`，默认端口 40000 |
| `companyDB` | String | ✅ | 公司数据库名称（如：`SBODemoUS`, `TestCN`） |
| `userName` | String | ✅ | SAP B1 登录用户名 |
| `password` | String | ✅ | SAP B1 登录密码 |
| `dbServerType` | Integer | ✅ | 数据库服务器类型代码（见下表） |
| `language` | String | ❌ | 界面语言，支持：`English`, `Chinese` 等，默认 `English` |
| `useTrusted` | Boolean | ❌ | 是否使用 Windows 集成认证，默认 `false` |

#### 数据库类型代码 (dbServerType)

| 代码 | 数据库类型 | 说明 |
|------|-----------|------|
| 1 | MSSQL | Microsoft SQL Server (旧版) |
| 4 | MSSQL 2005 | Microsoft SQL Server 2005 |
| 6 | MSSQL 2008 | Microsoft SQL Server 2008 |
| 7 | MSSQL 2012 | Microsoft SQL Server 2012 |
| 8 | MSSQL 2014 | Microsoft SQL Server 2014 |
| 9 | HANA | SAP HANA Database |
| 10 | MSSQL 2016 | Microsoft SQL Server 2016 |
| 11 | MSSQL 2017 | Microsoft SQL Server 2017 |
| 15 | MSSQL 2019 | Microsoft SQL Server 2019 |
| **17** | **MSSQL 2022** | **Microsoft SQL Server 2022** ⭐ |

#### test 配置项

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| `itemCode` | String | ❌ | 用于测试的物料代码，需要在系统中实际存在 |

### 配置示例

#### 示例 1：连接到本地 SAP B1（MSSQL 2022）

```json
{
  "connection": {
    "server": "localhost",
    "sldServer": "localhost:40000",
    "companyDB": "SBODemoUS",
    "userName": "manager",
    "password": "1234",
    "dbServerType": 17,
    "language": "English",
    "useTrusted": false
  },
  "test": {
    "itemCode": "A00001"
  }
}
```

#### 示例 2：连接到远程 SAP B1（HANA 数据库）

```json
{
  "connection": {
    "server": "192.168.1.100",
    "sldServer": "192.168.1.100:40000",
    "companyDB": "SBODEMOCN",
    "userName": "manager",
    "password": "password123",
    "dbServerType": 9,
    "language": "Chinese",
    "useTrusted": false
  },
  "test": {
    "itemCode": "ITEM001"
  }
}
```

#### 示例 3：使用 Windows 集成认证

```json
{
  "connection": {
    "server": "SAPB1-SERVER",
    "companyDB": "SBODemoUS",
    "userName": "",
    "password": "",
    "dbServerType": 17,
    "language": "English",
    "useTrusted": true
  },
  "test": {
    "itemCode": "A00001"
  }
}
```

### 3. 复制 SAP B1 DI API 文件

将以下文件从 SAP B1 DI API 安装目录复制到项目的 `lib` 目录：

```bash
# Windows 示例
copy "C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\sboapi.jar" lib\
copy "C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\sbowrapper.jar" lib\
copy "C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\sbojni.dll" lib\
```

### 4. 编译项目

```bash
mvn clean compile
```

### 5. 运行项目

#### 方式 A：使用 Maven 运行

```bash
mvn exec:java -Djava.library.path="C:\Program Files\SAP\SAP Business One DI API\JCO\LIB"
```

#### 方式 B：使用 IntelliJ IDEA 运行

1. 打开 `Main.java`
2. 右键 → Run 'Main.main()'
3. 如果出错，配置 VM Options：
   ```
   -Djava.library.path=C:\Program Files\SAP\SAP Business One DI API\JCO\LIB
   ```

#### 方式 C：打包后运行

```bash
# 打包
mvn clean package

# 运行
java -Djava.library.path="C:\Program Files\SAP\SAP Business One DI API\JCO\LIB" -jar target/SAPB1DIConnectinJava-1.0-SNAPSHOT.jar
```

## 代码示例

### 连接到 SAP B1

```java
// 加载配置
ConnectionConfig config = ConfigLoader.loadConfig();

// 创建连接
SAPConnection sapConnection = new SAPConnection(config.getConnection());

// 连接
if (sapConnection.connect()) {
    System.out.println("连接成功！");
    
    // 你的业务逻辑
    
    // 断开连接
    sapConnection.disconnect();
}
```

### 查询物料信息

```java
ItemHelper itemHelper = new ItemHelper(sapConnection.getCompany());

// 查询单个物料
String itemName = itemHelper.getItemName("A00001");
System.out.println("物料名称: " + itemName);

// 查询物料详细信息
itemHelper.getItem("A00001");

// 批量查询
String[] itemCodes = {"A00001", "A00002", "A00003"};
itemHelper.getMultipleItems(itemCodes);

// 检查物料是否存在
boolean exists = itemHelper.itemExists("A00001");
```

## 运行输出示例

```
╔══════════════════════════════════════════════════════╗
║                                                      ║
║       SAP Business One DI API 连接示例程序           ║
║              物料查询功能演示                        ║
║                  Version 1.3                         ║
║                                                      ║
╚══════════════════════════════════════════════════════╝

正在加载配置文件: config.json
✓ 配置文件加载成功
正在连接到 SAP Business One...
服务器: iZ7y4gbss1x67fZ
数据库: TestCN
用户: manager
数据库类型: MSSQL 2022

✓ Company 对象创建成功
开始连接...

========================================
✓ 连接成功！
========================================
公司名称: TestCN
数据库: TestCN
SAP 版本: 1000310
用户名: manager
数据库类型: MSSQL 2022
========================================

========== 公司详细信息 ==========
公司名称: TestCN
数据库名: TestCN
服务器: iZ7y4gbss1x67fZ
用户名: manager
SAP 版本: 1000310
数据库类型: MSSQL 2022
语言: English
================================

【示例 1】查询单个物料名称
物料代码: A001
物料名称: A001Item

【示例 2】查询物料详细信息

========================================
           物料信息
========================================
物料代码: A001
物料名称: A001Item
----------------------------------------
物料组: 100
物料类型: 标准物料
----------------------------------------
库存物料: 是
销售物料: 是
采购物料: 是
========================================

========================================
✓ 已断开 SAP Business One 连接
========================================
```

## 常见问题

### 1. 找不到 sbojni.dll

**错误信息：**
```
java.lang.UnsatisfiedLinkError: no sbojni in java.library.path
```

**解决方案：**
- 确保在运行时添加了 `-Djava.library.path` 参数
- 确保 `sbojni.dll` 在指定的路径中
- 或将 `sbojni.dll` 复制到系统 PATH 目录

### 2. 连接失败 - 错误代码 -119

**错误信息：**
```
错误代码: -119
错误描述: Database server type not supported
```

**解决方案：**
- 检查 `dbServerType` 配置是否正确
- 确认 SAP B1 版本支持该数据库类型
- MSSQL 2022 使用代码 **17**

### 3. 连接失败 - 错误代码 -5002

**错误信息：**
```
错误代码: -5002
```

**解决方案：**
- 检查用户名和密码是否正确
- 确认用户在 SAP B1 中存在且未被锁定

### 4. 连接失败 - 错误代码 -1001

**错误信息：**
```
错误代码: -1001
```

**解决方案：**
- 检查服务器地址是否正确
- 确认 SAP B1 Server 服务是否运行
- 检查网络连接和防火墙设置

### 5. 物料查询返回 null

**解决方案：**
- 确认物料代码在系统中存在
- 检查物料代码的大小写
- 确认当前用户有权限访问该物料

## Maven 命令

```bash
# 清理项目
mvn clean

# 编译
mvn compile

# 测试
mvn test

# 打包
mvn package

# 清理并打包
mvn clean package

# 运行
mvn exec:java

# 查看依赖树
mvn dependency:tree

# 安装到本地仓库
mvn install
```

## 开发工具

### 推荐 IDE

- IntelliJ IDEA Community Edition 2024+
- Eclipse with Maven plugin

### 配置 IntelliJ IDEA

1. **导入项目**
    - File → Open → 选择项目根目录
    - 选择 "Import as Maven project"

2. **配置 VM Options**
    - Run → Edit Configurations
    - VM options:
      ```
      -Djava.library.path=C:\Program Files\SAP\SAP Business One DI API\JCO\LIB
      ```

3. **配置 Maven**
    - File → Settings → Build, Execution, Deployment → Build Tools → Maven
    - 确认 Maven home directory 设置正确

## 项目扩展

### 添加新的业务对象操作

1. 创建新的 Helper 类（如 `SalesOrderHelper.java`）
2. 使用 `SBOCOMUtil.newXXX(company)` 获取业务对象
3. 实现 CRUD 操作
4. 记得在 finally 块中调用 `.release()` 释放资源

### 添加新的配置项

1. 在 `ConnectionConfig.java` 中添加新的字段
2. 在 `config.json` 中添加对应的配置
3. 在 `ConfigLoader.java` 中添加验证逻辑（如需要）

## 贡献

欢迎提交 Issue 和 Pull Request！

## 许可证

MIT License

## 联系方式

如有问题，请提交 Issue。

## 更新日志

### Version 1.3 (当前版本)
- ✅ 添加配置文件支持（config.json）
- ✅ 转换为 Maven 项目
- ✅ 添加物料查询功能
- ✅ 完善错误处理

### Version 1.2
- ✅ 修正数据库类型代码（MSSQL 2022 = 17）
- ✅ 简化数据库认证配置

### Version 1.1
- ✅ 初始版本
- ✅ 基本连接功能
- ✅ 简单的物料查询

## 参考资料

- [SAP Business One SDK Documentation](https://help.sap.com/docs/SAP_BUSINESS_ONE)
- [SAP Business One DI API Reference](https://help.sap.com/doc/saphelp_b1/10.0/en-US/frameset.htm?99e5fc1edb5a460ba8027d5b3e96dbed.html)
- [Maven Documentation](https://maven.apache.org/guides/)

---

**🎉 Happy Coding with SAP Business One DI API!**