# 高校实验室管理平台数据报告项目

## 项目概述

本项目是一个专业的高校实验室管理平台数据报告生成系统，用于生成高质量的HTML报告并转换为PDF格式。项目专注于实验室设备开放共享、化学品管理、安全检查等核心业务领域的数据分析和报告生成。

## 核心特性

### 🎯 专业报告类型
- **安全检查任务报告** - 实验室安全检查和任务完成情况
- **分级分类评估任务报告** - 化学品分级分类评估分析
- **化学品月报** - 校级和学院级化学品管理月度报告
- **设备开放共享月报** - 校级和学院级设备开放共享月度报告

### 🎨 专业设计风格
- 深色主题配色方案（深蓝、深紫、深绿）
- 专业的封面页设计（深蓝色背景，固定A4尺寸）
- 清晰的目录页布局
- 多种图表实现方案（饼图、折线图、柱状图、数据表格）

### 🔧 技术特点
- **纯HTML + CSS** - 无框架依赖，确保最大兼容性
- **iText7兼容** - 完美支持Java框架的PDF转换
- **FreeMarker兼容** - 支持模板引擎渲染
- **响应式设计** - 适配不同设备和打印需求

## 项目结构

```
labvision-report/
├── .cursorrules                    # 项目规则文件
├── .gitignore                     # Git忽略文件
├── README.md                      # 项目说明文档
├── AGENTS.md                      # 代理说明文档
├── docs/                          # 报告结构设计文档
│   ├── 01-安全检查任务报告.md
│   ├── 02-分级分类评估任务报告.md
│   ├── 03-化学品月报-校级.md
│   ├── 04-化学品月报-学院级.md
│   ├── 05-设备开放共享月报-校级.md
│   └── 06-设备开放共享月报-学院级.md
├── reports/                       # 生成的HTML报告文件
│   ├── 03-化学品月报-校级.html
│   ├── 04-化学品月报-学院级.html
│   ├── 05-设备开放共享月报-校级.html
│   └── 06-设备开放共享月报-学院级.html
├── assets/                        # 静态资源文件
│   ├── icons/                     # 图标文件
│   └── images/                    # 图片文件
└── java-test/                     # Java PDF转换测试代码
    ├── src/                       # 源代码
    ├── lib/                       # 依赖库
    ├── target/                    # 编译输出
    └── output/                    # PDF输出目录
```

## 技术规范

### HTML+CSS要求
- 只能使用纯HTML + CSS，不能使用任何框架
- CSS样式直接写在HTML文件内（内联或<style>标签）
- 每个报告对应一个独立的HTML文件

### Java框架兼容性要求
**重要：所有HTML必须兼容iText7和FreeMarker框架，禁止使用以下CSS属性：**

#### 禁止使用的Flexbox属性 ❌
- `display: flex` → 使用 `display: block` 或 `inline-block`
- `flex-direction: column` → 使用 `vertical-align: top` 和 `margin-bottom`
- `justify-content`, `align-items` → 使用 `text-align: center` 和 `margin: 0 auto`
- `flex: 1` → 使用固定宽度或百分比
- `gap: Xpx` → 使用 `margin` 和 `padding` 控制间距

#### 禁止使用的Grid属性 ❌
- `display: grid` → 使用 `display: block` 和 `text-align: center`
- `grid-template-columns` → 使用 `display: inline-block` 和 `width: X%`

#### 推荐使用的兼容属性 ✅
- `display: block`, `inline-block`
- `text-align: center`, `left`, `right`
- `position: relative`, `absolute`
- `margin`, `padding`
- `vertical-align: top`, `middle`

## 快速开始

### 1. 克隆项目
```bash
git clone https://github.com/yourusername/labvision-report.git
cd labvision-report
```

### 2. 查看报告
- 打开 `reports/` 目录下的HTML文件
- 使用浏览器查看报告效果

### 3. 生成PDF（需要Java环境）
```bash
cd java-test
./compile-and-run.sh
```

## 报告生成流程

### 1. 设计报告结构
- 在 `docs/` 目录下创建Markdown格式的报告结构文档
- 定义报告章节、数据维度、图表类型等

### 2. 生成HTML报告
- 基于Markdown文档创建HTML报告
- 使用项目提供的CSS样式库
- 确保iText7兼容性

### 3. 转换为PDF
- 使用Java程序将HTML转换为PDF
- 支持中文字体和专业排版

## 成功案例

### 化学品月报-校级
- ✅ 成功实现多色饼图（使用SVG polyline）
- ✅ 完整的封面页、目录页、分页控制
- ✅ 成功转换为PDF，图表显示正常

### 设备开放共享月报
- ✅ 校级版本：包含学院对比分析
- ✅ 学院级版本：专注校内校外对比
- ✅ 支持多种图表类型和数据分析

## 贡献指南

### 开发流程
1. Fork项目到你的GitHub账户
2. 创建功能分支：`git checkout -b feature/new-report`
3. 提交更改：`git commit -am 'Add new report type'`
4. 推送分支：`git push origin feature/new-report`
5. 创建Pull Request

### 代码规范
- 遵循项目的HTML+CSS兼容性要求
- 确保所有报告都能成功转换为PDF
- 保持专业的视觉设计风格
- 添加适当的注释和文档

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 联系方式

- **项目维护者**：小来实验室团队
- **项目名称**：LabVision
- **项目地址**：https://github.com/yourusername/labvision-report

## 更新日志

### v1.0.0 (2024-12-31)
- ✨ 初始版本发布
- ✨ 支持4种主要报告类型
- ✨ 完整的HTML+CSS样式库
- ✨ Java PDF转换支持
- ✨ 专业的报告设计模板

---

**小来实验室 LabVision** - 专业的实验室管理解决方案
