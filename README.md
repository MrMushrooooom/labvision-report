# 高校实验室管理平台数据报告生成器

## 项目简介

LabVision Report Generator 是一个专为高校实验室管理平台设计的数据报告自动生成工具。它能够将HTML格式的报告模板转换为高质量的PDF文档，支持多种图表类型和数据可视化。

## 主要功能

- 📊 **多种图表支持**：饼图、折线图、柱状图、数据表格
- 🎨 **专业设计风格**：简洁大气的视觉设计，适合正式报告
- 🔄 **自动化流程**：支持GitHub Actions自动生成报告
- 📱 **响应式布局**：兼容多种设备和打印需求
- 🌏 **中文支持**：完整的中文字体和排版支持

## 技术架构

### 核心技术栈
- **Java 17+**：主要开发语言
- **iText7**：PDF生成引擎
- **Maven**：项目构建和依赖管理
- **HTML5 + CSS3**：报告模板
- **SVG**：矢量图表绘制

### 架构特点
- 纯HTML + CSS实现，无JavaScript依赖
- 兼容iText7和FreeMarker框架
- 支持A4纸张打印优化
- 模块化设计，易于扩展

## 项目结构

```
labvision-report/
├── README.md                    # 项目说明文档
├── .cursorrules                 # AI开发规范
├── docs/                        # 报告结构设计文档
├── reports/                     # HTML报告模板
│   ├── 化学品月报-校级.html
│   ├── 化学品月报-学院级.html
│   └── ...
├── java-test/                   # Java转换程序
│   ├── src/main/java/          # 源代码
│   ├── pom.xml                 # Maven配置
│   └── output/                 # 生成的PDF文件
└── assets/                      # 静态资源文件
```

## 快速开始

### 环境要求
- Java 17 或更高版本
- Maven 3.6 或更高版本
- 支持中文的操作系统

### 安装步骤

1. **克隆项目**
```bash
git clone https://github.com/your-username/labvision-report.git
cd labvision-report
```

2. **编译项目**
```bash
cd java-test
mvn clean compile
```

3. **生成PDF报告**
```bash
mvn exec:java -Dexec.mainClass="RealHtmlToPdfConverter" -Dexec.args="../reports/化学品月报-学院级.html"
```

### 输出结果
- 生成的PDF文件保存在 `java-test/output/` 目录
- 支持批量转换多个HTML文件
- 自动处理中文字体和图表渲染

## 报告类型

### 已支持的报告
- **化学品月报**：校级和学院级版本
- **安全检查任务报告**
- **分级分类评估任务报告**
- **设备开放共享月报**

### 报告特点
- 专业的封面页设计
- 清晰的目录结构
- 多种图表展示方式
- 优化的打印样式

## 图表实现

### 支持图表类型
- **饼图**：使用SVG polyline绘制，兼容性最佳
- **折线图**：SVG path绘制平滑曲线，支持数据点标记
- **柱状图**：CSS布局实现，支持动态宽度
- **数据表格**：标准HTML表格，样式可定制

### 图表设计原则
- 简洁明了的视觉设计
- 适合黑白打印的配色方案
- 响应式布局，支持多种尺寸
- 无动画效果，确保PDF兼容性

## 自动化部署

### GitHub Actions
项目支持GitHub Actions自动生成PDF报告：

1. **触发条件**：HTML文件更新时自动触发
2. **构建环境**：Java 17 + Maven环境
3. **输出结果**：PDF文件保存为GitHub Artifacts
4. **下载方式**：通过GitHub Actions页面下载

### 工作流程
```yaml
name: Generate PDF Reports
on:
  push:
    paths: ['reports/**/*.html']
jobs:
  generate-pdf:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '17'
      - run: mvn clean compile
      - run: mvn exec:java -Dexec.mainClass="RealHtmlToPdfConverter" -Dexec.args="reports/*.html"
      - uses: actions/upload-artifact@v3
        with:
          name: pdf-reports
          path: output/*.pdf
```

## 开发指南

### 添加新报告
1. 在 `docs/` 目录创建报告设计文档
2. 在 `reports/` 目录创建HTML模板
3. 遵循现有的图表实现标准
4. 测试PDF转换效果

### 自定义图表
- 参考现有图表的CSS样式
- 使用兼容的SVG属性
- 避免使用Flexbox和Grid布局
- 确保打印样式正确

## 常见问题

### PDF转换问题
- **图表显示异常**：检查CSS兼容性，避免使用不支持的属性
- **中文字体缺失**：确保字体文件正确加载
- **页面断行错误**：使用正确的分页控制属性

### 性能优化
- 合并重复的CSS规则
- 优化图片资源大小
- 减少不必要的HTML标签

## 贡献指南

欢迎提交Issue和Pull Request！

### 贡献流程
1. Fork项目
2. 创建功能分支
3. 提交代码变更
4. 创建Pull Request

### 代码规范
- 遵循现有的代码风格
- 确保PDF转换兼容性
- 添加必要的注释说明
- 测试所有功能正常

## 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

## 联系方式

- 项目主页：[GitHub Repository](https://github.com/your-username/labvision-report)
- 问题反馈：[Issues](https://github.com/your-username/labvision-report/issues)
- 功能建议：[Discussions](https://github.com/your-username/labvision-report/discussions)

---

**LabVision Report Generator** - 让数据报告生成变得简单高效 🚀
