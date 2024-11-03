package controllers;
import models.SearchHistoryModel;
import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;


public class SearchController extends Controller {
    private final SearchHistoryModel shModel;

    @Inject
    public SearchController(SearchHistoryModel shModel){
        this.shModel = shModel;
    }

    public Result searchVideos(String query) {
        shModel.queryAndStore(query);
        return ok(views.html.index.render(shModel.getSearchHistory()));
    }

}
