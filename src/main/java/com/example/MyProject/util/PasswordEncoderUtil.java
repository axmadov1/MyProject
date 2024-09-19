package com.example.MyProject.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String adminPassword = "ac2178015";
        String worker1 = "worker1";
        String driver1 = "driver1";

        String encodedAdmin = encoder.encode(adminPassword);
        String encodedWorker = encoder.encode(worker1);
        String encodedDriver = encoder.encode(driver1);

        System.out.println("Admin : " + encodedAdmin +
                "\nWorker : " + encodedWorker + "\nDriver : " + encodedDriver);

    }
}
