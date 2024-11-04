package models;

import com.google.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchHistoryModel {

    private final YouTube youtubeApiClient;
    private static final int MAX_SEARCHES = 10;
    private static final int RESULTS_PER_QUERY = 10;
    private final LinkedList<SearchResult> searchHistory = new LinkedList<>();

    @Inject
    public SearchHistoryModel(YouTube youtubeApiClient) {
        this.youtubeApiClient = youtubeApiClient;
    }

    public List<Video> queryYoutube(String query){

        List<Video> videos = new ArrayList<>();

        try {
            JsonNode videosJson = youtubeApiClient.searchVideos(query, RESULTS_PER_QUERY);
            if (videosJson != null && videosJson.get("items") != null) {
                for (JsonNode item : videosJson.get("items")) {
                    Video video = new Video(
                            item.get("id").get("videoId").asText(),
                            item.get("snippet").get("title").asText(),
                            item.get("snippet").get("channelId").asText(),
                            item.get("snippet").get("channelTitle").asText(),
                            item.get("snippet").get("description").asText(),
                            item.get("snippet").get("thumbnails").get("default").get("url").asText()
                    );
                    System.out.println(video.thumbnail);
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error with API response: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Other Error: " + e.getMessage());
        }
        return videos;
    }
    public JsonNode queryYoutube(String query,int Result_num){

        JsonNode videosJson = null;

        try {
            videosJson = youtubeApiClient.searchVideos(query, Result_num);

        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error with API response: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Other Error: " + e.getMessage());
        }
        return videosJson;
    }

    public void queryAndStore(String query){
        addSearchResult(query, queryYoutube(query));
    }
    public void addSearchResult(String query, List<Video> videos) {
        if (searchHistory.size() == MAX_SEARCHES) {
            searchHistory.removeLast();
        }
        searchHistory.addFirst(new SearchResult(query, videos));
    }

    public LinkedList<SearchResult> getSearchHistory() {
        return searchHistory;
    }

    // CAN LATER ADD ANY FUNCTIONALITIES NEEDED

}
