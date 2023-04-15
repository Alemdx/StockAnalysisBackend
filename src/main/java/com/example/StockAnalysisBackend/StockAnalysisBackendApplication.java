package com.example.StockAnalysisBackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.example.StockAnalysisBackend.Mapper"}) //扫描mapper这个包
@ComponentScan
public class StockAnalysisBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAnalysisBackendApplication.class, args);
	}

}
