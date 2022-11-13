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

    public LinkedHashMap<String, ArrayList<String>> createSchedule(List<String> input) {
        trackCount = 1;
        var schedule = new LinkedHashMap<String, ArrayList<String>>();
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

    private void getSchedule(HashMap<String, Integer> talksWithLength, LinkedHashMap<String, ArrayList<String>> schedule) {
        var morningSection = helper.createSection(talksWithLength, 180);
        var afternoonSection = helper.createSection(talksWithLength, 240);
        var time = getTime("09:00");
        var talkList = new ArrayList<String>();

        scheduleMorningSectionWithLunch(time, morningSection, talkList);

        time = getTime("01:00");

        scheduleAfternoonSectionWithNetworkingEvent(time, afternoonSection, talkList);

        schedule.put("Track " + trackCount, talkList);

        trackCount++;
    }

    private void scheduleMorningSectionWithLunch(
            LocalTime time,
            HashMap<String, Integer> morningSection,
            ArrayList<String> talkList
    ) {
        var morningSectionTitles = morningSection.keySet().toArray(new String[0]);

        for (String title : morningSectionTitles) {
            var length = morningSection.get(title);
            String titleWithTime = time + "AM " + title;

            talkList.add(titleWithTime);
            time = time.plusMinutes(length);
        }

        talkList.add("12:00PM Lunch");
    }

    private void scheduleAfternoonSectionWithNetworkingEvent(
            LocalTime time,
            HashMap<String, Integer> afternoonSection,
            ArrayList<String> talkList
    ) {
        var afternoonSectionTitles = afternoonSection.keySet().toArray(new String[0]);

        for (String title : afternoonSectionTitles) {
            var length = afternoonSection.get(title);
            String titleWithTime = time + "PM " + title;

            talkList.add(titleWithTime);
            time = time.plusMinutes(length);
        }

        scheduleNetworkingEvent(time, talkList);
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
