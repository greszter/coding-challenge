package com.example.codingchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private TalkLengthAnalyzer analyzer;

    @Autowired
    private SectionCreationHelper helper;


    private final int MORNING_SECTION_LENGTH = 180;
    private final int AFTERNOON_SECTION_LENGTH = 240;
    private int trackCount = 1;

    public LinkedHashMap<String, ArrayList<String>> createSchedule(List<String> input) {
        var schedule = new LinkedHashMap<String, ArrayList<String>>();

        if (input.isEmpty()) {
            return schedule;
        }

        var talksWithLength = new HashMap<String, Integer>();

        input.forEach(talk -> {
            var length = analyzer.getTalkLength(talk);
            talksWithLength.put(talk, length);
        });

        while (!talksWithLength.isEmpty()) {
            getSchedule(talksWithLength, schedule);
        }

        return schedule;
    }

    private void getSchedule(HashMap<String, Integer> talksWithLength, LinkedHashMap<String, ArrayList<String>> schedule) {
        var morningSection = helper.createSection(talksWithLength, MORNING_SECTION_LENGTH);
        var afternoonSection = helper.createSection(talksWithLength, AFTERNOON_SECTION_LENGTH);
        var morningSectionTitles = morningSection.keySet().toArray(new String[morningSection.size()]);
        var afternoonSectionTitles = afternoonSection.keySet().toArray(new String[afternoonSection.size()]);
        var time = LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"));
        var talkList = new ArrayList<String>();

        for (String title : morningSectionTitles) {
            var length = morningSection.get(title);
            String titleWithTime = time + "AM " + title;

            talkList.add(titleWithTime);
            time = time.plusMinutes(length);
        }

        talkList.add("12:00PM Lunch");
        time = LocalTime.parse("13:00", DateTimeFormatter.ofPattern("HH:mm"));

        for (String title : afternoonSectionTitles) {
            var length = afternoonSection.get(title);
            String titleWithTime = time + "PM " + title;

            talkList.add(titleWithTime);
            time = time.plusMinutes(length);
        }

        schedule.put("Track " + trackCount, talkList);

        trackCount++;
    }
}
