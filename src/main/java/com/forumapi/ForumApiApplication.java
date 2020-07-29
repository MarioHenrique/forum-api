package com.forumapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
@EnableAsync
public class ForumApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApiApplication.class, args);
	}

}
