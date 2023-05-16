package com.consious_coder.ratelimiters.algorithms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucket implements IRateLimiter{
    
    private BlockingQueue<Integer> queue;

    private long lastLeakTimestamp;

    private int leakingRate;

    public LeakyBucket(int capacity, int leakingRate) {
        this.queue = new LinkedBlockingQueue<>(capacity);

        this.leakingRate = leakingRate;
        this.lastLeakTimestamp = System.currentTimeMillis();
    }


    public boolean allowRequest(Request request) {

        leakBucket();

        if (queue.remainingCapacity() > 0) {
            queue.add(1);
            return true;
        }
        return false;
    }

    public void leakBucket() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - this.lastLeakTimestamp;

        long tokenToLeak = elapsedTime * this.leakingRate / 1000;

        while (tokenToLeak > 0 && this.queue.size() > 0) {
            this.queue.poll();
            tokenToLeak--;
        }

    }

}

