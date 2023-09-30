package com.project.to_do_backend.script;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Component
public class StartupScript implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public StartupScript(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        executeSqlFile("sql/initial/uuidExtension.sql");
        executeSqlFile("sql/initial/priorityTypes.sql");
        executeSqlFile("sql/initial/statusTypes.sql");
    }

    private void executeSqlFile(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        String sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        jdbcTemplate.execute(sql);
    }
}
