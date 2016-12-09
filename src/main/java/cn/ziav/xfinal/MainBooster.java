package cn.ziav.xfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MainBooster {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainBooster.class, args);
	}
}