package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class SearchHistoryModelTest {

    private SearchHistoryModel searchHistoryModel;
    private YouTube mockYoutube;

    /**
     * @author Sam Collin
     * Defines the setup before each test. It creates the mock of the YouTube class and injects it in a new SearchHistoryModel instance.
     */
    @BeforeEach
    public void init() {
        mockYoutube = mock(YouTube.class);

        // We create a new SearchHistoryModel with the fake YouTube
        searchHistoryModel = new SearchHistoryModel(mockYoutube);
    }

    /**
     * @throws Exception
     * @author Sam Collin
     * This tests simulate a successful call to the api to retrieve specific information about a channel.
     * It creates its own fake JSON file and then pass it when the Youtube class is supposed to be called.
     * After that, the method getChannelDetails is just tested in the case everything works fine and we verify that all the information have been retrieved.
     */
    @Test
    public void testGetChannelDetails() throws Exception {

        String fakeChannelId = "0000000";

        String fakeChannelResponseFromAPI = """
                    {
                      "items": [
                        {
                          "id": "0000000",
                          "snippet": {
                            "title": "Fake Channel Title",
                            "description": "This description is a fake one.",
                            "publishedAt": "2024-11-08T12:00:00Z",
                            "country": "CA",
                            "thumbnails": {
                              "default": {
                                "url": "https://fakeurl.com/thumbnail.jpg"
                              }
                            }
                          },
                          "statistics": {
                            "subscriberCount": "999",
                            "hiddenSubscriberCount": false,
                            "viewCount": "14000",
                            "videoCount": "47"
                          }
                        }
                      ]
                    }
                """;

        // This step allows to create a json node object hich is what the API usually returns.
        JsonNode fakeJsonNode = new ObjectMapper().readTree(fakeChannelResponseFromAPI);

        // This is where we trick our program into thinking that the API returned this JSON node when it was called with the ID we've just created.
        when(mockYoutube.getChannelDetails(fakeChannelId)).thenReturn(fakeJsonNode);

        // We then make our call
        Channel result = searchHistoryModel.getChannelDetails(fakeChannelId);

        assertNotNull(result);
        assertEquals("0000000", result.getId());
        assertEquals("Fake Channel Title", result.getTitle());
        assertEquals("This description is a fake one.", result.getDescription());
        assertEquals("2024-11-08T12:00:00Z", result.getPublishedAt());
        assertEquals("CA", result.getCountry());
        assertEquals("https://fakeurl.com/thumbnail.jpg", result.getThumbnailUrl());
        assertEquals(999, result.getSubscriberCount());
        assertFalse(result.getHiddenSubscriberCount());
        assertEquals(14000, result.getViewCount());
        assertEquals(47, result.getVideoCount());

    }

    /**
     * @throws Exception
     * @author Sam Collin
     * This one is based on the same architecture than the prevous one but simulates the case where the API encounters an error. The function returns null then.
     */
    @Test
    public void testGetChannelDetailsFailed() throws Exception {

        String fakeChannelId = "0000000";

        // This is where we trick our program into thinking that the API returned and error bt returning a runtime exception
        when(mockYoutube.getChannelDetails(fakeChannelId)).thenThrow(new RuntimeException());


        // We then make our call
        Channel result = searchHistoryModel.getChannelDetails(fakeChannelId);

        // This time we want to make sure the result is null, because an exception occurs
        assertNull(result);
    }


    /**
     * @throws Exception
     * @author Sam Collin
     * This tests simulates a success when retrieving a list of videos from a given channel.
     */
    @Test
    public void testGetChannelVideos() throws Exception {

        String testChannelId = "0000000";
        int testMaxResults = 3;


        String fakeVideosListReceivedFromApi = """
                    {
                      "items": [
                        {
                          "id": {
                            "videoId": "01"
                          },
                          "snippet": {
                            "title": "Monday",
                            "channelId": "12121212",
                            "channelTitle": "Fake Channel",
                            "description": "Video 1",
                            "thumbnails": {
                              "default": {
                                "url": "https://fakeurl.com/vidMonday.jpg"
                              }
                            }
                          }
                        },
                        {
                          "id": {
                            "videoId": "02"
                          },
                          "snippet": {
                            "title": "Tuesday",
                            "channelId": "12121212",
                            "channelTitle": "Fake Channel",
                            "description": "Video 2",
                            "thumbnails": {
                              "default": {
                                "url": "https://fakeurl.com/vidTuesday.jpg"
                              }
                            }
                          }
                        },
                        {
                          "id": {
                            "videoId": "03"
                          },
                          "snippet": {
                            "title": "Wednesday",
                            "channelId": "12121212",
                            "channelTitle": "Fake Channel",
                            "description": "Video 3",
                            "thumbnails": {
                              "default": {
                                "url": "https://fakeurl.com/vidWednesday.jpg"
                              }
                            }
                          }
                        }
                      ]
                    }
                """;

        // Transform our string into JSON Node
        JsonNode fakeJsonNode = new ObjectMapper().readTree(fakeVideosListReceivedFromApi);
        // Returns it instead of real API call
        when(mockYoutube.getVideosByChannelId(testChannelId, testMaxResults)).thenReturn(fakeJsonNode);
        // Call the tested method
        List<Video> result = searchHistoryModel.getChannelVideos(testChannelId, testMaxResults);

        assertNotNull(result);
        assertEquals(3, result.size());

        // Validate the details of the third video
        Video video = result.get(2);
        assertEquals("03", video.getVideoId());
        assertEquals("Wednesday", video.getTitle());
        assertEquals("12121212", video.getChannelId());
        assertEquals("Fake Channel", video.getChannelTitle());
        assertEquals("Video 3", video.getDescription());
        assertEquals("https://fakeurl.com/vidWednesday.jpg", video.getThumbnail());
    }


    /**
     * @author Sam Collin
     * This tests simulates a success when retrieving an EMPTY list of videos from a given channel.
     */
    @Test
    public void testGetChannelVideosWithEmptyResponse() throws Exception {

        String testChannelId = "0000000";
        int testMaxResults = 3;


        String fakeEmptyVideosListReceivedFromApi = """
                    {
                      "items": []
                    }
                """;

        // Transform our string into JSON Node
        JsonNode fakeJsonNode = new ObjectMapper().readTree(fakeEmptyVideosListReceivedFromApi);
        // Returns it instead of real API call
        when(mockYoutube.getVideosByChannelId(testChannelId, testMaxResults)).thenReturn(fakeJsonNode);
        // Call the tested method
        List<Video> result = searchHistoryModel.getChannelVideos(testChannelId, testMaxResults);

        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    /**
     * @author Sam Collin
     * This tests simulates a failure caused by a RuntimeException when trying to retrieve a list of videos from a given channel.
     * It simulates an error with the API.
     */
    @Test
    public void testGetChannelVideosWithExceptionErrorApiResponse() throws Exception {
        // Fake channel ID and max results
        String fakeChannelId = "UC123456789";
        int maxResults = 3;

        // Simulate an exception being thrown by the YouTube API
        when(mockYoutube.getVideosByChannelId(fakeChannelId, maxResults)).thenThrow(new RuntimeException());

        // Call the method under test
        List<Video> result = searchHistoryModel.getChannelVideos(fakeChannelId, maxResults);

        // Assertions to validate the result
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    /**
     * @author Sam Collin
     * This tests simulates a failure caused by a IOException when trying to retrieve a list of videos from a given channel.
     * It simulates an error with the API.
     */
    @Test
    public void testGetChannelVideosWithIOException() throws Exception {
        // Fake channel ID and max results
        String fakeChannelId = "UC123456789";
        int maxResults = 3;

        // Simulate an exception being thrown by the YouTube API
        when(mockYoutube.getVideosByChannelId(fakeChannelId, maxResults)).thenThrow(new IOException());

        // Call the method under test
        List<Video> result = searchHistoryModel.getChannelVideos(fakeChannelId, maxResults);

        // Assertions to validate the result
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    /**
     * @author Sam Collin
     * This tests simulates a failure caused by a IOException when trying to retrieve a list of videos from a given channel.
     * It simulates an error with the API.
     */
    @Test
    public void testGetChannelVideosWithException() throws Exception {
        // Fake channel ID and max results
        String fakeChannelId = "UC123456789";
        int maxResults = 3;

        // Simulate an exception being thrown by the YouTube API
        when(mockYoutube.getVideosByChannelId(fakeChannelId, maxResults)).thenThrow(new Exception());

        // Call the method under test
        List<Video> result = searchHistoryModel.getChannelVideos(fakeChannelId, maxResults);

        // Assertions to validate the result
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
