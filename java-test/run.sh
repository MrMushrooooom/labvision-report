#!/bin/bash

echo "=== 高校实验室管理平台数据报告PDF转换器 ==="
echo ""

# 检查Java是否安装
if ! command -v java &> /dev/null; then
    echo "❌ 错误：未找到Java环境"
    echo "请先安装Java 8或更高版本"
    echo ""
    echo "安装方法："
    echo "  Mac: brew install openjdk@8"
    echo "  Ubuntu: sudo apt install openjdk-8-jdk"
    echo "  Windows: 下载并安装Java JDK"
    exit 1
fi

# 检查Maven是否安装
if ! command -v mvn &> /dev/null; then
    echo "❌ 错误：未找到Maven环境"
    echo "请先安装Maven"
    echo ""
    echo "安装方法："
    echo "  Mac: brew install maven"
    echo "  Ubuntu: sudo apt install maven"
    echo "  Windows: 下载并安装Maven"
    exit 1
fi

echo "✅ Java版本: $(java -version 2>&1 | head -n 1)"
echo "✅ Maven版本: $(mvn -version 2>&1 | head -n 1)"
echo ""

# 编译项目
echo "🔨 正在编译项目..."
if mvn clean compile; then
    echo "✅ 编译成功！"
else
    echo "❌ 编译失败，请检查错误信息"
    exit 1
fi

echo ""
echo "📋 使用方法："
echo "1. 转换默认文件（化学品月报-学院级）："
echo "   ./run.sh"
echo ""
echo "2. 转换指定HTML文件："
echo "   ./run.sh ../reports/设备开放共享月报-校级.html"
echo ""
echo "3. 查看所有可用的HTML文件："
echo "   ls ../reports/*.html"
echo ""

# 确定要转换的文件
if [ $# -eq 0 ]; then
    # 默认转换学院级化学品月报
    HTML_FILE="../reports/化学品月报-学院级.html"
    PDF_NAME="化学品月报-学院级.pdf"
    echo "📁 使用默认文件: $HTML_FILE"
else
    HTML_FILE="$1"
    # 从路径中提取文件名作为PDF名称
    PDF_NAME=$(basename "$HTML_FILE" .html).pdf
    echo "📁 使用指定文件: $HTML_FILE"
fi

echo "📄 输出PDF文件: $PDF_NAME"
echo ""

# 检查HTML文件是否存在
if [ ! -f "$HTML_FILE" ]; then
    echo "❌ 错误：HTML文件不存在: $HTML_FILE"
    echo ""
    echo "可用的HTML文件："
    ls ../reports/*.html 2>/dev/null || echo "  (reports目录为空)"
    exit 1
fi

# 运行PDF转换
echo "🚀 开始转换HTML为PDF..."
echo ""

# 使用Maven编译后的类路径运行
java -cp "target/classes:target/lib/*" RealHtmlToPdfConverter "$HTML_FILE" "$PDF_NAME"

echo ""
echo "🎉 转换完成！"
echo "📁 输出目录: target/output/"
echo "�� PDF文件: $PDF_NAME"
