package models;

import com.google.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.SearchController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
                            item.get("snippet").get("channelTitle").asText()
                    );
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


    public Channel getChannelDetails(String channelId){
        try{
            JsonNode channelJson = youtubeApiClient.getChannelDetails(channelId);
            if(channelJson != null && channelJson.has("items") && channelJson.get("items").size() > 0){
                JsonNode channelNode = channelJson.get("items").get(0);
                String id = channelNode.get("id").asText();
                String title = channelNode.get("snippet").get("title").asText();
                String description = channelNode.get("snippet").get("description").asText();
                String publishedAt = channelNode.get("snippet").get("publishedAt").asText();
                String country = channelNode.get("snippet").has("country") ? channelNode.get("snippet").get("country").asText() : "N/A";
                String customUrl = channelNode.get("snippet").has("customUrl") ? channelNode.get("snippet").get("customUrl").asText() : "N/A";
                String thumbnailUrl = channelNode.get("snippet").get("thumbnails").get("default").get("url").asText();
                int subscriberCount = channelNode.get("statistics").get("subscriberCount").asInt();
                boolean hiddenSubscriberCount = channelNode.get("statistics").get("hiddenSubscriberCount").asBoolean();
                int viewCount = channelNode.get("statistics").get("viewCount").asInt();
                int videoCount = channelNode.get("statistics").get("videoCount").asInt();

                return new Channel(id, title, description, publishedAt, country, customUrl, thumbnailUrl,
                        subscriberCount, hiddenSubscriberCount, viewCount, videoCount);
            }
        } catch (Exception e) {
            System.err.println("Retrieving channel details encounter a problem : " + e.getMessage());

        }
        return null;
    }

    public List<Video> getChannelVideos(String channelId, int maxResults){

        List<Video> channelVideos = new ArrayList<>();
        try {
            JsonNode videosJson = youtubeApiClient.getVideosByChannelId(channelId, maxResults);
            if (videosJson != null && videosJson.get("items") != null) {
                for (JsonNode item : videosJson.get("items")) {
                    Video video = new Video(
                            item.get("id").get("videoId").asText(),
                            item.get("snippet").get("title").asText(),
                            item.get("snippet").get("channelId").asText(),
                            item.get("snippet").get("channelTitle").asText()
                    );
                    channelVideos.add(video);
                }
            }

        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error with API response: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Other Error: " + e.getMessage());
        }
        return channelVideos;
    }
}
