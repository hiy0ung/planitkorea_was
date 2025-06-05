package org.koreait.planitkorea.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/config/WebMvcConfig.java
    @Value("${file.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + uploadPath)
=======
    @Value("${user.dir}")
    private String projectPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String rootPath = projectPath + "/image/";
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///" + rootPath)
>>>>>>> 65e6b84 (fix: reservation 로직 수정):PlanItKorea/src/main/java/org/koreait/planitkorea/config/WebMvcConfig.java
=======
    @Value("${file.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + uploadPath)
>>>>>>> b93696e (fix: 이미지처리설정변경)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8);
                        return super.getResource(resourcePath, location);
                    }
                });
    }
}
