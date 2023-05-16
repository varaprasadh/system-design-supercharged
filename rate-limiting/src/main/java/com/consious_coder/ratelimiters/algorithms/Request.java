package com.consious_coder.ratelimiters.algorithms;

public class Request {
    private int id;
    private Object request;
    private int size;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getRequest() {
        return this.request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
