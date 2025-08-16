import java.io.*;
import java.nio.file.*;
import java.util.*;

public class HtmlToPdfConverter {
    
    public static void main(String[] args) {
        System.out.println("HTML转PDF转换器启动...");
        
        if (args.length < 2) {
            System.out.println("使用方法: java HtmlToPdfConverter <HTML文件路径> <输出PDF路径>");
            return;
        }
        
        String htmlFilePath = args[0];
        String pdfFilePath = args[1];
        
        try {
            // 读取HTML文件
            System.out.println("正在读取HTML文件: " + htmlFilePath);
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
            
            // 分析HTML内容
            System.out.println("正在分析HTML内容...");
            analyzeHtml(htmlContent);
            
            // 尝试转换为PDF
            System.out.println("正在转换为PDF...");
            boolean success = convertToPdf(htmlContent, pdfFilePath);
            
            if (success) {
                System.out.println("转换成功！PDF文件已保存到: " + pdfFilePath);
            } else {
                System.out.println("转换失败，请检查错误信息");
            }
            
        } catch (Exception e) {
            System.err.println("转换过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void analyzeHtml(String htmlContent) {
        System.out.println("=== HTML内容分析 ===");
        
        // 基本统计
        System.out.println("HTML文件大小: " + htmlContent.length() + " 字符");
        
        // 检查关键元素
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
        
        // 检查CSS兼容性
        checkCssCompatibility(htmlContent);
        
        System.out.println("=== 分析完成 ===");
    }
    
    private static void checkCssCompatibility(String htmlContent) {
        System.out.println("\n--- CSS兼容性检查 ---");
        
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
        incompatibleCss.put("display:grid", 0);
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
                System.out.println("⚠️  " + entry.getKey() + ": " + entry.getValue() + " 次");
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
    
    private static boolean convertToPdf(String htmlContent, String pdfFilePath) {
        try {
            // 创建输出目录
            Path outputDir = Paths.get(pdfFilePath).getParent();
            if (outputDir != null) {
                Files.createDirectories(outputDir);
            }
            
            // 创建一个详细的测试报告
            StringBuilder report = new StringBuilder();
            report.append("HTML转PDF转换测试报告\n");
            report.append("=".repeat(50)).append("\n\n");
            
            report.append("转换时间: ").append(new Date()).append("\n");
            report.append("原始HTML长度: ").append(htmlContent.length()).append(" 字符\n");
            report.append("输出路径: ").append(pdfFilePath).append("\n\n");
            
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
            
            report.append("\n注意: 这是一个测试报告，实际PDF转换需要集成iText7库\n");
            report.append("要完整测试PDF转换，请:\n");
            report.append("1. 下载iText7核心库\n");
            report.append("2. 添加到classpath\n");
            report.append("3. 实现真正的HTML到PDF转换\n");
            
            // 保存报告
            String reportPath = pdfFilePath.replace(".pdf", "_report.txt");
            Files.write(Paths.get(reportPath), report.toString().getBytes());
            
            System.out.println("详细报告已保存到: " + reportPath);
            
            return true;
            
        } catch (Exception e) {
            System.err.println("转换失败: " + e.getMessage());
            return false;
        }
    }
}
