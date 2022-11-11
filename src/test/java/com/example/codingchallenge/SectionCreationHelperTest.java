package com.example.codingchallenge;

import com.example.codingchallenge.service.SectionCreationHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SectionCreationHelperTest {

    private final int MORNING_SECTION_LENGTH = 180;
    private final int AFTERNOON_SECTION_LENGTH = 240;

    @Autowired
    private SectionCreationHelper helper;

    @Test
    @DisplayName("Morning section creator returns talks with length sum under three hours")
    public void testCreateMorningSection() throws Exception {
        var talks = new HashMap<String, Integer>();

        talks.put("Writing Fast Tests Against Enterprise Rails 60min", 60);
        talks.put("Overdoing it in Python 45min", 45);
        talks.put("Lua for the Masses 30min", 45);
        talks.put("Ruby Errors from Mismatched Gem Versions 45min", 45);
        talks.put("Common Ruby Errors 45min", 45);

        var morningSection = helper.createSection(talks, MORNING_SECTION_LENGTH);

        assertThat(morningSection.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Morning section creator checks if additional talk's length can fit in section")
    public void testCreateMorningSectionChecksForTooLongSection() throws Exception {
        var talks = new HashMap<String, Integer>();

        talks.put("Writing Fast Tests Against Enterprise Rails 60min", 59);
        talks.put("Overdoing it in Python 45min", 59);
        talks.put("Lua for the Masses 30min", 59);
        talks.put("Ruby Errors from Mismatched Gem Versions 45min", 59);
        talks.put("Common Ruby Errors 45min", 59);

        var morningSection = helper.createSection(talks, MORNING_SECTION_LENGTH);

        assertThat(morningSection.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Afternoon section creator returns talks with length sum under four hours")
    public void testCreateAfternoonSection() throws Exception {
        var talks = new HashMap<String, Integer>();

        talks.put("Writing Fast Tests Against Enterprise Rails 60min", 60);
        talks.put("Overdoing it in Python 45min", 45);
        talks.put("Lua for the Masses 30min", 45);
        talks.put("Ruby Errors from Mismatched Gem Versions 45min", 45);
        talks.put("Common Ruby Errors 45min", 45);
        talks.put("Communicating Over Distance 60min", 60);
        talks.put("Accounting-Driven Development 45min", 45);

        var afternoonSection = helper.createSection(talks, AFTERNOON_SECTION_LENGTH);

        assertThat(afternoonSection.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Afternoon section creator checks if additional talk's length can fit in section")
    public void testCreateAfternoonSectionChecksForTooLongSection() throws Exception {
        var talks = new HashMap<String, Integer>();

        talks.put("Writing Fast Tests Against Enterprise Rails 60min", 59);
        talks.put("Overdoing it in Python 45min", 59);
        talks.put("Lua for the Masses 30min", 59);
        talks.put("Ruby Errors from Mismatched Gem Versions 45min", 59);
        talks.put("Common Ruby Errors 45min", 59);
        talks.put("Communicating Over Distance 60min", 59);
        talks.put("Accounting-Driven Development 45min", 59);

        var afternoonSection = helper.createSection(talks, AFTERNOON_SECTION_LENGTH);

        assertThat(afternoonSection.size()).isEqualTo(4);
    }
}
