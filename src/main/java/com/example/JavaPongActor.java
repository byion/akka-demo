package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;

import static java.lang.Thread.sleep;

public class JavaPongActor extends AbstractActor {
    public PartialFunction receive() {
        return ReceiveBuilder
            .matchEquals("Ping", s -> {
                sleep(7000);
                sender().tell("Pong", ActorRef.noSender());

            })
            .matchAny(x -> sender().tell(new Status.Failure(new Exception("unknown message")), self())).build();
    }
}
