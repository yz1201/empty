package cn.dbdj1201.demo.section12;

import java.io.*;
import java.net.Socket;

/**
 * @author tyz1201
 * @datetime 2020-05-22 21:59
 **/
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        //创建客户端的Socket对象(Socket)
        Socket s = new Socket("192.168.0.109", 10000);

        //获取输出流，写数据
        OutputStream os = s.getOutputStream();

        os.write("hello,tcp,我来了".getBytes());

//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        //接收服务器反馈
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys, 0, len);
        System.out.println("客户端：" + data);

        //释放资源
//        is.close();
//        os.close();
        s.close();
    }
}
