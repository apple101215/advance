package com.sundy.advance.homework.week2.作业6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author sundy
 * @date 2021/7/4
 */
public class HttpServerDemo {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(300));
        try {
            final ServerSocket server = new ServerSocket(8801);
            while (true) {
                try {
                    final Socket socket = server.accept();
                    executorService.execute(() -> service(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void service(Socket socket) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type:text/html;charset=utf-8");
            String body = "hello, hi";
            pw.println("Content-Length:" + body.getBytes().length);
            pw.println();
            pw.write(body);
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
