package actors;

import akka.actor.*;
import models.SearchHistoryModel;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;


public class YoutubeSupervisorActor extends AbstractActor {

    private final SearchHistoryModel searchHistoryModel;

    public YoutubeSupervisorActor(SearchHistoryModel searchHistoryModel){
        this.searchHistoryModel = searchHistoryModel;
    }

    public static Props props(SearchHistoryModel searchHistoryModel){
        return Props.create(YoutubeSupervisorActor.class, () -> new YoutubeSupervisorActor(searchHistoryModel));
    }

    @Override
    public SupervisorStrategy supervisorStrategy(){
        return new OneForOneStrategy(
                10, // Number of retries
                Duration.create(60, TimeUnit.MINUTES),
                throwable -> {
                    System.err.println("Child Actor Error: " + throwable.getMessage());
                    return SupervisorStrategy.restart(); // Restart the actor when error occurs
                }
        );
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                // Create a new child actor
                .match(Props.class, props -> {
                    ActorRef child = getContext().actorOf(props);
                    sender().tell(child, self());
                })
                // Perform a Youtube Query
                .match(String.class, query -> {
                    System.out.println("Youtube query: " + query);
                })
                .build();
    }
}
