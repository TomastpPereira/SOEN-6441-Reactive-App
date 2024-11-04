package models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SubmissionSentiment {

    private static Set<String> loadSentimentFile(String path) throws IOException {
        return new HashSet<>(Files.readAllLines(Paths.get(path)));
    }

    public static String singleSentiment(String description, Set<String> happySet, Set<String> sadSet){
        List <String> descWords = Arrays.asList(description.split(" "));
        int totalWords = descWords.size();

        long countHappy = descWords.stream().filter(happySet::contains).count();
        long countSad = descWords.stream().filter(sadSet::contains).count();

        if ((double) countHappy/totalWords >= 0.7)
            return ":-)";
        else if ((double) countSad/totalWords >= 0.7)
            return ":-(";
        else
            return ":-|";
    }

    public static String determineSentiment(List<Video> videos){

        String mostCommonSentiment = "DID NOT PROCESS SENTIMENT";

        try {
            Set<String> happySet = loadSentimentFile("public/SentimentWords/HappyStrings.txt");
            Set<String> sadSet = loadSentimentFile("public/SentimentWords/SadStrings.txt");

            Map<String, Long> sentimentCount = videos.stream()
                    .map(Video::getDescription)
                    .map(desc -> singleSentiment(desc, happySet, sadSet))
                    .collect(Collectors.groupingBy(sentiment -> sentiment, Collectors.counting()));

            mostCommonSentiment = sentimentCount.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(":-|");
        }
        catch (IOException e){
            System.err.println("IO Exception in Reading Sentiment Words." + e.getMessage());
        }

        return mostCommonSentiment;
    }

}
