package com.consious_coder.ratelimiters.algorithms;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;;

public class SlidingWindow implements IRateLimiter {
    
    Queue<Long> window;
    int timeWindow;
    int bucketCapacity;

    
    public SlidingWindow(int bucketCapacity, int timeWindow) {
        this.bucketCapacity = bucketCapacity;
        this.timeWindow = timeWindow;
        this.window = new ConcurrentLinkedQueue<>();
    }

    public boolean allowRequest(Request request) {

        long currentTime = System.currentTimeMillis();
        checkAndUpdateQueue(currentTime);
        if (this.window.size() < bucketCapacity) {
            this.window.offer(currentTime);
            return true;
        }
        return false;
    }

    public void checkAndUpdateQueue(long currentTime) {
        if (this.window.isEmpty()) return;
    
        long timeDiff = (currentTime - this.window.peek()) / 1000;

        while (timeDiff >= this.timeWindow) {
            this.window.poll();
            if (this.window.isEmpty()) break;
            timeDiff = (currentTime - this.window.peek()) / 1000;
        }


    }


}