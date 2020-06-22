package cn.dbdj1201.iconcurrent.cap6;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author tyz1201
 * @datetime 2020-06-22 10:04
 **/
@Slf4j(topic = "c.AccountDemo")
public class AccountDemo {
    public static void main(String[] args) {
        DecimalAccount.demo(new DecimalAccountCAS(new BigDecimal("10000")));

    }
}

class DecimalAccountCAS implements DecimalAccount {

    private AtomicReference<BigDecimal> balance;

    public DecimalAccountCAS(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while (true) {
            BigDecimal prev = this.balance.get();
            BigDecimal next = prev.subtract(amount);
            if (this.balance.compareAndSet(prev, next)) {
                break;
            }
        }

    }
}

interface DecimalAccount {

    BigDecimal getBalance();

    void withdraw(BigDecimal amount);

    static void demo(DecimalAccount account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> account.withdraw(BigDecimal.TEN)));
        }

        ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.getBalance());
    }
}
