package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Time")
    private Time time;

    @Column(name = "method")
    private String method;

    public Log(){}
    public Log(String method){

        this.method = method;
        this.id = UUID.randomUUID();
        this.date = LocalDate.now();
        this.time = Time.valueOf(LocalTime.now());
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMethod() {
        return method;
    }

    public Time getTime() {
        return time;
    }

    public UUID getId() {
        return id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("{\n" +
                "    \"id\": \"%s\",\n" +
                "    \"date\": \"%s\",\n" +
                "    \"time\": \"%s\",\n" +
                "    \"method\": \"%s\"\n" +
                "}", this.id, this.date, this.time, this.method);
    }
}
