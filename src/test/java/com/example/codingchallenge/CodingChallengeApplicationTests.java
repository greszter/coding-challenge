package com.example.codingchallenge;

import com.example.codingchallenge.service.ConferenceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CodingChallengeApplicationTests {

	@Autowired
	private ConferenceService service;

	@Test
	@DisplayName("Conference service return list of strings on proper input")
	public void testCreateListReturnsList() throws Exception {
		var input = new ArrayList<String>();

		input.add("Writing Fast Tests Against Enterprise Rails 60min");
		input.add("Overdoing it in Python 45min");

		var schedule = service.createSchedule(new ArrayList<>());

		assertThat(schedule.size()).isEqualTo(0);
	}

}
