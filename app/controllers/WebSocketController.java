package controllers;

import actors.UserActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import com.google.inject.Inject;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

public class WebSocketController extends Controller {

    @Inject
    private ActorSystem actorSystem;
    @Inject
    private Materializer materializer;

    @Inject
    public WebSocketController(ActorSystem actorSystem){
      actorSystem.actorOf(UserActor.getProps(), "mainActor");
    }

    public Result index(){
        return ok(views.html.index1.render());
    }

    public WebSocket ws(){
            return WebSocket.Json.accept(request ->
                    ActorFlow.actorRef(out -> Props.create(UserActor.class, out), actorSystem, materializer)
        );
    }

}
