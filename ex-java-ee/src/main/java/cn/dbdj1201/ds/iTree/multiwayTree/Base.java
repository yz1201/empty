package cn.dbdj1201.ds.iTree.multiwayTree;

/**
 * @author tyz1201
 * @datetime 2020-05-12 16:50
 **/
public class Base {
    /*
    在二叉树中，每个节点都有数据项，最多有两个子节点，如果允许每个节点可以有更多数据项和子节点，就是多叉树。
    后面我们讲解的2-3树，2-3-4树就是多叉树，多叉树通过重新组织节点，减少树的高度，能对二叉树进行优化

    二叉树与B树：
    B树通过重新组织节点，降低树的高度，并且减少i/o读写次数来提高效率。
    文件系统以及数据库系统的设计者利用了磁盘预读原理，将一个节点的大小设为等于一个页(大小通常为4k)，这样每个节点只需要一个io就可以完全载入
    将树的度M设置为1024，在600亿个元素中最多只需要4次io操作就可以读取到想要的元素，B树广泛应用于文件存储系统及数据库系统中。
    节点的度：节点下边的子树个数叫树
    树的度：所有节点里边度最大的值。

    2-3树简介：
    2-3树是最简单的B树结构，具有如下特点：
    2-3树的所有叶子节点都在同一层（B树都这样）
    有两个子节点的节点叫二节点，二节点要么没有子节点，要么有两个子节点。
    有三个子节点的节点叫三节点，要么无，要么三个。
    顾名思义，2-3树就是由2节点跟3节点构成的树

    B树：
    B就是Balanced，平衡的意思，B树就是B-tree
    应用：mysql某种类型的索引就是基于B树或者B+树的。
    1，B树的阶：节点的最多子节点个数，比如2-3树就是3，2-3-4树就是4
    2，B树的搜索，从根节点开始，对节点内的关键字进行二分查找，如果命中则结束，否则进入查询关键字所属的儿子节点，重复，直到所对应的儿子指针为空或者已经是儿子节点。
    3，关键字集合分布在整棵树种，即叶子节点和非叶子节点都存放数据。
    4，搜索有可能在非叶子节点结束
    5，其搜索性能等价于在关键字全集内做一次二分查找。

    B+树，是B树的变体，也是一种多路搜索树
    1，B+树的搜索与B树基本相同，区别是B+树只有达到叶子节点才会命中(它的数据只放在叶子节点)，其性能也等价于在关键字集合内做一次二分查找(即nlogn线性对数阶)
    2，所有关键字都出现在叶子节点的链表种(即数据只能在叶子节点，也叫稠密索引)，且链表种的关键字数据恰好是有序的
    3，不可能在非叶子节点命中
    4，非叶子节点相当于叶子节点的索引(稀疏索引)，叶子节点相当于是存储关键字数据的数据层
    5，更适合文件索引系统
    6，B树和B+树各有自己的应用场景，不能说B+树完全比B树好，反之亦然。

    B*树
    B*树是B+树的变体，在B+数的非根和非叶子节点再增加指向兄弟的指针
    1，B*树定义了非叶子节点关键字个数至少为2/3*M，即块的最低使用率为2/3，而B+树的块最低使用率为B+树的1/2；
    2，B*树分配新节点的概率比B+树低，空间使用率高。
     */
}