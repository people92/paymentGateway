package com.ssg.homework.t2021hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
public class T2021hwApplication {

	public static void main(String[] args) {
		SpringApplication.run(T2021hwApplication.class, args);
	}

}
