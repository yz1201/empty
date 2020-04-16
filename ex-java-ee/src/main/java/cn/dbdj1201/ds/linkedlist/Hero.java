package cn.dbdj1201.ds.linkedlist;

/**
 * @author tyz1201
 * @datetime 2020-04-17 2:54
 * 水浒英雄
 **/
public class Hero {
    private int no;             //座次
    private String name;        //名字
    private String nickname;    //诨号

    public Hero() {
    }

    public Hero(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
