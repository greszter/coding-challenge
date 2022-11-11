package com.example.codingchallenge.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SectionCreationHelper {

    public HashMap<String, Integer> createSection(HashMap<String, Integer> talks, int sectionLength) {
        var morningTalks = new HashMap<String, Integer>();
        var talkLengthSum = 0;

        while (talkLengthSum < sectionLength) {
            var talkTitle = talks.keySet().stream().findFirst();
            if (talkTitle.isPresent()) {
                var talkLength = talks.get(talkTitle.get());

                if (talkLengthSum + talkLength > sectionLength) {
                    var remainingTime = sectionLength - talkLengthSum;
                    var optionalTalk = talks.entrySet().stream().filter(e -> e.getValue() <= remainingTime).findFirst();

                    if (optionalTalk.isPresent()) {
                        var title = optionalTalk.get().getKey();

                        morningTalks.put(title, optionalTalk.get().getValue());
                        talkLengthSum += optionalTalk.get().getValue();
                        talks.remove(title);
                    } else {
                        break;
                    }

                } else {
                    morningTalks.put(talkTitle.get(), talkLength);
                    talkLengthSum += talkLength;
                    talks.remove(talkTitle.get());
                }
            }
        }

        return morningTalks;
    }
}
