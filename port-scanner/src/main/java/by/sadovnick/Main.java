package by.sadovnick;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int THREADS = 100;
    private static final int MIN_PORT_NUMBER = 0;
    private static final int MAX_PORT_NUMBER = 65535;
    private static final int TIMEOUT = 100;

    public static void main(String[] args) {
        scan("yandex.ru");
    }

    private static void scan(String host) {
        System.out.println("Scanning " + host);
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        for (int i = MIN_PORT_NUMBER; i < MAX_PORT_NUMBER; i++) {
            final int port = i;
            executorService.execute(() -> {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
                try (Socket socket = new Socket()) {
                    socket.connect(inetSocketAddress, TIMEOUT);
                    System.out.printf("Host: %s, port %d is opened\n", host, port);
                } catch (IOException e) {
//                System.err.println(e.getMessage());
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finish!");
    }
}
