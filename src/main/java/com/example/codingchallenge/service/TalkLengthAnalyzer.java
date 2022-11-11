package com.example.codingchallenge.service;

import org.springframework.stereotype.Service;

@Service
public class TalkLengthAnalyzer {

    private final String LIGHTNING = "lightning";

    public int getTalkLength(String input) {
        var words = input.split(" ");
        var lastWord = words[words.length - 1];

        if (lastWord.equalsIgnoreCase(LIGHTNING)) {
            return 5;
        } else {
            var talkLength = lastWord.substring(0, lastWord.length() - 3);

            return Integer.parseInt(talkLength);
        }
    }
}
