package joseph.test.water.overflow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WaterOverflowApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WaterOverflowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start");
    }
}
