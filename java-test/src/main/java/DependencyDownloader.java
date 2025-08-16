import java.io.*;
import java.net.*;
import java.nio.file.*;

public class DependencyDownloader {
    
    private static final String MAVEN_BASE_URL = "https://repo1.maven.org/maven2";
    
    public static void main(String[] args) {
        System.out.println("=== Maven依赖下载器 ===");
        System.out.println("正在下载iText7和FreeMarker依赖...");
        
        // 创建lib目录
        try {
            Files.createDirectories(Paths.get("lib"));
        } catch (IOException e) {
            System.err.println("创建lib目录失败: " + e.getMessage());
            return;
        }
        
        // 下载iText7
        downloadDependency("com/itextpdf", "itext7-core", "7.2.5", "itext7-core-7.2.5.jar");
        
        // 下载FreeMarker
        downloadDependency("org/freemarker", "freemarker", "2.3.23", "freemarker-2.3.23.jar");
        
        System.out.println("\n=== 下载完成 ===");
        System.out.println("检查lib目录:");
        try {
            Files.list(Paths.get("lib")).forEach(path -> {
                try {
                    long size = Files.size(path);
                    System.out.println(path.getFileName() + " - " + size + " bytes");
                } catch (IOException e) {
                    System.out.println(path.getFileName() + " - 无法获取大小");
                }
            });
        } catch (IOException e) {
            System.err.println("无法列出lib目录: " + e.getMessage());
        }
    }
    
    private static void downloadDependency(String groupPath, String artifact, String version, String filename) {
        String url = String.format("%s/%s/%s/%s/%s-%s.jar", 
                                 MAVEN_BASE_URL, groupPath, artifact, version, artifact, version);
        
        System.out.println("\n正在下载: " + filename);
        System.out.println("URL: " + url);
        
        try {
            // 创建URL连接
            URL downloadUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36");
            
            // 检查响应码
            int responseCode = connection.getResponseCode();
            System.out.println("响应码: " + responseCode);
            
            if (responseCode == 200) {
                // 下载文件
                try (InputStream in = connection.getInputStream();
                     FileOutputStream out = new FileOutputStream("lib/" + filename)) {
                    
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    long totalBytes = 0;
                    
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                        totalBytes += bytesRead;
                    }
                    
                    System.out.println("✅ 下载成功: " + filename + " (" + totalBytes + " bytes)");
                }
            } else {
                System.err.println("❌ 下载失败，响应码: " + responseCode);
                
                // 尝试读取错误信息
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream()))) {
                    String line;
                    System.out.println("错误详情:");
                    while ((line = reader.readLine()) != null) {
                        System.out.println("  " + line);
                    }
                } catch (Exception e) {
                    System.out.println("无法读取错误详情: " + e.getMessage());
                }
            }
            
            connection.disconnect();
            
        } catch (Exception e) {
            System.err.println("❌ 下载异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
