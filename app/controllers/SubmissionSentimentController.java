package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.submissionSentiment;

public class SubmissionSentimentController extends Controller {

    public Result showSentimentPage() {
        return ok(submissionSentiment.render());
    }
}