# Itemis Coding Challenge

## Problem 2: Conference Track Management

This is a test assignment with the following entry point:

- Create schedule: `/create-schedule`
    - The endpoint expects a `List<String>` that contains all the talks for the conference that has to be added to the schedule
    - It returns a map in which the key is the track number and the value is the list of talks

To setup and run the application use `mvn clean install` and then run the application with `mvn spring-boot:run`