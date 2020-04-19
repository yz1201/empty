package cn.dbdj1201.ds.stack;

/**
 * @author tyz1201
 * @datetime 2020-04-19 13:15
 **/
public class IStack {

    private final int maxSize; //最大容量
    private final int[] arr;//数组实现
    private int top = 0;//指向栈顶的游标

    public IStack(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    /**
     * 栈长度，有效数据个数
     *
     * @return
     */
    public int size() {
        return Math.max(this.top, 0);
    }

    public boolean isFull() {
        return this.size() == this.maxSize;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * 入栈
     *
     * @param num
     */
    public void push(int num) {
        if (isFull())
            System.out.println("出问题了");
        else {
            this.arr[top++] = num;
        }
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (!isEmpty())
            return this.arr[--top];
        return -1;
    }

    /**
     * 显示栈顶数据
     *
     * @return
     */
    public int peak() {
        return this.arr[top];
    }

}

