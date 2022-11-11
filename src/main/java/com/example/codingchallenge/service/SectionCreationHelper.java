package com.example.codingchallenge.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SectionCreationHelper {

    public List<String> createMorningSection(HashMap<String, Integer> talks) {
        var morningTalks = new ArrayList<String>();
        var talkLengthSum = 0;

        while (talkLengthSum < 180) {
            var talkTitle = talks.keySet().stream().findFirst();
            if (talkTitle.isPresent()) {
                var talkLength = talks.get(talkTitle.get());

                if (talkLengthSum + talkLength > 180) {
                   var remainingTime = 180 - talkLengthSum;

                   var optionalTalk = talks.entrySet().stream().filter(e -> e.getValue() <= remainingTime).findFirst();

                   if (optionalTalk.isPresent()) {
                       var title = optionalTalk.get().getKey();
                       morningTalks.add(title);
                       talkLengthSum += optionalTalk.get().getValue();
                       talks.remove(title);
                   } else {
                       break;
                   }
                } else {
                    morningTalks.add(talkTitle.get());
                    talkLengthSum += talkLength;
                    talks.remove(talkTitle.get());
                }
            }
        }

        return morningTalks;
    }
}
