package in.org.dig.induslockbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
scanBasePackages = "in.org.dig"
)
public class IndusLockBox1Application {

	public static void main(String[] args) {
		SpringApplication.run(IndusLockBox1Application.class, args);
	}

}
