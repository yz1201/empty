package cn.dbdj1201.ds.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author tyz1201
 * @datetime 2020-04-17 1:03
 * 数组实现环形队列
 **/
public class ArrayCircleQueue {
    /*
    思路
    1，front遍历的含义做调整：front指向队列的第一个元素     front初始值为0
    2，rear变量指向队列的最后一个元素的后一个位置，这里需要留一个空间作为约定  rear初始值为0
    3，队列满的条件变为，(rear + 1) % maxSize == front
    4，队列为空，rear == front
    5，队列里存了多少数据？ （rear-front+maxSize）% maxSize
     */

    private int[] array;    //存放数据
    private int maxSize; //表示数组的最大容量
    private int front;   //指向队列头
    private int rear;   //指向队列尾的后一个位置

    public ArrayCircleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        if (isFull()) {
            System.out.println("queue full, can't add");
        } else {
            this.array[rear] = num;
            rear = (rear + 1) % this.maxSize;
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("queue empty");
            return -1;
        } else {
            //这里需要分析出front是指向队列的第一个元素
            //1，先把front对应的值保存到一个临时变量
            //2，front后移
            //3，返回临时保存的变量
            int tempValue = this.array[front];
            front = (front + 1) % this.maxSize;
            return tempValue;
        }
    }

    /**
     * 遍历队列，打印
     */
    public void listQueue() {
        if (isEmpty())
            System.out.println("queue empty");
        //思路，从front开始遍历，遍历有效元素
        for (int i = this.front; i < front + size(); i++) {
            System.out.println(array[i % maxSize]);
        }
    }

    /**
     * 当前队列的元素个数
     *
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * peak头数据
     *
     * @return
     */
    public int headQueue() {
        if (isEmpty())
            System.out.println("queue empty");
        int head = this.front;
        return this.array[head];
    }

    public static void main(String[] args) {
        ArrayCircleQueue queue = new ArrayCircleQueue(4);
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
