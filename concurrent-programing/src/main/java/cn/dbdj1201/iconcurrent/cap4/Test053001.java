package cn.dbdj1201.iconcurrent.cap4;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-05-30 10:06
 **/
public class Test053001 {
    /*nio buffer channel selector*/
    public static void main(String[] args) throws IOException {
//        //里边包含了一个byte[10] {0,0,0...0} 间接(堆)
//        ByteBuffer buffer1 = ByteBuffer.allocate(10);
//        //System.out.println(buffer1);//java.nio.HeapByteBuffer[pos=0 lim=10 cap=10]
//
//        //里边包含了一个byte[10] {0,0,0...0} 直接(系统)
//        ByteBuffer buffer2 = ByteBuffer.allocateDirect(10);
//
//        //里边包含了指定的数据"我爱我的祖国" 间接(堆)
//        byte[] bytes = "我爱我的祖国".getBytes();
//        ByteBuffer buffer3 = ByteBuffer.wrap(bytes);
        test8();
    }


    private static void test1() {
        //创建一个长度为10的ByteBuffer==>包含了一个长度为10的byte数组,默认值:{0,0,0,0,...0}
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer);//java.nio.HeapByteBuffer[pos=0 lim=10 cap=10]

        byte[] arr = buffer.array();//获取此缓冲区的 byte 数组
        System.out.println(Arrays.toString(arr));//[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        //public ByteBuffer put(byte b)：向当前可用位置添加数据,一次添加一个字节。
        buffer.put((byte) 1);//1默认是int类型,put方法的参数需要byte类型,需要强转
        byte b1 = 2;
        buffer.put(b1);
        System.out.println(Arrays.toString(arr));//[1, 2, 0, 0, 0, 0, 0, 0, 0, 0]

        //public ByteBuffer put(byte[] byteArray)：向当前可用位置添加一个byte[]数组
        byte[] bytes = {10, 20, 30, 40, 50};
        buffer.put(bytes);
        System.out.println(Arrays.toString(arr));//[1, 2, 10, 20, 30, 40, 50, 0, 0, 0]

        /*
            public ByteBuffer put(byte[] byteArray,int offset,int len)：添加一个byte[]数组的一部分
            int offset:数组的开始索引,从哪个索引开始添加
            int len:添加个数
         */
        buffer.put(bytes, 3, 2);//40,50
        System.out.println(Arrays.toString(arr));//[1, 2, 10, 20, 30, 40, 50, 40, 50, 0]

