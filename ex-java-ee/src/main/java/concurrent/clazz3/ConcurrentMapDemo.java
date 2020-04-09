package concurrent.clazz3;

import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tyz1201
 * @datetime 2020-04-08 22:33
 **/
public class ConcurrentMapDemo {

    /*
    总结：
        对于map/set的选择使用：
            不加锁： HashMap LinkedHashMap TreeMap（排序）
            加锁：  HashTable Collections.synchronizedXXX，这两个在并发量不大的情况下可以使用，因为效率不高
                   ConcurrentHash ConcurrentSkipListMap(高并发且排序, 插入固然慢，但之后查询效率很高)
                   vector 效率问题，copyonwritelist 读的多写的少的场景下使用监听器队列
                   Queue:
                        ConcurrentLinkedQueue  poll peak
                        BlockingQueue 阻塞式队列
                            LinkedBlockingQueue 无界队列 put add offer take
                            ArrayBlockingQueue take
                            DelayQueue 执行定时任务
                            LinkedTransferQueue  transfer
                            SynchronusQueue 没有容量，必须有消费者等着消费，不能放在容器储存
     */
    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();

        CopyOnWriteArrayList list;
        Timer timer;
    }
}
