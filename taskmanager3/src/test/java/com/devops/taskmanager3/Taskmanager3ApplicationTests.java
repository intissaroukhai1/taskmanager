package com.devops.taskmanager3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Taskmanager3ApplicationTests {
    @Test
    @Disabled("Disabled in CI because it requires a real database connection")

    void contextLoads() {
    }

}
