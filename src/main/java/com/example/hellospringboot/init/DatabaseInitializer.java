package com.example.hellospringboot.init;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer {


    private final DataSource dataSource;

    @PostConstruct
    public void initDatabase() {
        log.info("Database init started.");
        init();
        log.info("Data base init completed.");
    }

    public void init() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS `User` (" +
                "`id` INT AUTO_INCREMENT PRIMARY KEY, " +
                "`username` VARCHAR(255) NOT NULL, " +
                "`email` VARCHAR(255) NOT NULL, " +
                "`password` VARCHAR(255) NOT NULL, " +
                "`created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        String insertDataSql = "INSERT INTO `User` (username, email, password) VALUES " +
                "('Alice', 'alice@example.com', 'password1'), " +
                "('Bob', 'bob@example.com', 'password2'), " +
                "('Charlie', 'charlie@example.com', 'password3');";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(createTableSql);
            statement.execute(insertDataSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}