package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class SearchHistoryModelTest {

    private SearchHistoryModel searchHistoryModel;
    private YouTube mockYoutube;

    @BeforeEach
    public void init() {
        mockYoutube = mock(YouTube.class);

        // We create a new SearchHistoryModel with the fake Youtube
        searchHistoryModel = new SearchHistoryModel(mockYoutube);
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
