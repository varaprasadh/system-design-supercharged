package com.consious_coder.ratelimiters.algorithms;


public class TokenBucket implements IRateLimiter {
    
    private int bucketSize;
    private int currentBucketSize;
    private int refillRate;
    private long lastRefillTimestamp;
    
    public TokenBucket(int bucketSize, int refillRate) {
        this.bucketSize = bucketSize;
        this.refillRate = refillRate;
        this.lastRefillTimestamp = System.currentTimeMillis();
        this.currentBucketSize = bucketSize;
    }

    public boolean allowRequest(Request request) {
        refill();
        if (currentBucketSize >= request.getSize()) {
            currentBucketSize -= request.getSize();
            return true;
        } else {
            return false;
        }
    }

    public void refill() {
        long now = System.currentTimeMillis();
        long timeSinceLastRefill = now - lastRefillTimestamp;
        int tokensToAdd = (int) (timeSinceLastRefill / refillRate);
        currentBucketSize = Math.min(currentBucketSize + tokensToAdd, bucketSize);
        lastRefillTimestamp = now;
    }
    

}