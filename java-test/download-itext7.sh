#!/bin/bash

echo "=== 下载iText7库 ==="
echo ""

# 创建lib目录
mkdir -p lib
cd lib

echo "正在下载iText7核心库..."

# 尝试下载iText7核心库
echo "1. 下载iText7核心库..."
curl -L -o itext7-core-7.2.5.jar "https://repo1.maven.org/maven2/com/itextpdf/itext7-core/7.2.5/itext7-core-7.2.5.jar"

if [ $? -eq 0 ] && [ -s itext7-core-7.2.5.jar ]; then
    echo "✅ iText7核心库下载成功"
else
    echo "❌ iText7核心库下载失败，尝试备用方案..."
    
    # 尝试下载其他版本
    echo "2. 尝试下载iText7 7.1.16版本..."
    curl -L -o itext7-core-7.1.16.jar "https://repo1.maven.org/maven2/com/itextpdf/itext7-core/7.1.16/itext7-core-7.1.16.jar"
    
    if [ $? -eq 0 ] && [ -s itext7-core-7.1.16.jar ]; then
        echo "✅ iText7 7.1.16版本下载成功"
        mv itext7-core-7.1.16.jar itext7-core-7.2.5.jar
    else
        echo "❌ 所有版本下载都失败"
        echo ""
        echo "手动下载说明："
        echo "1. 访问 https://mvnrepository.com/artifact/com.itextpdf/itext7-core"
        echo "2. 下载7.2.5版本的JAR文件"
        echo "3. 将文件重命名为 itext7-core-7.2.5.jar"
        echo "4. 放到 lib/ 目录下"
        echo ""
        echo "或者使用Maven："
        echo "mvn dependency:get -Dartifact=com.itextpdf:itext7-core:7.2.5"
        echo ""
        exit 1
    fi
fi

echo ""
echo "3. 下载FreeMarker库..."
curl -L -o freemarker-2.3.23.jar "https://repo1.maven.org/maven2/org/freemarker/freemarker/2.3.23/freemarker-2.3.23.jar"

if [ $? -eq 0 ] && [ -s freemarker-2.3.23.jar ]; then
    echo "✅ FreeMarker库下载成功"
else
    echo "❌ FreeMarker库下载失败"
    echo "请手动下载 freemarker-2.3.23.jar 并放到 lib/ 目录"
fi

echo ""
echo "4. 检查下载结果..."
ls -la *.jar

echo ""
echo "=== 下载完成 ==="
echo "现在可以运行真正的PDF转换了："
echo ""
echo "1. 编译程序："
echo "   javac -d target src/main/java/RealHtmlToPdfConverter.java"
echo ""
echo "2. 运行转换："
echo "   java -cp 'target:lib/*' RealHtmlToPdfConverter ../reports/03-化学品月报-校级.html ./output.pdf"
echo ""
echo "注意：运行时必须包含 lib/* 在classpath中"
