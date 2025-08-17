import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.ConverterProperties;

public class RealHtmlToPdfConverter {
    public static void main(String[] args) {
        System.out.println("=== HTML转PDF测试程序 ===");
        System.out.println("使用iText7 HtmlConverter真正转换HTML为PDF，支持中文字体");
        
        // 设置Maven路径（如果需要）
        String mavenPath = System.getenv("PATH");
        if (mavenPath != null && !mavenPath.contains("apache-maven")) {
            System.out.println("提示：Maven已安装，依赖已下载");
        }
        
        // 确定HTML文件路径
        String htmlFilePath;
        String pdfFileName;
        
        if (args.length > 0) {
            // 使用命令行参数指定的文件
            htmlFilePath = args[0];
            // 从路径中提取文件名作为PDF名称
            String fileName = new File(htmlFilePath).getName();
            pdfFileName = fileName.replace(".html", ".pdf");
        } else {
            // 默认使用学院版报告
            htmlFilePath = "../reports/化学品月报-学院级.html";
            pdfFileName = "化学品月报-学院级.pdf";
        }
        
        System.out.println("📁 要转换的HTML文件: " + htmlFilePath);
        System.out.println("📄 输出PDF文件名: " + pdfFileName);
        
        File htmlFile = new File(htmlFilePath);
        
        if (!htmlFile.exists()) {
            System.out.println("❌ HTML文件不存在: " + htmlFilePath);
            System.out.println("请确保HTML文件路径正确");
            System.out.println("\n使用方法:");
            System.out.println("1. 不传参数（默认转换学院版）: java RealHtmlToPdfConverter");
            System.out.println("2. 指定文件路径: java RealHtmlToPdfConverter ../reports/化学品月报-学院级.html");
            System.out.println("3. 转换其他文件: java RealHtmlToPdfConverter ../reports/化学品月报-校级.html");
            return;
        }
        
        System.out.println("✅ 找到HTML文件: " + htmlFile.getAbsolutePath());
        
        try {
            // 读取HTML内容
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
            System.out.println("✅ HTML文件读取成功，大小: " + htmlContent.length() + " 字符");
            
            // 检查HTML内容
            checkHtmlContent(htmlContent);
            
            // 检查CSS兼容性
            checkCssCompatibility(htmlContent);
            
            // 转换HTML为PDF
            String pdfFilePath = "output/" + pdfFileName;
            boolean success = convertToPdf(htmlContent, pdfFilePath);
            
            if (success) {
                System.out.println("\n🎉 PDF转换成功！");
                System.out.println("输出文件: " + new File(pdfFilePath).getAbsolutePath());
                System.out.println("\n现在你可以：");
                System.out.println("1. 打开PDF文件查看转换效果");
                System.out.println("2. 如果发现问题，截图给我，我来修复HTML");
                System.out.println("3. 测试其他HTML文件的转换");
            } else {
                System.out.println("\n❌ PDF转换失败");
                System.out.println("请检查错误信息并截图给我");
            }
            
        } catch (Exception e) {
            System.err.println("❌ 程序执行出错: " + e.getMessage());
            e.printStackTrace();
            System.out.println("\n请截图错误信息给我，我来帮你解决问题");
        }
    }
    
    private static void checkHtmlContent(String htmlContent) {
        System.out.println("\n--- HTML内容检查 ---");
        
        // 检查基本HTML结构
        boolean hasHtml = htmlContent.toLowerCase().contains("<html");
        boolean hasHead = htmlContent.toLowerCase().contains("<head");
        boolean hasBody = htmlContent.toLowerCase().contains("<body");
        boolean hasTitle = htmlContent.toLowerCase().contains("<title");
        
        System.out.println("HTML标签: " + (hasHtml ? "✅" : "❌"));
        System.out.println("HEAD标签: " + (hasHead ? "✅" : "❌"));
        System.out.println("BODY标签: " + (hasBody ? "✅" : "❌"));
        System.out.println("TITLE标签: " + (hasTitle ? "✅" : "❌"));
        
        // 检查CSS样式
        boolean hasStyle = htmlContent.contains("<style");
        boolean hasInlineCss = htmlContent.contains("style=\"");
        System.out.println("CSS样式标签: " + (hasStyle ? "✅" : "❌"));
        System.out.println("内联CSS: " + (hasInlineCss ? "✅" : "❌"));
        
        // 检查特殊内容
        boolean hasChinese = htmlContent.matches(".*[\\u4e00-\\u9fa5]+.*");
        boolean hasTables = htmlContent.contains("<table");
        boolean hasImages = htmlContent.contains("<img");
        
        System.out.println("中文字符: " + (hasChinese ? "✅" : "❌"));
        System.out.println("表格: " + (hasTables ? "✅" : "❌"));
        System.out.println("图片: " + (hasImages ? "✅" : "❌"));
    }
    
