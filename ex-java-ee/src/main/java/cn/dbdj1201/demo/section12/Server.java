package cn.dbdj1201.demo.section12;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tyz1201
 * @datetime 2020-05-24 21:37
 **/
public class Server {

    public static void main(String[] args) throws IOException {
        //1.创建ServerSocket对象，监听8080端口
        ServerSocket serverSocket = new ServerSocket(8080);
        //2.通过循环，让服务端可以一直执行
        while (true) {
            //3.调用accept方法，监听客户端连接
            Socket socket = serverSocket.accept();
            //4.如果有客户端连接，则开启子线程，与该客户端通信
            new Thread(() -> {
                try {
                    //4.1使用网络输入流，包装一个字符缓冲读取流
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //4.2读取客户端发送过来的数据
                    String content = br.readLine();
                    //4.3 判断客户端发送的数据是不是list，如果是list，则证明客户端要获取所有文件列表，如果不是，则证明客户端要下载文件
                    if ("list".equals(content)) {
                        //4.4客户端要获取服务端的所有文件列表，把download文件夹下的所有文件名称响应给客户端
                        File download_dir = new File("test/download");
                        File[] files = download_dir.listFiles();
                        OutputStream out = socket.getOutputStream();
                        for (File file : files) {
                            if (file.isFile())
                                out.write((file.getName() + "\r\n").getBytes());
                        }
                        //告诉客户端，数据写完了，不再写数据了
                        socket.shutdownOutput();
                    } else {
                        //4.5客户端要下载文件，content就是文件的名字
                        OutputStream out = socket.getOutputStream();
                        FileInputStream in = new FileInputStream("test/download/" + content);
                        byte[] bys = new byte[1024];
                        int len;
                        while ((len = in.read(bys)) != -1) {
                            out.write(bys, 0, len);
                        }
                        //告诉客户端，不再写数据了
                        socket.shutdownOutput();
                        in.close();
                    }
                    //4.6每一次交互完毕，都关闭socket
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
