package sda.dropbox.stats;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by RENT on 2017-01-25.
 */
public class Stats{
    private AtomicInteger fileSend = new AtomicInteger(0);

    public void increment() {
        fileSend.incrementAndGet();
    }

    public Integer getAndClear(){
        return fileSend.getAndSet(0);


    }
}
