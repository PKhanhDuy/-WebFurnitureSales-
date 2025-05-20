package com.supportjobsearch.Bean;

import com.supportjobsearch.enums.Method;
import com.supportjobsearch.enums.Statuss;

import java.io.Serializable;

public class Payment implements Serializable {
    private int id;
    private Method method;
    private Statuss status;

    public Payment(int id, Method method, Statuss status) {
        this.id = id;
        this.method = method;
        this.status = status;
    }

    public Payment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Statuss getStatus() {
        return status;
    }

    public void setStatus(Statuss status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", method=" + method +
                ", status=" + status +
                '}';
    }
}
