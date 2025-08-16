# HTML转PDF测试项目

这是一个用于测试HTML文件转换为PDF兼容性的Java项目。

## 项目目标

**主要目标**：确保你的HTML文件可以在同事的Java环境中正常转换为PDF，而不需要他在那边做修复。

**工作流程**：
1. 在我这边运行Java转换程序，生成PDF文件
2. 查看转换过程中的错误信息
3. 检查生成的PDF效果
4. 如果有问题，截图发给我，我来修正HTML文件

## 项目结构

```
java-test/
├── src/main/java/
│   ├── SimplePdfTest.java           # 简单测试程序（推荐使用）
│   ├── HtmlToPdfTest.java           # 基础分析程序
│   ├── HtmlToPdfConverter.java      # 转换器程序
│   └── RealHtmlToPdfConverter.java  # 真正的PDF转换器（需要iText7库）
├── target/                           # 编译输出目录
├── lib/                              # 依赖库目录
├── build.xml                         # Ant构建文件
├── compile-and-run.sh                # 编译运行脚本
├── download-itext7.sh                # 下载iText7库脚本
└── README.md                         # 说明文档
```

## 快速开始

### 方法1：使用简单测试程序（推荐）

```bash
# 编译
javac -d target src/main/java/SimplePdfTest.java

# 运行测试
java -cp target SimplePdfTest ../reports/03-化学品月报-校级.html ./test_output.pdf
```

### 方法2：使用Shell脚本

```bash
# 给脚本执行权限
chmod +x compile-and-run.sh

# 运行测试
./compile-and-run.sh
```

### 方法3：使用Ant构建工具

```bash
# 编译
ant compile

# 运行测试
ant run
```

## 程序功能说明

### SimplePdfTest.java（推荐）
- **功能**：分析HTML兼容性，生成详细报告
- **优点**：快速、准确、提供具体修复建议
- **用途**：日常测试和问题诊断

### RealHtmlToPdfConverter.java
- **功能**：真正的HTML转PDF转换
- **要求**：需要完整的iText7库
- **状态**：当前iText7库下载有问题，需要手动配置

## 当前状态

✅ **已完成**：
- HTML兼容性分析
- CSS属性检查
- 问题诊断和修复建议
- 基础测试框架

⚠️ **待解决**：
- iText7库下载问题
- 真正的PDF转换功能

## 工作流程

### 1. 日常测试流程
```bash
# 1. 运行测试程序
java -cp target SimplePdfTest ../reports/你的文件.html ./output.pdf

# 2. 查看分析结果
# 3. 根据建议修复HTML
# 4. 重新测试验证
```

### 2. 问题修复流程
1. 运行测试程序发现问题
2. 截图发给我（包含错误信息）
3. 我分析问题并修复HTML
4. 你重新测试验证效果

### 3. 最终验证流程
1. 修复所有兼容性问题
2. 在同事的Java环境中测试
3. 确认PDF生成正常

## 常见问题

### Q: 为什么不能直接生成PDF？
A: 当前iText7库下载有问题，需要手动配置。但分析功能完全正常。

### Q: 如何知道HTML是否兼容？
A: 运行SimplePdfTest程序，它会详细分析并给出建议。

### Q: 发现问题后怎么办？
A: 截图发给我，我来修复HTML文件。

## 下一步计划

1. **短期**：使用SimplePdfTest分析所有HTML文件
2. **中期**：解决iText7库问题，实现真正的PDF转换
3. **长期**：建立完整的测试和修复工作流

## 系统要求

- Java 8 或更高版本
- 可选：Ant构建工具

## 联系支持

如果遇到问题或需要修复HTML文件，请：
1. 截图错误信息
2. 说明具体问题
3. 我会帮你修复HTML文件
