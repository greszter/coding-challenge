package com.example.codingchallenge;

import com.example.codingchallenge.service.ConferenceService;
import com.example.codingchallenge.service.TalkLengthAnalyzer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConferenceServiceTest {

	@Autowired
	private ConferenceService service;

	@MockBean
	private TalkLengthAnalyzer analyzer;

	@Test
	@DisplayName("Conference service return list of strings on proper input")
	public void testCreateListReturnsList() throws Exception {
		var input = getInput();
		when(analyzer.getTalkLength(anyString())).thenReturn(anyInt());

		var schedule = service.createSchedule(input);

		assertThat(schedule.size()).isEqualTo(0);
	}

	public List<String> getInput() {
		var input = new ArrayList<String>();

		input.add("Writing Fast Tests Against Enterprise Rails 60min");
		input.add("Overdoing it in Python 45min");

		return input;
	}

}
