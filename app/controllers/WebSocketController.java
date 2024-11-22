package controllers;

import actors.UserActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import com.google.inject.Inject;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.WebSocket;

public class WebSocketController extends Controller {

    @Inject
    private final ActorSystem actorSystem;
    @Inject final Materializer materializer;

    @Inject
    public WebSocketController(ActorSystem actorSystem, Materializer materializer){
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }


    public WebSocket ws(){
            return WebSocket.Text.accept(request ->
                    ActorFlow.actorRef(out -> Props.create(UserActor.class, out), actorSystem, materializer)
        );
    }

}
