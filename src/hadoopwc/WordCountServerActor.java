package hadoopwc;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class WordCountServerActor extends UntypedActor {

  private ActorRef mapRouter;
  private ActorRef aggregateActor;

  public void onReceive(Object message) {
    if (message instanceof String) {
      if (((String) message).compareTo("DISPLAY_LIST") == 0) {
        System.out.println("Got Display Message");
        aggregateActor.tell(message, getSender());
      } else {
        mapRouter.tell(message,self());
      }
    }
  }

  public WordCountServerActor(ActorRef inAggregateActor, ActorRef inMapRouter) {

    mapRouter = inMapRouter;
    aggregateActor = inAggregateActor;
  }

}
