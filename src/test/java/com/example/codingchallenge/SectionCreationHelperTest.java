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

        var morningSection = helper.createMorningSection(talks);

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

        var morningSection = helper.createMorningSection(talks);

        assertThat(morningSection.size()).isEqualTo(3);
    }
}
