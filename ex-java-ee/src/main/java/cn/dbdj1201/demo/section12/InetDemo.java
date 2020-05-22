package cn.dbdj1201.demo.section12;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-22 17:23
 **/
public class InetDemo {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(Arrays.toString(address.getAddress()));
            System.out.println(address.getHostName());
            System.out.println(address.getHostAddress());
            System.out.println(address.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
