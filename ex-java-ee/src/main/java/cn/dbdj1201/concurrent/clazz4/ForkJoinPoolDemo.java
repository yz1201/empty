package cn.dbdj1201.concurrent.clazz4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author tyz1201
 * @datetime 2020-04-09 16:30
 **/
public class ForkJoinPoolDemo {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random random = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        System.out.println(Arrays.stream(nums).sum());
    }


    static class AddTask extends RecursiveAction {

        int start, end;

        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (this.end - this.start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                    System.out.println("from " + this.start + " to " + this.end + " sum -> " + sum);
                }
            } else {
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    static class AddTask1 extends RecursiveTask<Long> {

        int start, end;

        AddTask1(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (this.end - this.start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
//                    System.out.println("from " + this.start + " to " + this.end + " sum -> " + sum);
                }
                return sum;
            } else {
                int middle = start + (end - start) / 2;
                AddTask1 subTask1 = new AddTask1(start, middle);
                AddTask1 subTask2 = new AddTask1(middle, end);
                subTask1.fork();
                subTask2.fork();
                return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        System.out.println(fjp);
        AddTask1 task1 = new AddTask1(0, nums.length);
        fjp.execute(task1);
        System.out.println(fjp);
        Long result = task1.join();
        System.out.println("sum -> "+result);
        System.out.println(fjp);

//        System.in.read();
    }
}
