package com.example.codingchallenge.service;

import org.springframework.stereotype.Service;

@Service
public class TalkLengthAnalyzer {

    public int getTalkLength(String input) {
        var words = input.split(" ");
        var lastWord = words[words.length - 1];

        if (lastWord.equalsIgnoreCase("lightning")) {
            return 5;
        } else {
            var talkLength = lastWord.substring(0, lastWord.length() - 3);

            try {
                return Integer.parseInt(talkLength);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}
