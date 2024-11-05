package models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SubmissionSentiment {

    public SubmissionSentiment(){}

    public Set<String> loadSentimentFile(String path){
        List<String> readLines = null;
        try{
            readLines = Files.readAllLines(Paths.get(path));
        }
        catch (IOException e){
            System.err.println("Unable to Read Sentiment File: " + e);
        }
        if (readLines != null)
            return new HashSet<>(readLines);
        else return null;
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

    public String determineSentiment(List<Video> videos){

        Set<String> happySet = loadSentimentFile("public/SentimentWords/HappyStrings.txt");
        Set<String> sadSet = loadSentimentFile("public/SentimentWords/SadStrings.txt");

        Map<String, Long> sentimentCount = videos.stream()
                .map(Video::getDescription)
                .map(desc -> singleSentiment(desc, happySet, sadSet))
                .collect(Collectors.groupingBy(sentiment -> sentiment, Collectors.counting()));

        // Gets the sentiment which appears the most from the set
        return sentimentCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(":-|");



    }

}
