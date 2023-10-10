package com.project.to_do_backend.script;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Component
public class StartupScript implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Value("${app.initialization.enabled}")
    private boolean isInitializationEnabled;

    public StartupScript(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        if (isInitializationEnabled && !isAlreadyInitialized()) {
            executeSqlFile("sql/initial/startupControl.sql");
            executeSqlFile("sql/initial/uuidExtension.sql");
            executeSqlFile("sql/initial/priorityTypes.sql");
            executeSqlFile("sql/initial/statusTypes.sql");
        }
    }

    private boolean isAlreadyInitialized() {
        String sql = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'startup_control')";
        return jdbcTemplate.queryForObject(sql, Boolean.class);
    }

    private void executeSqlFile(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        String sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        jdbcTemplate.execute(sql);
    }
}