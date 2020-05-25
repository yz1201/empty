package cn.dbdj1201.demo;

/**
 * @author tyz1201
 * @datetime 2020-05-25 10:45
 **/
public enum CardColor {
    BLACK("♠"), RED("♥"), FLOWER("♣"), BLOCK("♦"), REDKING("B"), BLACKKING("b");

    private String color;

    private CardColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getColor();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