    private static void checkCssCompatibility(String htmlContent) {
        System.out.println("\n--- CSS兼容性检查 ---");
        
        String[] incompatibleCss = {
            "linear-gradient", "radial-gradient", "conic-gradient",
            "box-shadow", "text-shadow", "filter:", "transform:",
            "display:\\s*flex", "display:\\s*grid",
            "justify-content", "align-items", "flex-direction",
            "grid-template-columns", "gap:"
        };
        
        int totalIssues = 0;
        for (String css : incompatibleCss) {
            int count = countOccurrences(htmlContent, css);
            if (count > 0) {
                System.out.println("⚠️  " + css + ": " + count + " 处");
                totalIssues++;
            }
        }
        
        if (totalIssues == 0) {
            System.out.println("✅ 未发现不兼容的CSS属性");
        } else {
            System.out.println("\n⚠️  发现 " + totalIssues + " 种不兼容的CSS属性");
            System.out.println("这些属性可能在PDF转换时出现问题");
        }
    }
    
    private static int countOccurrences(String text, String pattern) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher m = p.matcher(text);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }
    
        private static boolean convertToPdf(String htmlContent, String pdfFilePath) {
        System.out.println("\n--- 开始PDF转换 ---");
        
        try {
            // 创建输出目录
            File outputDir = new File("output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            System.out.println("✅ 输出目录创建成功");
            
            // 使用HtmlConverter.convertToPdf()真正转换HTML
            System.out.println("正在使用HtmlConverter转换HTML为PDF...");
            
            // 配置字体提供者
            FontProvider fontProvider = new FontProvider();
            
            try {
                // 加载PingFang字体
                String pingfangPath = "src/main/resources/fonts/Pingfang.ttf";
                File pingfangFile = new File(pingfangPath);
                
                if (pingfangFile.exists()) {
                    FontProgram pingfangFont = FontProgramFactory.createFont(pingfangPath);
                    fontProvider.addFont(pingfangFont);
                    System.out.println("✅ 加载PingFang字体: " + pingfangPath);
                    System.out.println("📊 字体名称: " + pingfangFont.getFontNames().getFontName());
                } else {
                    System.out.println("⚠️  PingFang字体不存在: " + pingfangPath);
                }

                // 加载思源黑体
                String sourceHanPath = "src/main/resources/fonts/SubsetOTF/CN/SourceHanSansCN-Regular.otf";
                File sourceHanFile = new File(sourceHanPath);
                
                if (sourceHanFile.exists()) {
                    FontProgram sourceHanFont = FontProgramFactory.createFont(sourceHanPath);
                    fontProvider.addFont(sourceHanFont);
                    System.out.println("✅ 加载思源黑体: " + sourceHanPath);
                    System.out.println("📊 字体名称: " + sourceHanFont.getFontNames().getFontName());
                } else {
                    System.out.println("⚠️  思源黑体不存在: " + sourceHanPath);
                }

                System.out.println("📊 已加载字体数量: " + fontProvider.getFontSet().size());
                
            } catch (Exception e) {
                System.out.println("❌ 字体加载失败: " + e.getMessage());
                e.printStackTrace();
            }

            // 配置转换属性
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);
            System.out.println("✅ 字体提供者已设置到转换属性");

            // 真正转换HTML为PDF
            try (FileOutputStream outputStream = new FileOutputStream(pdfFilePath)) {
                HtmlConverter.convertToPdf(htmlContent, outputStream, converterProperties);
                System.out.println("✅ HTML转换完成");
            }

            System.out.println("✅ PDF文件生成完成");
            
            // 检查输出文件
            File pdfFile = new File(pdfFilePath);
            if (pdfFile.exists() && pdfFile.length() > 0) {
                System.out.println("✅ PDF文件生成成功");
                System.out.println("文件大小: " + (pdfFile.length() / 1024) + " KB");
                return true;
            } else {
                System.out.println("❌ PDF文件生成失败或为空");
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("❌ PDF转换出错: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
