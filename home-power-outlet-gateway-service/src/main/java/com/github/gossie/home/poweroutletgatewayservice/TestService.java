package com.github.gossie.home.poweroutletgatewayservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TestService {

    private ExecutorService executorService;


    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(1);
    }


    public String test(boolean fail) {
        //            Callable<String> callable = () -> {
        //                throw new RuntimeException();
        //            };
        return getString(fail);
    }


    @HystrixCommand(fallbackMethod = "defaultString")
    public String getString(boolean fail) {
        return internalGetString(fail);
    }

    private String internalGetString(boolean fail) {
        Future<String> future = executorService.submit(() -> callable(fail));
        try {
            return future.get();
        } catch(InterruptedException | ExecutionException e) {
            String s = "";
            if (fail) {
                s = null;
            }
            System.out.println(s.toLowerCase());
        }
        return "should not get here";
    }

    private String callable(boolean fail) {
        if (fail) {
            throw new RuntimeException("failed");
        }
        return "worked";
    }

    private String defaultString(boolean fail) {
        return "default";
    }
}
