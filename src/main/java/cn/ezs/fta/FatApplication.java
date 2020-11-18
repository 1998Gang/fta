package cn.ezs.fta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 1998Gang
 */
@MapperScan("cn.ezs.fta.mapper")
@SpringBootApplication()
@EnableTransactionManagement
public class FatApplication {
    public static void main(String[] args) {
        SpringApplication.run(FatApplication.class, args);
    }
}
