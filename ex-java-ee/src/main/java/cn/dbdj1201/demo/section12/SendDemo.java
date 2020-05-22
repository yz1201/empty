package cn.dbdj1201.demo.section12;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author tyz1201
 * @datetime 2020-05-22 21:17
 **/
public class SendDemo {
    public static void main(String[] args) throws IOException {
        //创建发送端的Socket对象(DatagramSocket)
        // DatagramSocket() 构造数据报套接字并将其绑定到本地主机上的任何可用端口
        DatagramSocket ds = new DatagramSocket();

        //创建数据，并把数据打包
        //DatagramPacket(byte[] buf, int length, InetAddress address, int port)
        //构造一个数据包，发送长度为 length的数据包到指定主机上的指定端口号。
//        byte[] bys = "hello,udp,我来了".getBytes();
        byte[] bys;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("886"))
                break;
            bys = line.getBytes();
            DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getLocalHost(), 12345);

            //调用DatagramSocket对象的方法发送数据
            //void send(DatagramPacket p) 从此套接字发送数据报包
            System.out.println("send : " + new String(bys));
            ds.send(dp);
        }


        //关闭发送端
        //void close() 关闭此数据报套接字
        ds.close();
    }
}
