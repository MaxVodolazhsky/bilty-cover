package ru.bilty.cover;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(SpringApplication.run(BiltyCoverStarterApplication.class).isRunning());
    }
}
