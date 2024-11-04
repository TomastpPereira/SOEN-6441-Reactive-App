package models;

import java.util.List;

public class SearchResult {
    public String query;
    public List<Video> videos;
    public String sentiment;
    public SearchResult(String query, List<Video> videos, String sentiment) {
        this.query = query;
        this.videos = videos;
        this.sentiment = sentiment;
    }

}