package cn.dbdj1201.ds.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author tyz1201
 * @datetime 2020-04-16 0:00
 * 数组模拟队列
 * 这种实现并没有实现队列的复用，指针只会单方向的增加。
 **/
public class ArrayQueue {
    private int[] array;    //存放数据
    private int maxSize; //表示数组的最大容量
    private int front;   //指向队列头
    private int rear;   //指向队列尾

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
        this.front = -1;    //指向队列头部的前一个位置
        this.rear = -1;     //指向队列尾部的数据
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return this.rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    /**
     * 入队
     *
     * @param num
     */
    public void addQueue(int num) {
        //判断队列是否已满
        if (isFull())
            System.out.println("queue full, can't add");
        this.rear++;
        this.array[rear] = num;
    }

    /**
     * 出队
     *
     * @return
     */
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty())
            throw new RuntimeException("queue empty");
        return this.array[++front];
    }

    /**
     * 遍历队列，打印
     */
    public void listQueue() {
        if (isEmpty())
            System.out.println("queue empty");
        Arrays.stream(this.array).forEach(num -> System.out.println(num + " "));
    }

    /**
     * peak头数据
     *
     * @return
     */
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("queue empty");
        int head = this.front + 1;
        return this.array[head];
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 获取队列头");
            System.out.println("h(head): 显示队列头");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.listQueue();
                    break;
                case 'e':
                    System.out.println("exit");
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入数据");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    System.out.println("get queue");
                    System.out.println(queue.getQueue());
                    break;
                case 'h':
                    System.out.println("head queue");
                    System.out.println(queue.headQueue());
                    break;
                default:
                    break;
            }
        }
    }
}
