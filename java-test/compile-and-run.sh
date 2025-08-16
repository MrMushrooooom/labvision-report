#!/bin/bash

echo "=== HTML转PDF测试程序 ==="
echo "正在编译Java程序..."

# 编译Java程序
javac -d target src/main/java/HtmlToPdfTest.java

if [ $? -eq 0 ]; then
    echo "编译成功！"
    echo ""
    echo "正在运行测试程序..."
    echo "测试文件: ../reports/03-化学品月报-校级.html"
    echo ""
    
    # 运行程序
    java -cp target HtmlToPdfTest ../reports/03-化学品月报-校级.html ./output.pdf
    
else
    echo "编译失败！"
    exit 1
fi
