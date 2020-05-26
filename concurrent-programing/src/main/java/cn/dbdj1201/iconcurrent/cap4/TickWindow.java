package cn.dbdj1201.iconcurrent.cap4;

/**
 * @author tyz1201
 * @datetime 2020-05-26 16:12
 **/
public class TickWindow {

    private int count;

    public TickWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int sell(int amount) {
        if (amount > this.count) {
            return -1;
        } else {
            this.count -= amount;
            return amount;
        }

    }
}
