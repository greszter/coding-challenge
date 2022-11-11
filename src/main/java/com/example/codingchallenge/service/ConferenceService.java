package com.example.codingchallenge.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConferenceService {

    public List<String> createSchedule(List<String> input) {
        if (input.isEmpty()) {
            return input;
        }

        var schedule = new ArrayList<String>();

        return schedule;
    }
}
