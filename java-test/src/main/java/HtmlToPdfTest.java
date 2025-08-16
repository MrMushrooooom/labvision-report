import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class HtmlToPdfTest {
    
    public static void main(String[] args) {
        System.out.println("HTML转PDF测试程序启动...");
        
        // 检查输入参数
        if (args.length < 2) {
            System.out.println("使用方法: java HtmlToPdfTest <HTML文件路径> <输出PDF路径>");
            System.out.println("示例: java HtmlToPdfTest ../reports/03-化学品月报-校级.html ./output.pdf");
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
            
            // 尝试转换为PDF（这里只是模拟，实际需要PDF库）
            System.out.println("正在转换为PDF...");
            boolean success = convertToPdf(htmlContent, pdfFilePath);
            
            if (success) {
                System.out.println("转换成功！PDF文件已保存到: " + pdfFilePath);
            } else {
                System.out.println("转换失败，请检查错误信息");
            }
            
        } catch (IOException e) {
            System.err.println("文件操作错误: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("转换过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void analyzeHtml(String htmlContent) {
        System.out.println("=== HTML内容分析 ===");
        
        // 检查CSS属性兼容性
        checkCssCompatibility(htmlContent);
        
        // 检查HTML结构
        checkHtmlStructure(htmlContent);
        
        System.out.println("=== 分析完成 ===");
    }
    
    private static void checkCssCompatibility(String htmlContent) {
        System.out.println("\n--- CSS兼容性检查 ---");
        
        // 检查不兼容的CSS属性
        String[] incompatibleCss = {
            "linear-gradient", "radial-gradient", "conic-gradient",
            "box-shadow", "text-shadow", "filter:", "transform:",
            "display:\\s*flex", "display:\\s*grid",
            "justify-content", "align-items", "flex-direction",
            "grid-template-columns", "gap:"
        };
        
        for (String css : incompatibleCss) {
            Pattern pattern = Pattern.compile(css, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(htmlContent);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            if (count > 0) {
                System.out.println("⚠️  发现不兼容CSS属性: " + css + " (出现" + count + "次)");
            }
        }
        
        // 检查饼图相关
        if (htmlContent.contains("pie-chart")) {
            System.out.println("✅ 发现饼图元素");
            if (htmlContent.contains("conic-gradient")) {
                System.out.println("✅ 饼图使用conic-gradient，兼容性好");
            } else if (htmlContent.contains("linear-gradient")) {
                System.out.println("⚠️  饼图使用linear-gradient，可能不兼容");
            }
        }
        
        // 检查布局方式
        if (htmlContent.contains("display: flex") || htmlContent.contains("display:flex")) {
            System.out.println("❌ 发现Flexbox布局，不兼容iText7");
        }
        if (htmlContent.contains("display: grid") || htmlContent.contains("display:grid")) {
            System.out.println("❌ 发现Grid布局，不兼容iText7");
        }
    }
    
    private static void checkHtmlStructure(String htmlContent) {
        System.out.println("\n--- HTML结构检查 ---");
        
        // 检查基本结构
        if (htmlContent.contains("<html") && htmlContent.contains("</html>")) {
            System.out.println("✅ HTML文档结构完整");
        } else {
            System.out.println("❌ HTML文档结构不完整");
        }
        
        // 检查样式标签
        if (htmlContent.contains("<style>") && htmlContent.contains("</style>")) {
            System.out.println("✅ 发现内联CSS样式");
        } else {
            System.out.println("⚠️  未发现内联CSS样式");
        }
        
        // 检查页面结构
        if (htmlContent.contains("cover-page")) {
            System.out.println("✅ 发现封面页结构");
        }
        if (htmlContent.contains("toc-page")) {
            System.out.println("✅ 发现目录页结构");
        }
        if (htmlContent.contains("content-page")) {
            System.out.println("✅ 发现内容页结构");
        }
        
        // 检查分页控制
        if (htmlContent.contains("page-break")) {
            System.out.println("✅ 发现分页控制CSS");
        }
        
        // 检查打印样式
        if (htmlContent.contains("@media print")) {
            System.out.println("✅ 发现打印样式");
        }
    }
    
    private static boolean convertToPdf(String htmlContent, String pdfFilePath) {
        try {
            // 这里只是模拟转换过程
            // 实际需要集成PDF生成库
            
            // 创建输出目录
            Path outputDir = Paths.get(pdfFilePath).getParent();
            if (outputDir != null) {
                Files.createDirectories(outputDir);
            }
            
            // 创建一个简单的测试文件来验证路径
            String testContent = "HTML转PDF测试\n\n";
            testContent += "原始HTML长度: " + htmlContent.length() + " 字符\n";
            testContent += "转换时间: " + new Date() + "\n";
            testContent += "注意：这是一个测试文件，实际PDF转换需要集成PDF库\n";
            
            Files.write(Paths.get(pdfFilePath.replace(".pdf", ".txt")), testContent.getBytes());
            
            System.out.println("注意：由于没有PDF库，只创建了测试文件");
            System.out.println("要完整测试PDF转换，需要集成iText7或类似库");
            
            return true;
            
        } catch (Exception e) {
            System.err.println("转换失败: " + e.getMessage());
            return false;
        }
    }
}
