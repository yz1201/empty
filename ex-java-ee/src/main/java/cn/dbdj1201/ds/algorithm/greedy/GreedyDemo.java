package cn.dbdj1201.ds.algorithm.greedy;

import java.util.*;

/**
 * @author tyz1201
 * @datetime 2020-05-14 15:33
 **/
public class GreedyDemo {
    /*
    贪心算法：
        应用场景-集合覆盖问题
        假设存在下面需要付费的广播台，以及广播台信号可以覆盖的地区，如何选择最少的广播台，让所有的地区都被覆盖到
        广播台                                         覆盖地区
        K1                                          北京，上海，天津
        K2                                          广州，北京，深圳
        K3                                          成都，上海，杭州
        K4                                          上海，天津
        K5                                          杭州，大连
     */

    public static void main(String[] args) {
        String shanghai = "上海";
        String beijing = "北京";
        String tianjin = "天津";
        String guangzhou = "广州";
        String shenzhen = "深圳";
        String cd = "成都";
        String hz = "杭州";
        String dl = "大连";
        Map<String, Set<String>> broadcasts = new HashMap<>();
        Set<String> K1 = new HashSet<>();
        K1.add(beijing);
        K1.add(tianjin);
        K1.add(shanghai);

        Set<String> K2 = new HashSet<>();
        K2.add(guangzhou);
        K2.add(beijing);
        K2.add(shenzhen);

        Set<String> K3 = new HashSet<>();
        K3.add(cd);
        K3.add(shanghai);
        K3.add(hz);

        Set<String> K4 = new HashSet<>();
        K4.add(shanghai);
        K4.add(tianjin);

        Set<String> K5 = new HashSet<>();
        K5.add(hz);
        K5.add(dl);

        broadcasts.put("K1", K1);
        broadcasts.put("K2", K2);
        broadcasts.put("K3", K3);
        broadcasts.put("K4", K4);
        broadcasts.put("K5", K5);

        Set<String> allAreas = new HashSet<>();
        allAreas.add(shanghai);
        allAreas.add(shenzhen);
        allAreas.add(dl);
        allAreas.add(beijing);
        allAreas.add(hz);
        allAreas.add(guangzhou);
        allAreas.add(cd);
        allAreas.add(tianjin);

        List<String> selects = new ArrayList<>();

        //临时集合，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集。
        Set<String> tempSet = new HashSet<>();

        //直到全覆盖才停止
        while (allAreas.size() != 0) {
            String maxKey = null;
            //遍历广播map
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                Set<String> areas = broadcasts.get(key); //拿到当前电台能覆盖的地区
                tempSet.addAll(areas);
                //求出两个集合的交集，交集会保存在tempSet
                tempSet.retainAll(allAreas);
                //如果当前集合存在未覆盖地区，并且此次循环最大未覆盖地区不存在或者覆盖的地方少，就当前循环最大覆盖地区的指针就指向当前的key
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //一次循环结束后，若指向最大覆盖地区的指针存在，就添加为选中的电台，并且把它覆盖的地区从源集合中去掉，以进行下一次选取最优解/贪心算法。
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }

        System.out.println(selects);
    }


}
