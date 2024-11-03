package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class YouTube {
    private static final String API_KEY="AIzaSyBTdLng0J0bxQOYFhKhMrI23guTCVRI1xQ";
    private static final String API_URL="https://www.googleapis.com/youtube/v3/";
    public JsonNode searchVideos(String query, int maxResults) throws Exception {
        String url = API_URL + "search?part=snippet&q=" + query + "&maxResults=" + maxResults + "&key=" + API_KEY;
        return makeRequest(url);
    }
    // Get video details by video ID
    public JsonNode getVideoDetails(String videoId) throws Exception {
        String url = API_URL + "videos?part=snippet,contentDetails,statistics&id=" + videoId + "&key=" + API_KEY;
        return makeRequest(url);
    }
    // Get channel details by channel ID
    public JsonNode getChannelDetails(String channelId) throws Exception {
        String url = API_URL + "channels?part=snippet,statistics&id=" + channelId + "&key=" + API_KEY;
        return makeRequest(url);
    }
    private JsonNode makeRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set up the connection properties
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Check the response code
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // Success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            // Read the response line by line
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the response JSON
            return Json.parse(response.toString());
        } else {
            throw new RuntimeException("Request failed with response code: " + responseCode);
        }
    }
}
