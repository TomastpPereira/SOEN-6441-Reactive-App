package controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.jupiter.api.Assertions.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * @author Tomas Pereira
 * Integration testing for the search controller.
 * Uses the Play framework's testing branch to make fake HTTP requests.
 */
public class SearchControllerIntegrationTest extends WithApplication {

    /**
     * @author Tomas Pereira
     *
     * Starts the application before each test.
     */
    @BeforeEach
    public void init() {
        app = new GuiceApplicationBuilder().build();
    }

    /**
     * @author Tomas Pereira
     * Test for the landing page which includes only the Welcome message and search bar.
     * By checking for "Welcome to YT Lytics", we know that we are one of the pages.
     * By then checking that "Search Terms:" is not present, we know that it is the landing page, since no search is made yet.
     */
    @Test
    public void testIndex(){
        Http.RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        assertTrue(contentAsString(result).contains("Welcome to YT Lytics"));
        assertFalse(contentAsString(result).contains("Search Terms:"));
    }

    /**
     * @author Tomas Pereira
     * Test for the result of a search query, which must show the search terms used.
     * By then checking that "Search Terms:" is present, we know that it is the result of a search.
     */
    @Test
    public void testSearchVideos() {
        Http.RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/search/searchVideos?query=test");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        // "Search Terms:" will not be present unless a search has been made
        assertTrue(contentAsString(result).contains("Search Terms:"));
    }

    /**
     * @author Tomas Pereira
     * Test for the More Stats page.
     * By then checking that "Word Statistics for" is present, we know that we are on the correct page.
     */
    @Test
    public void testMoreStats(){
        Http.RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/search/MoreStats?query=test");

        Result result = route(app, request);

        assertEquals(OK, result.status());
        // "Word Statistics for" will not be present unless we are looking at More Stats
        assertTrue(contentAsString(result).contains("Word Statistics for"));
    }

    /**
     * @author Tomas Pereira
     * Stops the application after each test.
     */
    @AfterEach
    public void tearDown(){
        app.asScala().stop();
    }


}
