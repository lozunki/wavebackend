package org.lozunki.wavebackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WavebackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WavebackendApplication.class, args);
    }

}
