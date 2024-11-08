package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Sam Collin
 * @author Tomas Pereira
 */
class SearchHistoryModelTest {

    private static final int MAX_SEARCHES = 10;
    @InjectMocks
    private SearchHistoryModel searchHistoryModel;

    @Mock
    private YouTube mockYoutube;
    @Mock
    private SubmissionSentiment sentimentAnalyzer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test case for queryYoutube when the API has returned an Empty JSON response.
     * This leads to the results being an empty list.
     */
    @Test
    public void testQueryYoutubeNull() throws Exception {

        // Return null from Mock API
        JsonNode emptyResponse = objectMapper.readTree("{}");
        when(searchHistoryModel.queryYoutube(anyString(),anyInt())).thenReturn(emptyResponse);

        List<Video> result = searchHistoryModel.queryYoutube("test");

        assertEquals(0, result.size(), "List should be empty when the JSON response is empty.");
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test case for queryYoutube with a successful API request.
     * The JSON with 2 entries leads to a List of size 2, with all the video details matching.
     */
    @Test
    public void testQueryYoutubeSuccess() throws Exception {
        // Fake API Response simulating a success
        String fakeResponse = """
        {
            "items": [
                {
                    "id": { "videoId": "testVideoId1" },
                    "snippet": {
                        "title": "Test Video Title 1",
                        "channelId": "testChannelId1",
                        "channelTitle": "Test Channel 1",
                        "description": "Test Description 1",
                        "thumbnails": {
                            "default": { "url": "https://fakeurl1.com/thumbnail.jpg" }
                        }
                    }
                },
                {
                    "id": { "videoId": "testVideoId2" },
                    "snippet": {
                        "title": "Test Video Title 2",
                        "channelId": "testChannelId2",
                        "channelTitle": "Test Channel 2",
                        "description": "Test Description 2",
                        "thumbnails": {
                            "default": { "url": "https://fakeurl2.com/thumbnail.jpg" }
                        }
                    }
                }
            ]
        }
        """;

        // Fake the API call
        JsonNode data = objectMapper.readTree(fakeResponse);
        when(mockYoutube.searchVideos(anyString(), anyInt())).thenReturn(data);

        List<Video> result = searchHistoryModel.queryYoutube("test");

        // Check All is as Expected
        assertEquals(2, result.size(), "Expected 2 videos in the result list.");

        assertEquals("testVideoId1", result.getFirst().getVideoId());
        assertEquals("Test Video Title 1", result.getFirst().getTitle());
        assertEquals("testChannelId1", result.getFirst().getChannelId());
        assertEquals("Test Channel 1", result.getFirst().getChannelTitle());
        assertEquals("Test Description 1", result.getFirst().getDescription());
        assertEquals("https://fakeurl1.com/thumbnail.jpg", result.getFirst().getThumbnail());

        assertEquals("testVideoId2", result.get(1).getVideoId());
        assertEquals("Test Video Title 2", result.get(1).getTitle());
        assertEquals("testChannelId2", result.get(1).getChannelId());
        assertEquals("Test Channel 2", result.get(1).getChannelTitle());
        assertEquals("Test Description 2", result.get(1).getDescription());
        assertEquals("https://fakeurl2.com/thumbnail.jpg", result.get(1).getThumbnail());
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test for the case when the JSON is missing some field. Leads to that specific video being skipped.
     */
    @Test
    public void queryYoutubeMissingField() throws Exception {
        String fakeResponse = """
        {
            "items": [
                {
                    "id": { "videoId": "testVideoId1" },
                    "snippet": {
                        "title": "Test Video Title 1",
                        "channelId": "testChannelId1",
                        "channelTitle": "Test Channel 1",
                        "thumbnails": {
                            "default": { "url": "https://fakeurl1.com/thumbnail.jpg" }
                        }
                    }
                },
                {
                    "id": { "videoId": "testVideoId2" },
                    "snippet": {
                        "title": "Test Video Title 2",
                        "channelId": "testChannelId2",
                        "channelTitle": "Test Channel 2",
                        "description": "Test Description 2",
                        "thumbnails": {
                            "default": { "url": "https://fakeurl2.com/thumbnail.jpg" }
                        }
                    }
                }
            ]
        }
        """;

        // Fake the API call
        JsonNode data = objectMapper.readTree(fakeResponse);
        when(mockYoutube.searchVideos(anyString(), anyInt())).thenReturn(data);

        List<Video> result = searchHistoryModel.queryYoutube("test");

        assertEquals(1, result.size(), "A missing field in the Video JSON, should result in the video being skipped");
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test case for when the query leads to an IO Exception (Network Issue)
     */
    @Test
    public void queryYoutubeIOException() throws Exception{

        when(mockYoutube.searchVideos(anyString(), anyInt())).thenThrow(new IOException("Network error"));

        List<Video> result = searchHistoryModel.queryYoutube("test query");

        assertEquals(0, result.size(), "List should be empty when an IOException occurs.");
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test case for when the query leads to a general Exception
     */
    @Test
    public void queryYoutubeException() throws Exception{

        when(mockYoutube.searchVideos(anyString(), anyInt())).thenThrow(new Exception("Error"));

        List<Video> result = searchHistoryModel.queryYoutube("test query");

        assertEquals(0, result.size(), "List should be empty when an Exception occurs.");
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test case for when the query (overloaded with Int param) leads to an IO Exception (Network Issue)
     */
    @Test
    public void queryYoutubeIOExceptionWithInt() throws Exception{

        when(mockYoutube.searchVideos(anyString(), anyInt())).thenThrow(new IOException("Network error"));

        JsonNode result = searchHistoryModel.queryYoutube("test query", 5);

        assertNull(result, "Result should be Null when encountering an exception");
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test case for when the query (overloaded with Int param) leads to a Runtime Exception (API Issue)
     */
    @Test
    public void queryYoutubeRuntimeExceptionWithInt() throws Exception{

        when(mockYoutube.searchVideos(anyString(), anyInt())).thenThrow(new RuntimeException("Runtime Error"));

        JsonNode result = searchHistoryModel.queryYoutube("test query", 5);

        assertNull(result, "Result should be Null when encountering an exception");
    }

    /**
     * @author Tomas Pereira
     * @throws Exception
     *
     * Test case for when the query (overloaded with Int param) leads to a general Exception
     */
    @Test
    public void queryYoutubeExceptionWithInt() throws Exception{

        when(mockYoutube.searchVideos(anyString(), anyInt())).thenThrow(new Exception("Error"));

        JsonNode result = searchHistoryModel.queryYoutube("test query", 5);

        assertNull(result, "Result should be Null when encountering an exception");
    }

    /**
     * @author Tomas Pereira
     *
     * Test for the addSearchResult method when the history list is already at full capacity.
     * After the operation, Query 0 will be gone in favour of Query 10
     */
    @Test
    public void testAddSearchResultFullList(){

        // First populate with Max examples to fill the list
        when(sentimentAnalyzer.determineSentiment(anyList())).thenReturn("neutral");
        for (int i = 0; i < MAX_SEARCHES; i++) {
            List<Video> videos = new ArrayList<>();  // empty video list
            searchHistoryModel.addSearchResult("query" + i, videos);
        }

        // Initital checks
        assertEquals(MAX_SEARCHES, searchHistoryModel.getSearchHistory().size(), "List should be full before the operation.");
        assertEquals("query0", searchHistoryModel.getSearchHistory().getLast().query, "Tail of the list should be query0");

        // New Video to Add in
        List<Video> vidsToAdd = new ArrayList<>();
        searchHistoryModel.addSearchResult("test", vidsToAdd);

        // Check states
        List<SearchResult> historyToCheck = searchHistoryModel.getSearchHistory();
        assertEquals(MAX_SEARCHES, historyToCheck.size(), "Size should still be Max after the operation");
        assertEquals("test", historyToCheck.getFirst().query, "New Video queried by 'test' should now be at the head");
        assertEquals("query1", historyToCheck.getLast().query, "The new tail should be query1 after query0 is removed");

    }

    @Test
    void testGetChannelDetails() throws Exception {

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


}
