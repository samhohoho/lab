package com.samhoho.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class LabApplication {

//	private final DataSource dataSource;
//
//    public LabApplication(final DataSource dataSource) {
//		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
//		restTemplate.execute("select 1");
//        this.dataSource = dataSource;
//    }

    public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

//	@Override
//	public void run(final String... args) {
//		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
//		restTemplate.execute("select 1");
//	}
}
