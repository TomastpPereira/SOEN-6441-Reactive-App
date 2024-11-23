package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import models.SearchHistoryModel;
import models.YouTube;
import models.Video;
import models.SearchResult;

import java.util.LinkedList;
import java.util.List;

public class SearchHistoryActor extends AbstractActor {
    private final SearchHistoryModel searchHistoryModel;
    private final ActorRef userActor;

    // Constructor to initialize SearchHistoryModel and UserActor reference
    public SearchHistoryActor(YouTube youtubeApiClient, ActorRef userActor) {
        this.searchHistoryModel = new SearchHistoryModel(youtubeApiClient);
        this.userActor = userActor;
    }

    // Factory method for Props
    public static Props props(YouTube youtubeApiClient, ActorRef userActor) {
        return Props.create(SearchHistoryActor.class, () -> new SearchHistoryActor(youtubeApiClient, userActor));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(QueryMessage.class, this::handleQuery)
                .match(ResponseMessage.class, this::handleResponse)
                .match(FetchHistoryMessage.class, this::handleFetchHistory)
                .build();
    }

    // Handle queries from the user
    private void handleQuery(QueryMessage queryMessage) {
        System.out.println("SearchHistoryActor - Received query: " + queryMessage.query);

        // Forward the query to the UserActor
        userActor.tell(new UserActor.Query(self().path().name(), queryMessage.query), getSelf());
    }

    // Handle responses from the UserActor
    private void handleResponse(ResponseMessage responseMessage) {
        System.out.println("SearchHistoryActor - Received response with " + responseMessage.videos.size() + " videos");

        // Update the search history
        searchHistoryModel.addSearchResult(responseMessage.videos.get(0).getTitle(), responseMessage.videos);

        // Optionally send the updated history back to the UI
        sender().tell(new SearchHistoryResponse(searchHistoryModel.getSearchHistory()), getSelf());
    }

    // Handle fetch history requests
    private void handleFetchHistory(FetchHistoryMessage fetchHistoryMessage) {
        System.out.println("SearchHistoryActor - Fetching search history");

        // Send the current search history back to the sender
        sender().tell(new SearchHistoryResponse(searchHistoryModel.getSearchHistory()), getSelf());
    }

    public static class QueryMessage {
        public final String query;

        public QueryMessage(String query) {
            this.query = query;
        }
    }

    public static class ResponseMessage {
        public final List<Video> videos;

        public ResponseMessage(List<Video> videos) {
            this.videos = videos;
        }
    }

    public static class FetchHistoryMessage {}

    public static class SearchHistoryResponse {
        public final LinkedList<SearchResult> searchHistory;

        public SearchHistoryResponse(LinkedList<SearchResult> searchHistory) {
            this.searchHistory = searchHistory;
        }
    }

}

