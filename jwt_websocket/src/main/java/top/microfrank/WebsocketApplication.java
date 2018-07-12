package top.microfrank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@SpringBootApplication
public class WebsocketApplication {
	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;
	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}
	public void send(){

		brokerMessagingTemplate.convertAndSend("/topic/greetings", "foo");
	}
}
