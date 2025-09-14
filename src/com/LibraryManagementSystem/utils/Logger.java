package com.LibraryManagementSystem.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");

    public static void info(String message) {
        log("INFO", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    public static void warn(String message) {
        log("WARN", message);
    }

    public static void log(String level , String message){
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("[%s] %s - %s", timestamp, level, message));
    }
}
