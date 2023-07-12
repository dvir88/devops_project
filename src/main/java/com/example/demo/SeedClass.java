package com.example.demo;

import com.example.demo.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeedClass implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        Log log = new Log("SeedClass");
        String save = "INSERT INTO logs(Id, Date, Time, Method) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(save, log.getId(), log.getDate(), log.getTime(), log.getMethod());

    }
}
