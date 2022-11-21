package net.ecnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"net.ecnu.**.mapper"})
@SpringBootApplication(scanBasePackages = {"net.ecnu"})
public class SoeTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoeTestApplication.class,args);
    }
}
