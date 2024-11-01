package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Wordstats {
    public static Map<String, Long> generateWordStats(JsonNode videos) {
        // Flatten all titles and descriptions into a single stream of words
        return StreamSupport.stream(videos.get("items").spliterator(), false)
                .flatMap(video -> {
                    String title = video.get("snippet").get("title").asText();
                    String description = video.get("snippet").get("description").asText();
                    return Arrays.stream((title + " " + description).toLowerCase().split("\\W+"));
                })
                .filter(word -> !word.isEmpty()) // Filter out empty words
                .collect(Collectors.groupingBy(word -> word, Collectors.counting())) // Group by word and count occurrences
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // Sort by frequency in descending order
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); // Collect as a LinkedHashMap
    }

}
