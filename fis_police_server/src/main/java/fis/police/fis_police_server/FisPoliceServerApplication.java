package fis.police.fis_police_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FisPoliceServerApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FisPoliceServerApplication.class, args);

	}
}
