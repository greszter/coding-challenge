package com.example.codingchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private TalkLengthAnalyzer analyzer;

    public List<String> createSchedule(List<String> input) {
        if (input.isEmpty()) {
            return input;
        }

        var talksWithLength = new HashMap<String, Integer>();

        input.forEach(talk -> {
            var length = analyzer.getTalkLength(talk);
            talksWithLength.put(talk, length);
        });

        var schedule = new ArrayList<String>();

        return schedule;
    }
}
