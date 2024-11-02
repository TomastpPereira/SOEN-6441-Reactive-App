package controllers;
import models.Video;
import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchController extends Controller {
    private final YouTube youtubeApiClient;
    private static final int RESULTS_PER_QUERY = 10;
    private static final int MAX_SEARCHES = 10;
    private static final LinkedList<SearchResult> searchHistory = new LinkedList<>();

    @Inject
    public SearchController(YouTube youtubeApiClient) {
        this.youtubeApiClient = youtubeApiClient;
    }

    public Result searchVideos(String query) {
        try {
            JsonNode videosJson = youtubeApiClient.searchVideos(query, RESULTS_PER_QUERY);
            List<Video> videos = new ArrayList<>();
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

            if (searchHistory.size() == MAX_SEARCHES) {
                searchHistory.removeLast();
            }
            searchHistory.addFirst(new SearchResult(query, videos));

            //return ok(views.html.index.render(query, videos));
            return ok(views.html.index.render(searchHistory));
        } catch (Exception e) {
            return internalServerError("Video Not Found or some other errors : " + e.getMessage());
        }
    }

    public static class SearchResult {
        public String query;
        public List<Video> videos;
        public SearchResult(String query, List<Video> videos) {
            this.query = query;
            this.videos = videos;
        }

    }

}
