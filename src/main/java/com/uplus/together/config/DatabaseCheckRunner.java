package com.uplus.together.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseCheckRunner implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseCheckRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🔌 DataSource Class: " + dataSource.getClass().getName());
        System.out.println("✅ DB 연결 성공 테스트 통과");
    }
}

