package com.example;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.Future;

import static akka.pattern.Patterns.ask;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;
import static scala.compat.java8.FutureConverters.toJava;


public class JavaPongActorTest {
    ActorSystem system = ActorSystem.create();
    ActorRef actorRef = system.actorOf(Props.create(JavaPongActor.class));

    @Test
    public void shouldReplyToPingWithPong() throws Exception {
        Future sFuture = ask(actorRef, "Ping", 9000);
        final CompletionStage<String> cs = toJava(sFuture);
        final CompletableFuture<String> jFuture =
            (CompletableFuture<String>) cs;
        //assert (jFuture.get(9000, TimeUnit.MILLISECONDS).equals("Pong"));

        jFuture.thenAccept(x -> assertTrue(x.equals("Pong")));

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        sleep(15000);
    }

    @Test(expected = ExecutionException.class)
    public void shouldReplyToUnknownMessageWithFailure() throws
                                                         Exception {
        Future sFuture = ask(actorRef, "unknown", 1000);
        final CompletionStage<String> cs = toJava(sFuture);
        final CompletableFuture<String> jFuture =
            (CompletableFuture<String>) cs;
        jFuture.get(1000, TimeUnit.MILLISECONDS);
    }
}
