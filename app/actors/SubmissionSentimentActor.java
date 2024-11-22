package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import models.SearchHistoryModel;
import models.SubmissionSentiment;
import models.Video;

import java.util.List;

public class SubmissionSentimentActor extends AbstractActor {

    private final SearchHistoryModel searchHistoryModel;

    public SubmissionSentimentActor(SearchHistoryModel searchHistoryModel){
        this.searchHistoryModel = searchHistoryModel;
    }

    public static Props props(SearchHistoryModel searchHistoryModel){
        return Props.create(SubmissionSentimentActor.class, () -> new SubmissionSentimentActor(searchHistoryModel));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, query -> {
                    List<Video> videos = searchHistoryModel.queryYoutube(query);
                    SubmissionSentiment sentimentAnalyzer = new SubmissionSentiment();
                    String sentiment = sentimentAnalyzer.determineSentiment((List<Video>) videos);
                    sender().tell(sentiment, self());
                })
                .build();
    }

}
