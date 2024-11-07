package models;

public class Channel {

    private String id;
    private String title;
    private String description;
    private String publishedAt;
    private String country;
    private String customUrl;
    private String thumbnailUrl;
    private int subscriberCount;
    private boolean hiddenSubscriberCount;
    private int viewCount;
    private int videoCount;

    public Channel(String id, String title, String description, String publishedAt, String country, String customUrl, String thumbnailUrl, int subscriberCount, boolean hiddenSubscriberCount, int viewCount, int videoCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.country = country;
        this.customUrl = customUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.subscriberCount = subscriberCount;
        this.hiddenSubscriberCount = hiddenSubscriberCount;
        this.viewCount = viewCount;
        this.videoCount = videoCount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getCountry() {
        return country;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public boolean isHiddenSubscriberCount() {
        return hiddenSubscriberCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getVideoCount() {
        return videoCount;
    }
}
