package com.example.codingchallenge.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SectionCreationHelper {

    public HashMap<String, Integer> createSection(HashMap<String, Integer> talksInput, int sectionLengthInMinutes) {
        var talksWithTime = new HashMap<String, Integer>();
        var talkLengthSum = 0;

        while (talkLengthSum < sectionLengthInMinutes && !talksInput.isEmpty()) {
            var remainingTime = sectionLengthInMinutes - talkLengthSum;
            var optionalTalk = talksInput.entrySet().stream().filter(e -> e.getValue() <= remainingTime).findFirst();

            if (optionalTalk.isEmpty()) {
                break;
            }

            var title = optionalTalk.get().getKey();
            var length = optionalTalk.get().getValue();

            talksWithTime.put(title, length);
            talkLengthSum += length;
            talksInput.remove(title);
        }
        return talksWithTime;
    }
}
