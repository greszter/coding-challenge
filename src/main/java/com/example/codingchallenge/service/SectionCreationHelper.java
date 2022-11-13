package com.example.codingchallenge.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SectionCreationHelper {

    public HashMap<String, Integer> createSection(HashMap<String, Integer> talksInput, int sectionLengthInMinutes) {
        var talksWithTime = new HashMap<String, Integer>();
        var talkLengthSum = 0;

        while (talkLengthSum < sectionLengthInMinutes && !talksInput.isEmpty()) {
            var talkTitle = talksInput.keySet().stream().findFirst();
            var talkLength = talksInput.get(talkTitle.get());

            if (talkLengthSum + talkLength > sectionLengthInMinutes) {
                var remainingTime = sectionLengthInMinutes - talkLengthSum;
                var optionalTalk = talksInput.entrySet().stream().filter(e -> e.getValue() <= remainingTime).findFirst();

                if (optionalTalk.isPresent()) {
                    var title = optionalTalk.get().getKey();

                    talksWithTime.put(title, optionalTalk.get().getValue());
                    talkLengthSum += optionalTalk.get().getValue();
                    talksInput.remove(title);
                } else {
                    break;
                }
            } else {
                talksWithTime.put(talkTitle.get(), talkLength);
                talkLengthSum += talkLength;
                talksInput.remove(talkTitle.get());
            }
        }
        return talksWithTime;
    }
}
