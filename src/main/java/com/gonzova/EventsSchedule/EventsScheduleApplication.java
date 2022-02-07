package com.gonzova.EventsSchedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class EventsScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsScheduleApplication.class, args);
	}

}
