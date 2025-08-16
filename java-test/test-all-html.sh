#!/bin/bash

echo "=== 测试所有HTML文件 ==="
echo ""

# 检查reports目录
REPORTS_DIR="../reports"
if [ ! -d "$REPORTS_DIR" ]; then
    echo "❌ 找不到reports目录: $REPORTS_DIR"
    exit 1
fi

# 编译Java程序
echo "正在编译Java程序..."
javac -d target src/main/java/SimplePdfTest.java

if [ $? -ne 0 ]; then
    echo "❌ 编译失败！"
    exit 1
fi

echo "✅ 编译成功！"
echo ""

# 创建输出目录
OUTPUT_DIR="./test-results"
mkdir -p "$OUTPUT_DIR"

# 测试所有HTML文件
echo "开始测试所有HTML文件..."
echo ""

for html_file in "$REPORTS_DIR"/*.html; do
    if [ -f "$html_file" ]; then
        filename=$(basename "$html_file" .html)
        echo "正在测试: $filename"
        echo "----------------------------------------"
        
        # 运行测试
        java -cp target SimplePdfTest "$html_file" "$OUTPUT_DIR/${filename}.pdf"
        
        if [ $? -eq 0 ]; then
            echo "✅ $filename 测试完成"
        else
            echo "❌ $filename 测试失败"
        fi
        
        echo ""
    fi
done

echo "=== 测试完成 ==="
echo "所有测试结果保存在: $OUTPUT_DIR"
echo ""

# 显示结果文件
echo "生成的文件:"
ls -la "$OUTPUT_DIR"

echo ""
echo "=== 下一步操作 ==="
echo "1. 查看各个测试报告文件"
echo "2. 根据建议修复HTML文件"
echo "3. 重新运行测试验证修复效果"
echo "4. 如果有问题，截图发给我修复"
