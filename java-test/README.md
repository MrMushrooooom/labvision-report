# HTML转PDF转换器

## 项目概述

这是高校实验室管理平台数据报告的HTML转PDF转换器，基于iText7技术实现，支持中文字体和专业排版。

## 功能特性

- ✅ **HTML转PDF**：将HTML报告转换为高质量PDF
- ✅ **中文字体支持**：内置PingFang和思源黑体字体
- ✅ **专业排版**：支持封面页、目录页、分页控制
- ✅ **图表兼容**：支持SVG图表和复杂CSS样式
- ✅ **Maven管理**：标准Maven项目，依赖管理清晰

## 系统要求

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.0 或更高版本
- **操作系统**: Windows, macOS, Linux

## 快速开始

### 1. 安装依赖

#### Mac用户：
```bash
# 安装Java
brew install openjdk@8

# 安装Maven
brew install maven
```

#### Ubuntu用户：
```bash
# 安装Java
sudo apt update
sudo apt install openjdk-8-jdk

# 安装Maven
sudo apt install maven
```

#### Windows用户：
- 下载并安装 [Java JDK](https://adoptium.net/)
- 下载并安装 [Maven](https://maven.apache.org/download.cgi)
- 配置环境变量

### 2. 编译项目

```bash
# 进入项目目录
cd java-test

# 编译项目
mvn clean compile
```

### 3. 运行转换

#### 方法1：使用便捷脚本（推荐）
```bash
# 转换默认文件（化学品月报-学院级）
./run.sh

# 转换指定HTML文件
./run.sh ../reports/设备开放共享月报-校级.html
```

#### 方法2：直接使用Java命令
```bash
# 转换默认文件
java -cp "target/classes:target/lib/*" RealHtmlToPdfConverter

# 转换指定文件
java -cp "target/classes:target/lib/*" RealHtmlToPdfConverter ../reports/设备开放共享月报-校级.html
```

## 项目结构

```
java-test/
├── pom.xml                    # Maven配置文件
├── run.sh                     # 便捷运行脚本
├── src/main/java/
│   └── RealHtmlToPdfConverter.java  # 核心转换器
├── src/main/resources/
│   └── fonts/                # 字体资源
│       ├── Pingfang.ttf      # PingFang字体
│       └── SubsetOTF/        # 思源黑体字体
├── target/                    # 编译输出目录
└── README.md                  # 项目说明文档
```

## 使用方法

### 基本用法

1. **查看可用的HTML文件**：
   ```bash
   ls ../reports/*.html
   ```

2. **转换默认文件**：
   ```bash
   ./run.sh
   ```

3. **转换指定文件**：
   ```bash
   ./run.sh ../reports/化学品月报-校级.html
   ```

### 输出文件

- **PDF文件位置**: `target/output/` 目录
- **文件命名**: 自动根据HTML文件名生成PDF文件名
- **示例**: `化学品月报-校级.html` → `化学品月报-校级.pdf`

## 技术实现

### 核心技术栈

- **iText7**: PDF生成引擎
- **Html2PDF**: HTML到PDF转换
- **字体支持**: PingFang + 思源黑体
- **Maven**: 依赖管理和构建工具

### 依赖版本

- **iText7**: 7.1.16
- **Html2PDF**: 2.1.7
- **FreeMarker**: 2.3.23
- **Java**: 8+

## 故障排除

### 常见问题

#### 1. 编译失败
```bash
# 清理并重新编译
mvn clean compile
```

#### 2. 字体加载失败
- 确保 `src/main/resources/fonts/` 目录存在
- 检查字体文件是否完整

#### 3. 依赖下载失败
```bash
# 强制更新依赖
mvn clean compile -U
```

#### 4. 权限问题
```bash
# 给脚本添加执行权限
chmod +x run.sh
```

### 日志查看

运行时会显示详细的转换日志，包括：
- HTML文件检查结果
- CSS兼容性分析
- 字体加载状态
- PDF生成进度

## 开发说明

### 添加新的转换功能

1. 在 `RealHtmlToPdfConverter.java` 中添加新方法
2. 更新 `pom.xml` 添加新依赖（如需要）
3. 测试新功能
4. 更新文档

### 自定义字体

1. 将字体文件放入 `src/main/resources/fonts/` 目录
2. 在代码中加载新字体
3. 更新字体提供者配置

## 许可证

本项目采用 MIT 许可证。

## 联系方式

- **项目维护者**: 小来实验室团队
- **项目名称**: LabVision
- **技术支持**: 如有问题请提交Issue

---

**小来实验室 LabVision** - 专业的实验室管理解决方案
