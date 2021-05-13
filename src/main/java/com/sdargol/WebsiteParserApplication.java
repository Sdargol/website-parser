package com.sdargol;

import com.sdargol.utils.StorageFolderUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebsiteParserApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(WebsiteParserApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/download/**")
				.addResourceLocations(StorageFolderUtil.createDir().toUri().toString());
	}
}
