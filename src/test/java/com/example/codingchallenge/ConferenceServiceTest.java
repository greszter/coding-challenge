package com.example.codingchallenge;

import com.example.codingchallenge.service.ConferenceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConferenceServiceTest {

	@Autowired
	private ConferenceService service;

	@Test
	@DisplayName("Conference service return list of strings on proper input")
	public void testCreateListReturnsList() throws Exception {
		var input = getInput();
		var schedule = service.createSchedule(input);

		assertThat(schedule.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("Networking event starts at 4PM when talks end until then")
	public void testNetworkingEventStartsAt4Pm() throws Exception {
		var input = getInputForShorterTrack();
		var schedule = service.createSchedule(input);
		var trackOne = schedule.get("Track 1");

		assertThat(trackOne.get(trackOne.size() - 1)).startsWith("04:00PM");
	}

	@Test
	@DisplayName("Networking event starts at 5PM when talks end after 4PM")
	public void testNetworkingEventStartsAt5Pm() throws Exception {
		var input = getInputForLongerTrack();
		var schedule = service.createSchedule(input);
		var trackOne = schedule.get("Track 1");

		assertThat(trackOne.get(trackOne.size() - 1)).startsWith("05:00PM");
	}

	public List<String> getInput() {
		var input = new ArrayList<String>();

		input.add("Writing Fast Tests Against Enterprise Rails 60min");
		input.add("Overdoing it in Python 45min");
		input.add("Lua for the Masses 30min");
		input.add("Ruby Errors from Mismatched Gem Versions 45min");
		input.add("Common Ruby Errors 45min");
		input.add("Rails for Python Developers lightning");
		input.add("Communicating Over Distance 60min");
		input.add("Accounting-Driven Development 45min");
		input.add("Woah 30min");
		input.add("Sit Down and Write 30min");
		input.add("Pair Programming vs Noise 45min");
		input.add("Rails Magic 60min");
		input.add("Ruby on Rails: Why We Should Move On 60min");
		input.add("Clojure Ate Scala (on my project) 45min");
		input.add("Programming in the Boondocks of Seattle 30min");
		input.add("Ruby vs. Clojure for Back-End Development 30min");
		input.add("Ruby on Rails Legacy App Maintenance 60min");
		input.add("A World Without HackerNews 30min");
		input.add("User Interface CSS in Rails Apps 30min");

		return input;
	}

	public List<String> getInputForShorterTrack() {
		var input = new ArrayList<String>();

		input.add("Writing Fast Tests Against Enterprise Rails 60min");
		input.add("Overdoing it in Python 60min");
		input.add("Lua for the Masses 60min");
		input.add("Ruby Errors from Mismatched Gem Versions 60min");
		input.add("Common Ruby Errors 60min");
		input.add("Rails for Python Developers 60min");

		return input;
	}

	public List<String> getInputForLongerTrack() {
		var input = new ArrayList<String>();

		input.add("Writing Fast Tests Against Enterprise Rails 60min");
		input.add("Overdoing it in Python 60min");
		input.add("Lua for the Masses 60min");
		input.add("Ruby Errors from Mismatched Gem Versions 60min");
		input.add("Common Ruby Errors 60min");
		input.add("Rails for Python Developers 60min");
		input.add("Communicating Over Distance 60min");

		return input;
	}
}
