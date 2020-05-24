package cn.dbdj1201.demo.section12;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author tyz1201
 * @datetime 2020-05-24 21:26
 **/
public class Client {

    public static void main(String[] args) throws IOException {
        //1.创建Scanner对象，从控制台获取数据
        Scanner sc = new Scanner(System.in);
        while (true) {
            //2.获取控制台用户输入的命令
            System.out.println("请向服务器发数据：");
            String content = sc.nextLine();

            //2.创建Socket对象，与服务端创建连接
            Socket socket = new Socket("localhost", 8080);
            //3.使用网络数出流，把用户输入的命令传输给服务端
            socket.getOutputStream().write(content.getBytes());
            //4.告诉服务端，不再写数据了
            socket.shutdownOutput();

            //5.判断用户的输入，如果是list，则证明用户想从服务端获取文件列表，如果不是list，则证明用户想从服务器端下载文件，而content就是文件名
            if ("list".equals(content)) {
                //5.1用户要获取服务端的文件列表，把数据打印到控制台
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } else {
                //5.2用户要从服务端下载文件，把读取到的文件内容写入到 files 文件夹指定的文件下
                InputStream in = socket.getInputStream();
                FileOutputStream out = new FileOutputStream("test\\files\\" + content);
                byte[] bys = new byte[1024];
                int len;
                while ((len = in.read(bys)) != -1) {
                    out.write(bys, 0, len);
                }
                out.close();
            }
            //6.每次交互完毕，则关闭socket对象
            socket.close();
        }
    }
}
