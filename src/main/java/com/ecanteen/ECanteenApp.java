package com.ecanteen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.ecanteen.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({LiquibaseProperties.class,ApplicationProperties.class})
public class ECanteenApp {

    public static void main(String[] args) {
        SpringApplication.run(ECanteenApp.class, args);

        try (Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://127.0.0.1:5432/test", "postgres", "password")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
