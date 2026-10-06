package com.djibrilhaddadi.chatrooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.djibrilhaddadi.chatrooms.repository")
public class ChatRoomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatRoomsApplication.class, args);
	}

}
