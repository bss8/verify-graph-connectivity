package com.example.connectedgraphs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConnectedgraphsApplication {
	@Value("${TARGET:World}")
	String message;

	@RestController
	class HelloworldController {
		@GetMapping("/graphs")
		String hello() {
			return "Graphs says Hello " + message + "!";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ConnectedgraphsApplication.class, args);
	}

}
