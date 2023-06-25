package com.wxsl.rosalind.framework.web;

import com.wxsl.rosalind.framework.web.controller.ConverterController;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        // open api 定义描述
        info = @Info(
                title = "rosalind API文档",
                version = "1.0.0"
        ),

        // 请求服务地址配置
        servers = {
                @Server(description = "dev", url = "/")
        }
)
@Configuration
public class OpenApiConfig {

    //全部
    @Bean
    public GroupedOpenApi rosalindApi() {
        String[] packagesToScan = {ConverterController.class.getPackage().getName()};
        return GroupedOpenApi.builder()
                .group("全部")
                .packagesToScan(packagesToScan)
                .build();
    }
}
