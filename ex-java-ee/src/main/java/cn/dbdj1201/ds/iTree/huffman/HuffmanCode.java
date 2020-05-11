package cn.dbdj1201.ds.iTree.huffman;

import java.io.*;
import java.util.*;

/**
 * @author tyz1201
 * @datetime 2020-05-11 14:34
 **/
public class HuffmanCode {
    /*
    赫夫曼编码压缩文件注意:
        如果文件本身就是经过压缩处理的，那么再用这个压缩不会有明显变化，比如视频或者ppt
        赫夫曼编码是按字节来处理的，可以处理所有文件(二进制文件，文本文件)
        如果一个文件中的内容重复的数据不多，那么压缩效果也不会太明显。
     */

    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] bytes = content.getBytes();
//        System.out.println(bytes.length);
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//        List<Node2> node2s = getNodes(bytes);
//        Collections.sort(node2s);
//        node2s.forEach(System.out::println);
//        Node2 node2 = huffmanTreeBuilder(node2s);
//        System.out.println(node2);

//        preOrder(node2);

//        getCodes(node2, "", stringBuilder);
//        huffmanCodes.entrySet().forEach(System.out::println);
//        byte[] zip = zip(bytes, huffmanCodes);
//        for (byte b : zip) {
//            System.out.println(b);
//        }

//        byte[] decode = decode(huffmanCodes, zip);
//        System.out.println(new String(decode));
//        for (byte b : decode) {
//            System.out.println(String.valueOf(b));
//        }

//        byte[] huffmanZip = huffmanZip(bytes);
//        System.out.println(huffmanZip.length);
//        for (byte b : huffmanZip) {
//            System.out.println(b);
//        }

        String zipFile = "D:\\test\\huffman_zip\\dst.zip";
        String dstFile = "D:\\test\\huffman_zip\\testNew.jpg";

//        zipFile(srcFile, dstFile);
        unZipFile(zipFile, dstFile);
        System.out.println("done");
    }

    private static List<Node2> getNodes(byte[] bytes) {
        List<Node2> node2s = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte aByte : bytes) {
            counts.merge(aByte, 1, Integer::sum);
        }

        //把存到map的数据转化到节点中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            node2s.add(new Node2(entry.getKey(), entry.getValue()));
        }
        return node2s;
    }

    private static Node2 huffmanTreeBuilder(List<Node2> node2s) {
        while (node2s.size() > 1) {
            Collections.sort(node2s);
            Node2 leftNode = node2s.get(0);
            Node2 rightNode = node2s.get(1);
            Node2 parent = new Node2(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            node2s.remove(leftNode);
            node2s.remove(rightNode);
            node2s.add(parent);
        }

        return node2s.get(0);
    }

    private static void preOrder(Node2 root) {
        if (root != null)
            root.preOrder();
        else
            System.out.println("出问题了");
    }

    //赫夫曼树对应的赫夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * @param node2         根节点
     * @param code          表示路径。左子节点0，右边子节点为1
     * @param stringBuilder 路径拼接
     */
    private static void getCodes(Node2 node2, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node2 != null) {
            if (node2.data == null) {
                getCodes(node2.left, "0", stringBuilder2);
                getCodes(node2.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node2.data, stringBuilder2.toString());
            }
        }
    }

    private static Map<Byte, String> getCodes(Node2 root) {
        if (root == null)
            return null;
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //编写一个方法，将字节串对于的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩的byte[]

    /**
     * @param bytes        待压缩字节数组字节数组
     * @param huffmanCodes 赫夫曼编码表
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //将待压缩字节数组转换为赫夫曼编码对应的字节串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            String temp = huffmanCodes.get(aByte);
//            System.out.println("this-> " + temp);
            stringBuilder.append(temp);
        }

//        System.out.println(stringBuilder);
        //将转换好的字节串转为压缩后的字节数组
        //统计返回 byte数组 huffmanCodeBytes长度
        int len = (stringBuilder.length() + 7) / 8;

        //创建 存储压缩后的字节数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else
                strByte = stringBuilder.substring(i, i + 8);

            //将该字节串转成一个字节，放入huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * @param bytes 原始的字节串对应的字节数组
     * @return 用赫夫曼编码压缩过的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //处理原始字节数组，转化为节点
        //构造赫夫曼树
        //根据赫夫曼树生成赫夫曼编码表
        //根据赫夫曼编码表压缩原始字节数组。
        return zip(bytes, getCodes(huffmanTreeBuilder(getNodes(bytes))));
    }

    /**
     * @param flag 是否需要补高位，true表示需要补高位，false不需要
     * @param b    传入的字节
     * @return 是该字节对应的二进制的字符串，但是是按补码返回的
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;

        if (flag) {
            temp |= 256;
        }

        String s = Integer.toBinaryString(temp);
        if (flag)
            return s.substring(s.length() - 8);
        else
            return s;
    }

    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + count); //i 不动，count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null)
                    count++;
                else
                    flag = false;
            }
            list.add(b);
            i += count; //i直接移动到count位置
        }
        //把list数据放进一个字节数组中
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    private static byte[] huffmanUnzip() {
        return null;
    }

    //将一个文件进行压缩

    /**
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件输入流
        FileInputStream fis = null;
        try {
            //读文件，压缩，将huffman编码跟压缩后的字节数组全部输出到目标文件
            fis = new FileInputStream(srcFile);
            byte[] bytes = new byte[fis.available()];

            fis.read(bytes);
            byte[] huffmanBytes = huffmanZip(bytes);

            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);

            //我们以对象流的方式写入赫夫曼编码，是为了之后恢复源文件时使用
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    //文件解压

    /**
     * @param zipFile
     * @param dstFile
     */
    public static void unZipFile(String zipFile, String dstFile) {
        ObjectInputStream ois = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            fos = new FileOutputStream(dstFile);
            fos.write(decode);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Node2 implements Comparable<Node2> {
    Byte data;      //存放数据本身，比如'a' => 97 ' ' => 32
    int weight;     //权值，表示字节出现次数
    Node2 left;
    Node2 right;

    public Node2(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node2 o) {
        return this.weight - o.weight; //从小到大
    }

    @Override
    public String toString() {
        return "Node2{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);

        if (this.left != null)
            this.left.preOrder();

        if (this.right != null)
            this.right.preOrder();
    }


}