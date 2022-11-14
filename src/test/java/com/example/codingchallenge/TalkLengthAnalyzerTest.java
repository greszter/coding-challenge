package com.example.codingchallenge;

import com.example.codingchallenge.service.TalkLengthAnalyzer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TalkLengthAnalyzerTest {

    @Autowired
    private TalkLengthAnalyzer analyzer;

    @Test
    @DisplayName("Analyzer returns talk length in minutes")
    public void testGetTalkLengthReturnLengthInMinute() throws Exception {

        var length = analyzer.getTalkLength("Writing Fast Tests Against Enterprise Rails 60min");

        assertThat(length).isEqualTo(60);
    }

    @Test
    @DisplayName("Analyzer returns 5 for lightning talk")
    public void testGetTalkLengthReturnFiveForLightning() throws Exception {

        var length = analyzer.getTalkLength("Rails for Python Developers lightning");

        assertThat(length).isEqualTo(5);
    }

    @Test
    @DisplayName("Analyzer returns 0 for blank input")
    public void testGetTalkLengthReturnZeroForBlankInput() throws Exception {

        var length = analyzer.getTalkLength(" ");

        assertThat(length).isEqualTo(0);
    }
}
