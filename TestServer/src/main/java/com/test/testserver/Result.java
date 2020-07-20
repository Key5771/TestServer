package com.test.testserver;

public class Result<T> {
    private final T data;
    private final boolean ok;

    public Result() {
        data = null;
        ok = false;
    }

    public Result(T data) {
        this.data = data;
        ok = true;
    }

    public T getData() {
        return data;
    }

    public boolean getOk() {
        return ok;
    }
}