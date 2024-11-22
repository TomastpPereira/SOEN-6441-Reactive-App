package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import models.*;

import java.util.LinkedList;
import java.util.List;

public class UserActor extends AbstractActor {

    // Ensures 1 search history per session
    private final SearchHistoryModel searchHistoryModel;

    public UserActor(ActorRef supervisor){
        this.searchHistoryModel = new SearchHistoryModel(new YouTube());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                // Sends the searchhistory to the client
                .match(String.class, query ->{
                    // Query and Store the Results
                    searchHistoryModel.queryAndStore(query);
                    // Send out the current search history
                    LinkedList<SearchResult> searchHistory = searchHistoryModel.getSearchHistory();
                    sender().tell(searchHistory, self());
                })
                .build();
    }


}
