package controllers;

import models.SearchHistoryModel;
import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static models.WordStats.generateWordStats;

public class SearchController extends Controller {
    private final SearchHistoryModel shModel;

    @Inject
    public SearchController(SearchHistoryModel shModel) {
        this.shModel = shModel;
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
}
