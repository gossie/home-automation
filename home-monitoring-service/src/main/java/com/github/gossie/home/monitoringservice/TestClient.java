package com.github.gossie.home.monitoringservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestClient {

    public static void main(String... args) throws Exception {

        Callable<String> rooms = () -> {

            try {
                System.out.println("send request to rooms");
                return getRequest("http://it0124013.eventim.ag:7081/rooms");
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        };

        Callable<String> powerOutlets = () -> {
            try {
                System.out.println("send request to power outlets");
                return getRequest("http://it0124013.eventim.ag:7082/power-outlets");
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Random random = new Random();
        while (true) {
            List<Future<String>> futures = new ArrayList<>(20);
            futures.addAll(callEndpoint(executor, random, rooms));
            futures.addAll(callEndpoint(executor, random, powerOutlets));
            futures.forEach(TestClient::printFuture);
            int millis = random.nextInt(2500);
            System.out.println("sleep " + millis + " ms");
            Thread.sleep(millis);
        }
    }

    private static void printFuture(Future<String> future) {
        try {
            System.out.println(future.get());
        } catch(InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Future<String>> callEndpoint(ExecutorService executor, Random random, Callable<String> callable) {
        int max = random.nextInt(10);
        List<Future<String>> futures = new ArrayList<>(max);
        System.out.println("call endpoint " + max + " times");
        for (int i = 0; i < max; i++) {
            futures.add(executor.submit(callable));
        }
        return futures;
    }

    private static String getRequest(String sUrl) throws IOException {
        URL url = new URL(sUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setUseCaches(false);
        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }
}
