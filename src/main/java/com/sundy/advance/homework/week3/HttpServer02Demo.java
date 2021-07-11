package com.sundy.advance.homework.week3;

import com.sundy.advance.homework.week2.HttpServerDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shawn
 * @date 2021/7/11
 */
public class HttpServer02Demo {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(300));
        try {
            final ServerSocket server = new ServerSocket(8802);
            while (true) {
                try {
                    final Socket socket = server.accept();
                    executorService.execute(() -> HttpServerDemo.service(socket, "hello, test"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
