package com.example.StockAnalysisBackend.Config;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @date 下午2:19
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket coreApiConfig(){
        List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("swagger测试用(模拟token传入)非必填 header").modelRef(new
                ModelRef("string")).parameterType("header").required(false);

        pars.add((Parameter) tokenPar.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .groupName("adminApi")
                .select()
                //只显示admin下面的路径
                .paths(Predicates.and(PathSelectors.regex("/.*")))
                .build().globalOperationParameters(pars);
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("股票分析系统")
                .description("股票分析系统接口描述")
                .version("1.0")
                .contact(new Contact("顾城","http://baidu.com","1245193245@qq.com"))
                .build();
    }
}
