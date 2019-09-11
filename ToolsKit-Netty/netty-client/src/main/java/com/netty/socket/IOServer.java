package com.netty.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/9/11 10:11
 */
public class IOServer implements Runnable {
    ExecutorService fixedThreadPool;

    public IOServer(ExecutorService fixedThreadPool) {
        this.fixedThreadPool = fixedThreadPool;
    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        new Thread(new IOServer(fixedThreadPool)).start();
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("等待连接.......");
            while (true){
                Socket socket = serverSocket.accept();
                readMsg(socket);
                sendMsg(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Socket socket) throws IOException {
        Scanner scanner = new Scanner(System.in);
        fixedThreadPool.execute(() -> {
            while (true) {
                System.out.println("请输入：");
                String msg = scanner.next();
                if (msg.equals("exit")) {
                    break;
                }
                OutputStream outputStream = null;
                try {
                    outputStream = socket.getOutputStream();
                    outputStream.write((new Date() + "  服务端说: " + msg).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                finally {
//                    try {
//                        if (outputStream != null) {
//                            outputStream.close();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });
    }

    public void readMsg(Socket socket) throws IOException {
        fixedThreadPool.execute(() -> {
            System.out.println("连接成功");
            InputStream inputStream = null;
            try {
                int len;
                byte[] data = new byte[1024];
                inputStream = socket.getInputStream();
                // (3) 按字节流方式读取数据
                while ((len = inputStream.read(data)) != -1) {
                    System.out.println(new String(data, 0, len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            finally {
//                try {
//                    if (inputStream != null) {
//                        inputStream.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        });
    }
}
