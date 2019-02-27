package edu.jennifer.stress.lock;

import edu.jennifer.stress.factory.UserFactory;
import edu.jennifer.stress.model.User;
import edu.jennifer.stress.simula.HttpClient;

import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Khalid
 * @Created 2/18/19 4:53 PM.
 */
public class LockMain extends TimerTask {

    private static final int THREADS = 30;
    private String baseUrl;

    public LockMain(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        User fake = UserFactory.createUser();
        HttpClient client = new HttpClient(fake.getLocation());

        for(int i = 0; i < THREADS; i++) {
            String url = String.format("%s/visitors", this.baseUrl);
            Runner worker = new Runner(url, client);
            executor.execute(worker);
        }

        executor.shutdown();
        while(!executor.isTerminated()) {}
    }


    public static class Runner implements Runnable {
        private String url;
        private HttpClient client;

        public Runner(String url, HttpClient client) {
            this.url = url;
            this.client = client;
        }

        @Override
        public void run() {
            this.client.doGet(url);
        }
    }
}
