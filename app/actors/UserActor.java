package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;


import models.*;
import java.util.HashMap;
import java.util.Map;

public class UserActor extends AbstractActor {

    private final Map<String, ActorRef> searchHistoryActors = new HashMap<>();
    private final ActorRef youtubeSupervisor;

    public UserActor(ActorRef youtubeSupervisor){
        this.youtubeSupervisor = youtubeSupervisor;
    }

    public static Props getProps(){
        return Props.create(UserActor.class);
    }

    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(Query.class, this::handleQuery)
                .match(Response.class, this::handleResponse)
                .build();
    }

    private void handleQuery(Query query) {
        System.out.println("UserActor - Received Query from userId: " + query.userId);

        // Forward the query to the YoutubeSupervisorActor
        youtubeSupervisor.tell(query, getSelf());
    }

    private void handleResponse(Response response) {
        System.out.println("UserActor - Received Response for userId: " + response.userId);

        // Find the appropriate SearchHistoryActor and send the response
        ActorRef searchHistoryActor = searchHistoryActors.get(response.userId);
        if (searchHistoryActor != null) {
            searchHistoryActor.tell(response, getSelf());
        } else {
            System.err.println("UserActor - No SearchHistoryActor found for userId: " + response.userId);
        }
    }

    // Method to create or retrieve a SearchHistoryActor
    private ActorRef getOrCreateSearchHistoryActor(String userId) {
        return searchHistoryActors.computeIfAbsent(userId, id -> {
            System.out.println("UserActor - Creating new SearchHistoryActor for userId: " + id);
            ActorRef actor = getContext().actorOf(Props.create(SearchHistoryActor.class), "searchHistoryActor-" + id);
            return actor;
        });
    }

    public static class Query {
        public final String userId; // Identifier for the user session
        public final String query; // Search query

        public Query(String userId, String query) {
            this.userId = userId;
            this.query = query;
        }
    }

    public static class Response {
        public final String userId; // Identifier for the user session
        public final Object data;   // The API response data

        public Response(String userId, Object data) {
            this.userId = userId;
            this.data = data;
        }
    }

}
