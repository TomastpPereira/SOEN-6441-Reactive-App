package models;

public class Video {
    private String videoId;
    private String title;
    private String channelId;
    private String channelTitle;
    private String description;
    private String thumbnail;

    public Video(String videoId, String title, String channelId, String channelTitle, String description, String thumbnail) {
        this.videoId = videoId;
        this.title = title;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getDescription(){
        return description;
    }
    public String getVideoId() {return videoId;}
    public String getTitle() {return title;}
    public String getChannelId() {return channelId;}
    public String getChannelTitle() {return channelTitle;}
    public String getThumbnail() {return thumbnail;}
}

