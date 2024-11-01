package controllers;
import Models.Video;
import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class SearchController extends Controller {
    private final YouTube youtubeApiClient;

    @Inject
    public SearchController(YouTube youtubeApiClient) {
        this.youtubeApiClient = youtubeApiClient;
    }

    public Result searchVideos(String query) {
        try {
            JsonNode videosJson = youtubeApiClient.searchVideos(query, 10);
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

            return ok(views.html.index.render(query, videos));
        } catch (Exception e) {
            return internalServerError("Video Not Found or some other errors : " + e.getMessage());
        }
    }

}
