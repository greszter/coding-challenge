package com.example.codingchallenge.controller;

import com.example.codingchallenge.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @PostMapping("create-schedule")
    public ResponseEntity<LinkedHashMap<String, List<String>>> createSchedule(@RequestBody List<String> input) {
        return ResponseEntity.ok(conferenceService.createSchedule(input));
    }
}
