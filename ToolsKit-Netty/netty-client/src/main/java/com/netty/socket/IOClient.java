package com.netty.socket;

import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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
public class IOClient implements Runnable {

    ExecutorService singleThreadExecutor;

    public IOClient(ExecutorService singleThreadExecutor) {
        this.singleThreadExecutor = singleThreadExecutor;
    }

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(2);
        new Thread(new IOClient(singleThreadExecutor)).start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            sendMsg(socket);
            readMsg(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMsg(Socket socket){
        Scanner scanner = new Scanner(System.in);
        singleThreadExecutor.execute(() -> {
            while (true) {
                System.out.println("请输入：");
                String msg = scanner.next();
                if (msg.equals("exit")) {
                    break;
                }
                OutputStream outputStream = null;
                try {
                    outputStream = socket.getOutputStream();
                    outputStream.write((new Date() + "  客户端说: " + msg).getBytes());
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

    public void readMsg(Socket socket){
        singleThreadExecutor.execute(() -> {
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
