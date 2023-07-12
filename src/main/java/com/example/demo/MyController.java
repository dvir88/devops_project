package com.example.demo;

import com.example.demo.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class MyController {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/addLog")
    public String addLogs() {
        Log log = new Log("addLog");
        String save = "INSERT INTO logs(Id, Date, Time, Method) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(save, log.getId(), log.getDate(), log.getTime(), log.getMethod());

        return log.toString();
    }

    @GetMapping("/logs")
    public List<Log> getAllLogs() {
        String getAll = "SELECT * FROM logs";
        return jdbcTemplate.query(getAll, (rs, rowNum) -> {
            Log log = new Log();
            log.setId(UUID.fromString(rs.getString("id")));
            log.setMethod(rs.getString("method"));
            log.setDate(rs.getDate("Date").toLocalDate());
            log.setTime(rs.getTime("Time"));
            return log;
        });
    }
}
