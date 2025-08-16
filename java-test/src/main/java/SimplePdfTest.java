import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SimplePdfTest {
    
    public static void main(String[] args) {
        System.out.println("=== 简单PDF测试程序 ===");
        System.out.println("这个程序用于验证HTML转PDF的工作流程");
        System.out.println("");
        
        if (args.length < 2) {
            System.out.println("使用方法: java SimplePdfTest <HTML文件路径> <输出PDF路径>");
            System.out.println("示例: java SimplePdfTest ../reports/03-化学品月报-校级.html ./output.pdf");
            return;
        }
        
        String htmlFilePath = args[0];
        String pdfFilePath = args[1];
        
        try {
            // 读取HTML文件
            System.out.println("正在读取HTML文件: " + htmlFilePath);
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
            System.out.println("HTML文件大小: " + htmlContent.length() + " 字符");
            
            // 分析HTML内容
            System.out.println("\n正在分析HTML内容...");
            analyzeHtml(htmlContent);
            
            // 模拟PDF转换过程
            System.out.println("\n正在模拟PDF转换过程...");
            boolean success = simulatePdfConversion(htmlContent, pdfFilePath);
            
            if (success) {
                System.out.println("✅ 模拟转换完成！");
                System.out.println("");
                System.out.println("=== 下一步操作 ===");
                System.out.println("1. 检查生成的测试文件");
                System.out.println("2. 查看是否有错误信息");
                System.out.println("3. 如果有问题，截图发给我修复HTML");
                System.out.println("");
                System.out.println("=== 实际PDF转换 ===");
                System.out.println("要生成真正的PDF，你需要：");
                System.out.println("1. 下载完整的iText7库");
                System.out.println("2. 或者使用你同事的Java环境");
                System.out.println("3. 或者我帮你创建一个完整的转换器");
            } else {
                System.out.println("❌ 模拟转换失败！");
            }
            
        } catch (Exception e) {
            System.err.println("❌ 程序执行过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void analyzeHtml(String htmlContent) {
        System.out.println("=== HTML内容分析 ===");
        
        // 基本结构检查
        if (htmlContent.contains("<html") && htmlContent.contains("</html>")) {
            System.out.println("✅ HTML文档结构完整");
        } else {
            System.out.println("❌ HTML文档结构不完整");
        }
        
        // 关键元素检查
        String[] keyElements = {
            "cover-page", "toc-page", "content-page", 
            "pie-chart", "chart-container", "data-table"
        };
        
        for (String element : keyElements) {
            int count = countOccurrences(htmlContent, element);
            if (count > 0) {
                System.out.println("✅ " + element + ": " + count + " 个");
            }
        }
        
        // CSS兼容性检查
        System.out.println("\n--- CSS兼容性检查 ---");
        checkCssCompatibility(htmlContent);
        
        // 生成修复建议
        System.out.println("\n--- 修复建议 ---");
        generateFixSuggestions(htmlContent);
    }
    
    private static void checkCssCompatibility(String htmlContent) {
        // 检查不兼容的CSS属性
        Map<String, Integer> incompatibleCss = new HashMap<>();
        incompatibleCss.put("linear-gradient", 0);
        incompatibleCss.put("radial-gradient", 0);
        incompatibleCss.put("conic-gradient", 0);
        incompatibleCss.put("box-shadow", 0);
        incompatibleCss.put("text-shadow", 0);
        incompatibleCss.put("filter:", 0);
        incompatibleCss.put("transform:", 0);
        incompatibleCss.put("display: flex", 0);
        incompatibleCss.put("display: grid", 0);
        incompatibleCss.put("justify-content", 0);
        incompatibleCss.put("align-items", 0);
        incompatibleCss.put("flex-direction", 0);
        incompatibleCss.put("grid-template-columns", 0);
        incompatibleCss.put("gap:", 0);
        
        for (String css : incompatibleCss.keySet()) {
            int count = countOccurrences(htmlContent, css);
            incompatibleCss.put(css, count);
        }
        
        // 显示结果
        for (Map.Entry<String, Integer> entry : incompatibleCss.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println("⚠️  " + entry.getKey() + ": " + entry.getValue() + " 次 (可能不兼容iText7)");
            }
        }
        
        // 检查兼容的CSS
        String[] compatibleCss = {
            "display: block", "display: inline-block", "text-align", 
            "margin", "padding", "border", "position"
        };
        
        for (String css : compatibleCss) {
            int count = countOccurrences(htmlContent, css);
            if (count > 0) {
                System.out.println("✅ " + css + ": " + count + " 次");
            }
        }
    }
    
    private static void generateFixSuggestions(String htmlContent) {
        System.out.println("基于分析结果，建议修复以下问题：");
        
        if (htmlContent.contains("linear-gradient")) {
            System.out.println("1. 将 linear-gradient 替换为纯色背景");
        }
        if (htmlContent.contains("box-shadow")) {
            System.out.println("2. 将 box-shadow 替换为 border 样式");
        }
        if (htmlContent.contains("text-shadow")) {
            System.out.println("3. 移除 text-shadow 属性");
        }
        if (htmlContent.contains("conic-gradient")) {
            System.out.println("4. 检查 conic-gradient 在PDF中的显示效果");
        }
        if (htmlContent.contains("transform:")) {
            System.out.println("5. 检查 transform 属性是否影响布局");
        }
        
        System.out.println("\n修复完成后，重新运行此程序验证效果");
    }
    
    private static int countOccurrences(String text, String pattern) {
        int count = 0;
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = text.indexOf(pattern, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += pattern.length();
            }
        }
        return count;
    }
    
    private static boolean simulatePdfConversion(String htmlContent, String pdfFilePath) {
        try {
            // 创建输出目录
            Path outputDir = Paths.get(pdfFilePath).getParent();
            if (outputDir != null) {
                Files.createDirectories(outputDir);
            }
            
            // 创建一个详细的测试报告
            StringBuilder report = new StringBuilder();
            report.append("HTML转PDF测试报告\n");
            report.append("=".repeat(50)).append("\n\n");
            
            report.append("测试时间: ").append(new Date()).append("\n");
            report.append("原始HTML长度: ").append(htmlContent.length()).append(" 字符\n");
            report.append("目标PDF路径: ").append(pdfFilePath).append("\n\n");
            
            // 添加分析结果
            report.append("HTML结构分析:\n");
            report.append("- 包含封面页: ").append(htmlContent.contains("cover-page") ? "是" : "否").append("\n");
            report.append("- 包含目录页: ").append(htmlContent.contains("toc-page") ? "是" : "否").append("\n");
            report.append("- 包含内容页: ").append(htmlContent.contains("content-page") ? "是" : "否").append("\n");
            report.append("- 包含饼图: ").append(htmlContent.contains("pie-chart") ? "是" : "否").append("\n");
            report.append("- 包含表格: ").append(htmlContent.contains("data-table") ? "是" : "否").append("\n\n");
            
            // 添加CSS兼容性信息
            report.append("CSS兼容性分析:\n");
            String[] cssChecks = {
                "linear-gradient", "conic-gradient", "box-shadow", 
                "text-shadow", "display: flex", "display: grid"
            };
            
            for (String css : cssChecks) {
                int count = countOccurrences(htmlContent, css);
                if (count > 0) {
                    report.append("- ").append(css).append(": ").append(count).append(" 次 (可能不兼容)\n");
                }
            }
            
            report.append("\n=== 工作流程说明 ===\n");
            report.append("1. 这个程序用于分析HTML兼容性\n");
            report.append("2. 要生成真正的PDF，需要iText7库\n");
            report.append("3. 或者使用你同事的Java环境\n");
            report.append("4. 发现问题后，我来修复HTML文件\n");
            
            // 保存报告
            String reportPath = pdfFilePath.replace(".pdf", "_test_report.txt");
            Files.write(Paths.get(reportPath), report.toString().getBytes());
            
            System.out.println("测试报告已保存到: " + reportPath);
            
            return true;
            
        } catch (Exception e) {
            System.err.println("模拟转换失败: " + e.getMessage());
            return false;
        }
    }
}
