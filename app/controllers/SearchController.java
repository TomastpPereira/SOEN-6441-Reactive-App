package controllers;

import models.Channel;
import models.Video;
import models.SearchHistoryModel;
import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static models.WordStats.generateWordStats;


public class SearchController extends Controller {
    private final SearchHistoryModel shModel;

    @Inject
    public SearchController(SearchHistoryModel shModel) {
        this.shModel = shModel;
    }

    public Result index() {
        return ok(views.html.index.render(new LinkedList<>()));
    }

    public CompletionStage<Result> searchVideos(String query) {
        // Asynchronously handle the query and response
        return CompletableFuture.supplyAsync(() -> {
            // Perform query and store operation
            shModel.queryAndStore(query);
            // Fetch the updated search history
            return shModel.getSearchHistory();
        }).thenApply(searchHistory -> ok(views.html.index.render(searchHistory)));
    }
    public CompletionStage<Result> MoreStats(String query) {
        // Asynchronously handle
        return CompletableFuture.supplyAsync(() -> {

            return generateWordStats(shModel.queryYoutube(query,50));
        }).thenApply(Morestats -> ok(views.html.wordstats.render(query,Morestats)));
    }

    public CompletionStage<Result> showChannelProfile(String channelId) {
        // Handle of the asynchronous part
        return CompletableFuture.supplyAsync(() -> {
            Channel channelDetails = shModel.getChannelDetails(channelId);
            List<Video> latestVideos = shModel.getChannelVideos(channelId, 10);
            return ok(views.html.channel.render(channelDetails, latestVideos));
        });
    }
}
