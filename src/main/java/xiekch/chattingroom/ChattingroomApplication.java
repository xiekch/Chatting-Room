package xiekch.chattingroom;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xiekch.chattingroom.domain.Storage;

@SpringBootApplication
public class ChattingroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChattingroomApplication.class, args);
	}

	@PreDestroy
	public void destory() {
		System.out.println("Exit");
		Storage.getInstance().sync();
	}
}
