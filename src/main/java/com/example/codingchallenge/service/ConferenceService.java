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

    private int trackCount = 1;
    private final int MORNING_SECTION_LENGTH = 180;
    private final int EVENINIG_SECTION_LENGTH = 240;

    public LinkedHashMap<String, List<String>> createSchedule(List<String> input) {
        trackCount = 1;
        var schedule = new LinkedHashMap<String, List<String>>();
        var talksWithLength = new HashMap<String, Integer>();

        if (input.isEmpty()) {
            return schedule;
        }

        input.forEach(talk -> {
            var length = analyzer.getTalkLength(talk);

            if (length != 0) {
                talksWithLength.put(talk, length);
            }
        });

        while (!talksWithLength.isEmpty()) {
            getSchedule(talksWithLength, schedule);
        }

        return schedule;
    }

    private void getSchedule(HashMap<String, Integer> talksWithLength, LinkedHashMap<String, List<String>> schedule) {
        var time = getTime("09:00");
        var talkList = new ArrayList<String>();

        scheduleSection(time, talksWithLength, talkList, MORNING_SECTION_LENGTH);

        time = getTime("01:00");

        scheduleSection(time, talksWithLength, talkList, EVENINIG_SECTION_LENGTH);

        schedule.put("Track " + trackCount, talkList);

        trackCount++;
    }

    private void scheduleSection(
            LocalTime time,
            HashMap<String, Integer> talksWithLength,
            ArrayList<String> talkList,
            int sectionLength
    ) {
        var section = helper.createSection(talksWithLength, sectionLength);
        var sectionTitles = section.keySet().toArray(new String[0]);

        var partOfDay = sectionLength == MORNING_SECTION_LENGTH ? "AM " : "PM ";

        for (String title : sectionTitles) {
            var length = section.get(title);
            String titleWithTime = time + partOfDay + title;

            talkList.add(titleWithTime);
            time = time.plusMinutes(length);
        }

        if (sectionLength == MORNING_SECTION_LENGTH) {
            talkList.add("12:00PM Lunch");
        } else {
            scheduleNetworkingEvent(time, talkList);
        }
    }

    private void scheduleNetworkingEvent(LocalTime time, ArrayList<String> talkList) {
        if (time.isBefore(getTime("04:00")) || time.equals(getTime("04:00"))) {
            talkList.add("04:00PM Networking Event");
        } else {
            talkList.add("05:00PM Networking Event");
        }
    }

    private LocalTime getTime(String timeInString) {
        return LocalTime.parse(timeInString, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
