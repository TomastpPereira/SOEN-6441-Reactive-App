package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordStats {

    private static final Set<String> ignore = Set.of("the", "and", "in", "of", "a", "to", "is", "it", "that", "with", "as", "for", "on");

    public static Map<String, Long> generateWordStats(JsonNode videos) {
        // Check if 'videos' contains 'items'
        if (videos == null || !videos.has("items")) {
            return Collections.emptyMap();
        }

        return StreamSupport.stream(videos.get("items").spliterator(), false)
                .flatMap(video -> {
                    JsonNode snippet = video.get("snippet");
                    if (snippet == null) return Stream.empty();

                    String title = snippet.has("title") ? snippet.get("title").asText() : "";
                    String description = snippet.has("description") ? snippet.get("description").asText() : "";
                    return Arrays.stream(normalizeText(title + " " + description));
                })
                .filter(word -> !word.isEmpty() && !ignore.contains(word)) // Filter out empty words and stop words
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static String[] normalizeText(String text) {
        return text.toLowerCase().split("\\W+");
    }
}
