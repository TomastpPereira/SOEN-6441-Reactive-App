package Models;

public class Video {
    public String videoId;
    public String title;
    public String channelId;
    public String channelTitle;

    public Video(String videoId, String title, String channelId, String channelTitle) {
        this.videoId = videoId;
        this.title = title;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
    }
}