        //ByteBuffer put(int index, byte b)  往指定索引处添加一个byte字节
        buffer.put(1, (byte) 88);
        System.out.println(Arrays.toString(arr));//[1, 88, 10, 20, 30, 40, 50, 40, 50, 0]
    }

    private static void test2() {
        //创建一个长度为10的ByteBuffer,创建完之后长度是不能改变,底层是数组
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        System.out.println("容量:" + buffer1.capacity());//容量:10 只能存储10个字节

        //创建一个包含指定字节的ByteBuffer
        ByteBuffer buffer2 = ByteBuffer.wrap("你好".getBytes());
        System.out.println("容量:" + buffer2.capacity());//容量:6
        System.out.println(Arrays.toString(buffer2.array()));//[-28, -67, -96, -27, -91, -67]
    }

    private static void test3() {
        //创建一个长度为10的ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("容量:" + buffer.capacity() + " 限制:" + buffer.limit());//容量:10 限制:10 索引[0-9]都可以使用

        //设置限制为3
        buffer.limit(3);//3索引和3索引以后将不可以在使用了
        System.out.println("容量:" + buffer.capacity() + " 限制:" + buffer.limit());//容量:10 限制:3

        buffer.put((byte) 0);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        System.out.println(Arrays.toString(buffer.array()));//[0, 1, 2, 0, 0, 0, 0, 0, 0, 0]

        buffer.put((byte) 3);//BufferOverflowException 超出了限制会抛出异常
    }

    private static void test4() {
        //创建一个长度为10的ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("容量:" + buffer.capacity() + //10:可以存储10个元素
                " 限制:" + buffer.limit() + //10:限制可以使用10以前的索引[0-9]
                " 位置:" + buffer.position());//0:开始索引的位置在0

        buffer.put((byte) 10);//往position=0的索引处添加元素
        buffer.put((byte) 20);//往position=1的索引处添加元素
        buffer.put((byte) 30);//往position=2的索引处添加元素
        System.out.println(Arrays.toString(buffer.array()));//[10, 20, 30, 0, 0, 0, 0, 0, 0, 0]

        System.out.println("容量:" + buffer.capacity() + //10:可以存储10个元素
                " 限制:" + buffer.limit() + //10:限制可以使用10以前的索引[0-9]
                " 位置:" + buffer.position());//3:开始索引的位置在3

        //public Buffer position(int p)：更改当前可写入位置索引。
        buffer.position(1);//把position位置改为1
        buffer.put((byte) 1);//往position=1的索引处添加元素
        buffer.put((byte) 2);//往position=2的索引处添加元素
        System.out.println(Arrays.toString(buffer.array()));//[10, 1, 2, 0, 0, 0, 0, 0, 0, 0]
    }

    private static void test5() {
        //创建一个长度为10的ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //position=0
        buffer.put((byte) 0);
        //position=1
        buffer.mark();//设置标记为1索引
        buffer.put((byte) 1);
        //position=2
        buffer.put((byte) 2);
        //position=3
        System.out.println("位置:" + buffer.position());//位置:3
        System.out.println(Arrays.toString(buffer.array()));//[0, 1, 2, 0, 0, 0, 0, 0, 0, 0]

        buffer.reset();//把position的位置重置为mark方法标记的位置(1) 把position设置为1
        System.out.println("位置:" + buffer.position());//位置:1
        buffer.put((byte) 100);
        System.out.println(Arrays.toString(buffer.array()));//[0, 100, 2, 0, 0, 0, 0, 0, 0, 0]
    }

    private static void test6() {
        //创建一个长度为10的ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0);//position=0==>1
        buffer.put((byte) 1);//position=1==>2
        buffer.put((byte) 2);//position=2==>3                            p                   limit
        System.out.println(Arrays.toString(buffer.array()));//[0, 1, 2, 0, 0, 0, 0, 0, 0, 0]  10

        //public int remaining()：获取position与limit之间的元素数量。
        System.out.println(buffer.remaining());//7[3-9]

        //public boolean isReadOnly()：获取当前缓冲区是否只读。
        System.out.println(buffer.isReadOnly());//false:既能读,有能写  true:只能读,不能写

        //public boolean isDirect()：获取当前缓冲区是否为直接缓冲区。
        System.out.println(buffer.isDirect());//false:间接缓冲区(堆)  true:直接缓冲区(系统内存)

        buffer.limit(5);//设置限制为5
        System.out.println("位置:" + buffer.position() + " 限制:" + buffer.limit());//位置:3 限制:5
        /*
            public Buffer clear()：还原缓冲区的状态。
            - 将position设置为：0
            - 将限制limit设置为容量capacity；
            - 丢弃标记mark。
         */
        //buffer.clear();
        //System.out.println("位置:"+buffer.position()+" 限制:"+buffer.limit());//位置:0 限制:10

        /*
            public Buffer flip()：缩小limit的范围。 获取读取的有效数据0到position之间的数据
            - 将limit设置为当前position位置； [0, 1, 2, 0, 0, 0, 0, 0, 0, 0]  position=3 limit=10
            - 将当前position位置设置为0；   position=0 limit=3  new String(bytes,0,len)
            - 丢弃标记。
         */
        buffer.flip();
        System.out.println("位置:" + buffer.position() + " 限制:" + buffer.limit());//位置:0 限制:3
        buffer.clear();
        System.out.println("位置:" + buffer.position() + " 限制:" + buffer.limit());//位置:0 限制:3

    }

    private static void test7() throws IOException {
        /*FileChannel*/
//        FileChannel
        //1.创建FileInputStream对象,构造方法中绑定要读取的数据源
        FileInputStream fis = new FileInputStream("D:\\test\\test1\\test2\\t3.txt");
        //2.创建FileOutputStream对象,构造方法中绑定要写入的目的地
        FileOutputStream fos = new FileOutputStream("D:\\test\\test1\\test2\\t2562222.txt");
        //3.使用FileInputStream对象中的方法getChannel,获取读取文件的FileChannel对象
        FileChannel fisChannel = fis.getChannel();
        //4.使用FileOutputStream对象中的方法getChannel,获取写入文件的FileChannel对象
        FileChannel fosChannel = fos.getChannel();
        //一读一写复制文件
        //5.使用读取的FileChannel对象中的方法read,读取文件  int read(ByteBuffer dst)
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("?");
        while (fisChannel.read(buffer) != -1) {
            //6.使用写入的FileChannel对象中的方法write,把读取到数据写入到文件中 int write(ByteBuffer src)
            //使用flip方法缩小limit的范围:最后一次读取的不一定是1024个字节
            System.out.println("flip前:position:" + buffer.position() + ",limit:" + buffer.limit());
            buffer.flip();//limit设置当前position的位置(1000),position设置为0
            System.out.println("flip后:position:" + buffer.position() + ",limit:" + buffer.limit());
            fosChannel.write(buffer);//0-limit之间的数据,写完之后会修改position的位置(1000)
            //初始化ByteBuffer的状态
            System.out.println("clear前:position:" + buffer.position() + ",limit:" + buffer.limit());
            buffer.clear();//position设置为0,将limit设置为容量(1024)
            System.out.println("clear后:position:" + buffer.position() + ",limit:" + buffer.limit());
            System.out.println("-------------------------------------");
        }
        //7.释放资源
        fosChannel.close();
        fisChannel.close();
        fos.close();
        fis.close();
    }

    private static void test8() throws IOException {
        long s = System.currentTimeMillis();
        //1.创建读取文件的RandomAccessFile对象,构造方法中封装要读取的数据源和设置只读("r")模式
        RandomAccessFile inRAF = new RandomAccessFile("D:\\test\\test1\\test2\\t3.txt", "r");
        //2.创建写入文件的RandomAccessFile对象,构造方法中封装要写入的目的地和设置读写("rw")模式
        RandomAccessFile outRAF = new RandomAccessFile("D:\\test\\test1\\test2\\t23423.txt", "rw");
        //3.使用读取文件的RandomAccessFile对象中的方法getChannel,获取读取文件的FileChannel对象
        FileChannel inRAFChannel = inRAF.getChannel();
        //4.使用写入文件的RandomAccessFile对象中的方法getChannel,获取写入文件的FileChannel对象
        FileChannel outRAFChannel = outRAF.getChannel();
        //5.使用读取文件的FileChannel对象中的方法size,获取读取的文件大小(字节)
        long size = inRAFChannel.size();
        System.out.println(size);//785042177字节
        //6.使用读取文件的FileChannel对象中的方法map,创建读取文件的直接缓冲区MappedByteBuffer对象
        MappedByteBuffer inMap = inRAFChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
        //7.使用写入文件的FileChannel对象中的方法map,创建写入文件的直接缓冲区MappedByteBuffer对象
        MappedByteBuffer outMap = outRAFChannel.map(FileChannel.MapMode.READ_WRITE, 0, size);
        //8.创建for循环,循环size次
        for (int i = 0; i < size; i++) {
            //9.使用读取文件的直接缓冲区MappedByteBuffer对象中的方法get,读取数据源指定索引处的字节
            byte b = inMap.get(i);
            //10.使用写入文件的直接缓冲区MappedByteBuffer对象中的方法put,把读取到的字节写入到目的地指定的索引处
            outMap.put(i, b);
        }
        //11.释放资源
        outRAFChannel.close();
        inRAFChannel.close();
        outRAF.close();
        inRAF.close();
        long e = System.currentTimeMillis();
        System.out.println("复制文件共耗时:" + (e - s) + "毫秒!");//复制文件共耗时:4388毫秒!
    }
}
