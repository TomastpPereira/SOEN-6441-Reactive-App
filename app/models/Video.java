package models;

public class Video {
    public String videoId;
    public String title;
    public String channelId;
    public String channelTitle;
    public String description;
    public String thumbnail;

    public Video(String videoId, String title, String channelId, String channelTitle, String description, String thumbnail) {
        this.videoId = videoId;
        this.title = title;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}

